package com.example.Backend.Services;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.example.Backend.DTOS.AddressDto;
import com.example.Backend.DTOS.AdminDashboards.UserReports.BookingCount;
import com.example.Backend.DTOS.AdminDashboards.UserReports.UserReport;
import com.example.Backend.DTOS.AdminDashboards.UserReports.UserReportList;
import com.example.Backend.DTOS.Users.MUsers.UserProfileDto;
import com.example.Backend.DTOS.Users.UserList;
import com.example.Backend.DTOS.Users.UserView;
import com.example.Backend.Events.Event;
import com.example.Backend.Models.Address;
import com.example.Backend.Models.User;
import com.example.Backend.Repositories.AddressRepository;
import com.example.Backend.Repositories.BookingRepository;
import com.example.Backend.Repositories.UserRepository;
import com.example.Backend.Requests.UserUpdateRequest;
import com.example.Backend.Responses.Messages.Message;
import com.example.Backend.Responses.Users.UserResponse;
import com.example.Backend.Utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressService addressService;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private Cloudinary cloudinary;



    @Async
    public CompletableFuture<ResponseEntity<?>> add(User user,Double lat,Double lon,String addressName){
        user.setCreateDate(LocalDateTime.now());
        User save = this.userRepository.save(user);
        this.addressService.add(new Address(addressName,"Active",lat,lon,LocalDate.now()),save.getId());
        save.setToken(JwtUtil.generateToken(save.getId().toString(),"USER"));
        this.userRepository.save(save);
        this.messagingTemplate.convertAndSend("/queue/user",new Event(true));
        return CompletableFuture.completedFuture(ResponseEntity.ok(new UserResponse(save.getId(),save.getToken())));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> setFmcToken(Long userId,String fmcToken){
        try{
            User user = this.userRepository.findById(userId).orElse(null);
            if(user ==null) return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("No data")));
            user.setFcmToken(fmcToken);
            this.userRepository.save(user);
            return CompletableFuture.completedFuture(
                    ResponseEntity.ok(
                            new Message("Successfully")
                    )
            );
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @Async
    public  CompletableFuture<ResponseEntity<?>> getUserByTel(String tel){
        if(this.userRepository.findByTel(tel).isPresent()){
            User user = this.userRepository.findByTel(tel).get();
            user.setToken(JwtUtil.generateToken(user.getId().toString(),"USER"));
            User save = this.userRepository.save(user);
            return CompletableFuture.completedFuture(ResponseEntity.ok(new UserResponse(save.getId(),save.getToken())));
        }
        return CompletableFuture.completedFuture(ResponseEntity.status(404).body("No Account"));
    }

    private String getPublicIdFromUrl(String url) {
        String[] parts = url.split("/");
        String lastPart = parts[parts.length - 1];
        int dotIndex = lastPart.lastIndexOf('.');
        if (dotIndex > 0) {
            return lastPart.substring(0, dotIndex);
        }
        return lastPart;
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> setProfile(long id, MultipartFile file){
        try {
            User user = this.userRepository.findById(id).orElse(null);
            if (user == null)
                return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("No data")));
            if (user.getProfile() != null)
                cloudinary.uploader().destroy(getPublicIdFromUrl(user.getProfile()), ObjectUtils.emptyMap());
            user.setProfile(cloudinary.url().transformation(new Transformation()
                            .crop("limit")
                            .width(800)
                            .height(800)

                    )
                    .generate(cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("public_id").toString()));
            this.userRepository.save(user);
            this.messagingTemplate.convertAndSend("/queue/user", new Event(true));
            return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    public ResponseEntity<?> editeTel(User user){
        this.userRepository.save(user);
        return ResponseEntity.ok(new Message("Successfully"));
    }

    public User getById(long id){
        return this.userRepository.findById(id).orElse(null);
    }

    // Get To Admin List
    @Async
    public CompletableFuture<ResponseEntity<?>> getToAdminList(){
        List<UserList> lists = new ArrayList<>();
        this.userRepository.findAll().forEach(
                user -> {
                    String address = "";
                    if( this.addressRepository.findByUser_IdAndStatus(user.getId(),"Default")!=null){
                        address =this.addressRepository.findByUser_IdAndStatus(user.getId(),"Default").getAddress();
                    }
                    lists.add(
                            new UserList(
                                    user.getId(),
                                    user.getName(),
                                    user.getTel(),
                                    user.getGender(),
                                    user.getCreateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a")),
                                    user.getProfile(),
                                    address
                            )
                    );
                }
        );
        return CompletableFuture.completedFuture(ResponseEntity.ok(lists));
    }

    // Get To Admin View
    @Async
    public CompletableFuture<ResponseEntity<?>> getToAdminView(Long id){
        User user = this.userRepository.findById(id).orElse(new User());
        Address address = new Address();
        address.setLon(10); address.setLat(10);
        if(this.addressRepository.findByUser_IdAndStatus(user.getId(),"Default")!=null){
          address = this.addressRepository.findByUser_IdAndStatus(user.getId(),"Default");
        }
        return CompletableFuture.completedFuture(ResponseEntity.ok(new UserView(
                user.getName(),
                user.getGender(),
                user.getTel(),
                user.getDob(),
                user.getCreateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a")),
                new AddressDto(address.getLat(),address.getLon()),
                user.getProfile()
        )));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> getUserProfile(Long id){
       try{
           User user = this.userRepository.findById(id).orElse(new User());
           return CompletableFuture.completedFuture(
                   ResponseEntity.ok(
                           new UserProfileDto(
                                   user.getName(),
                                   user.getTel(),
                                   user.getProfile(),
                                   this.bookingRepository.countByUser_Id(user.getId()),
                                   this.bookingRepository.countByUser_IdAndStatus(user.getId(),"Completed"),
                                   this.bookingRepository.countByUser_IdAndStatus(user.getId(),"Cancelled"),
                                   user.getDob()
                           )
                   )
           );
       } catch (Exception e) {
           return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
       }
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> editeProfile(UserUpdateRequest user){
        try{
            User existion = this.userRepository.findById(user.getUserId()).orElse(new User());
            existion.setName(user.getName());
            existion.setTel(user.getTel());
            existion.setDob(user.getDob());
            if(user.getImage() !=null){
                if(existion.getProfile() !=null)cloudinary.uploader().destroy(getPublicIdFromUrl(existion.getProfile()),ObjectUtils.emptyMap());
                existion.setProfile( cloudinary.url().transformation(new Transformation()
                                .width(800)
                                .height(800)
                                .crop("fill")
                                .gravity("auto")
                                .fetchFormat("auto")
                                .quality("auto"))
                        .generate(cloudinary.uploader().upload(user.getImage().getBytes(), ObjectUtils.emptyMap()).get("public_id").toString()));
                this.userRepository.save(existion);
                this.messagingTemplate.convertAndSend("/queue/user",new Event(true));
                return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
            }
            this.userRepository.save(existion);
            this.messagingTemplate.convertAndSend("/queue/user",new Event(true));
            return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> userReport(){
        try{
            List<UserReportList> users = new ArrayList<>();
            this.userRepository.findAll().forEach(
                    user -> {
                        users.add(
                                new UserReportList(
                                        user.getId(),
                                        user.getName(),
                                        user.getTel(),
                                        this.bookingRepository.countByUser_Id(user.getId()),
                                        this.bookingRepository.countByUser_IdAndStatus(user.getId(),"Completed"),
                                        this.bookingRepository.countByUser_IdAndStatus(user.getId(),"Cancelled"),
                                        user.getCreateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a"))

                                )
                        );
                    }
            );
            return  CompletableFuture.completedFuture(
                    ResponseEntity.ok(
                            new UserReport(
                                    this.userRepository.getUserPerMonth(LocalDate.now().getYear()),
                                    new BookingCount(
                                            this.bookingRepository.count(),
                                            this.bookingRepository.countByStatus("Completed"),
                                            this.bookingRepository.countByStatus("Cancelled")
                                    ),
                                    users
                            )
                    )
            );
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }
}

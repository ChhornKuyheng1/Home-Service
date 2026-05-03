package com.example.Backend.Services;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.example.Backend.DTOS.AdminDashboards.Dashboard;
import com.example.Backend.DTOS.AdminDashboards.ProviderRequestViewDashboard;
import com.example.Backend.DTOS.AdminDashboards.RecentBooking;
import com.example.Backend.DTOS.AdminDto;
import com.example.Backend.Models.Admin;
import com.example.Backend.Repositories.*;
import com.example.Backend.Requests.AdminChengPassword;
import com.example.Backend.Requests.AdminRequest;
import com.example.Backend.Requests.AdminUpdate;
import com.example.Backend.Responses.Messages.Message;
import com.example.Backend.Responses.Users.UserResponse;
import com.example.Backend.Utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<?> add(AdminRequest adminRequest){
        Admin admin = new Admin(
                adminRequest.getName(),
                adminRequest.getGender(),
                adminRequest.getTel(),
                adminRequest.getEmail(),
                passwordEncoder.encode(adminRequest.getPassword()));
        Admin save = this.adminRepository.save(admin);
        save.setToken(JwtUtil.generateToken(save.getId().toString(),"ADMIN"));
        this.adminRepository.save(save);
        return ResponseEntity.ok(new Message("Successfully"));
    }

   public ResponseEntity<?> getByEmailAndPassword(String email,String password) {
       Admin admin = this.adminRepository.findByEmail(email);
       if(admin == null) return ResponseEntity.status(404).body(new Message("Email not found"));
       if(!this.passwordEncoder.matches(password,admin.getPassword())){return ResponseEntity.status(404).body(new Message("Invalid password"));}
       admin.setToken(JwtUtil.generateToken(admin.getId().toString(),"ADMIN"));
       this.adminRepository.save(admin);
       return ResponseEntity.ok(new UserResponse(admin.getId(),admin.getToken()));
   }

   @Async
   public CompletableFuture<ResponseEntity<?>> getDashboard(){
        List<ProviderRequestViewDashboard> provider = new ArrayList<>();
        List<RecentBooking> bookings = new ArrayList<>();
        this.providerRepository.findByjob_status("Pending").forEach(
                provider1 -> {
                    provider.add(
                            new ProviderRequestViewDashboard(
                                    provider1.getId(),
                                    provider1.getBusinessName(),
                                    provider1.getUser().getTel(),
                                    provider1.getUser().getProfile(),
                                    provider1.getEmail()
                            )
                    );
                    if(provider.size()==4) return;
                }
        );
        this.bookingRepository.findAll4Date().forEach(
                booking -> {
                    bookings.add(
                       new RecentBooking(
                               booking.getId(),
                               booking.getJobFocus().getJob().getProvider().getBusinessName(),
                               booking.getBookingDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a")),
                               booking.getJobFocus().getSkill().getName(),
                               booking.getJobFocus().getJob().getProvider().getUser().getProfile(),
                               booking.getStatus()
                       )
                    );
                }
        );
        Double total =0.0;
        if(this.transactionRepository.sumPriceByStatus("Completed")!=null){ total =  this.transactionRepository.sumPriceByStatus("Completed");}
        return CompletableFuture.completedFuture(
                ResponseEntity.ok(
                        new Dashboard(
                                total,
                                this.providerRepository.count(),
                                this.userRepository.count(),
                                this.bookingRepository.count(),
                                this.transactionRepository.getEarnings(LocalDate.now().getYear()),
                                provider,
                                bookings
                        )
                )
        );
   }

   @Async
    public CompletableFuture<ResponseEntity<?>> getAdminById(Long id){
        try{
            Admin admin = this.adminRepository.findById(id).orElse(null);
            if(admin==null) return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("No data")));
            return CompletableFuture.completedFuture(ResponseEntity.ok(new AdminDto(
                    admin.getName(),
                    admin.getGender(),
                    admin.getTel(),
                    admin.getEmail(),
                    admin.getProfile()
            )));
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
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
    public CompletableFuture<ResponseEntity<?>> edite(Long id, AdminUpdate admin){
        try{
            Admin existion = this.adminRepository.findById(id).orElse(null);
            if(existion==null) return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("No data")));
            existion.setName(admin.getName());
            existion.setTel(admin.getTel());
            existion.setGender(admin.getGender());
            if(admin.getImage() == null){
                this.adminRepository.save(existion);
                return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
            }
            if(existion.getProfile()!=null) cloudinary.uploader().destroy(getPublicIdFromUrl(existion.getProfile()),ObjectUtils.emptyMap());
            existion.setProfile(
                    cloudinary.url().transformation(new Transformation()
                            .width(800)
                            .height(800)
                            .crop("fill")
                            .gravity("auto")
                            .fetchFormat("auto")
                            .quality("auto"))
                    .generate(cloudinary.uploader().upload(admin.getImage().getBytes(), ObjectUtils.emptyMap()).get("public_id").toString())
            );
            this.adminRepository.save(existion);
            return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> chengPassword(Long id, AdminChengPassword password){
        try{
            Admin admin = this.adminRepository.findById(id).orElse(null);
            if(admin == null) return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("No data")));
            if(!this.passwordEncoder.matches(password.getOldPassword(),admin.getPassword())){return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("Invalid password")));}
            admin.setPassword(this.passwordEncoder.encode(password.getNewPassword()));
            this.adminRepository.save(admin);
            return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> chengPassword(String email,String password){
        try{
            Admin admin = this.adminRepository.findByEmail(email);
            if(admin == null) return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("No data")));
            admin.setPassword(this.passwordEncoder.encode(password));
            this.adminRepository.save(admin);
            return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }
}

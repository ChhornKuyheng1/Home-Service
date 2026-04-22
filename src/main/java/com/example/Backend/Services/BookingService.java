package com.example.Backend.Services;

import com.example.Backend.DTOS.AddressDto;
import com.example.Backend.DTOS.Bookings.*;
import com.example.Backend.DTOS.MobileProviders.Jobs.*;
import com.example.Backend.DTOS.MobileProviders.Jobs.InProgress.InProgress;
import com.example.Backend.DTOS.MobileProviders.Jobs.InProgress.ServiceDto;
import com.example.Backend.DTOS.Users.MUsers.*;
import com.example.Backend.Events.Event;
import com.example.Backend.Models.*;
import com.example.Backend.Repositories.BookingRepository;
import com.example.Backend.Repositories.JobRepository;
import com.example.Backend.Repositories.ProviderRepository;
import com.example.Backend.Requests.BookingRequest;
import com.example.Backend.Responses.Messages.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private JobFocusService jobFocusService;

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private NotificationService NotificationService;

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private TimeService timeOperationService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private JobRepository jobRepository;

    @Async
    public  CompletableFuture<ResponseEntity<?>>  add(BookingRequest booking){
        User user = this.userService.getById(booking.getUserId());
        JobFocus jobFocus = this.jobFocusService.getById(booking.getJobFocusId());
        Address address = this.addressService.getById(booking.getAddressId());
        Booking save =  this.bookingRepository.save(
                new Booking(
                        user,
                        jobFocus,
                        booking.getWorkDate(),
                        booking.getWorkTime(),
                        LocalDateTime.now(),
                        "Requested",
                        address

                )
        );
        NotificationService.sendMessage(
                jobFocus.getJob().getProvider().getUser(),
                "BOOKING",
                "Your have a new booking request.",
                "BOOKING/PROVIDER",
                save.getId()
                );
        this.messagingTemplate.convertAndSend("/queue/booking",new Event(true));
        this.messagingTemplate.convertAndSend("/queue/dashboard", new Event(true));
        return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
    }

    @Async
    public  CompletableFuture<ResponseEntity<?>> setUpcomingStratus(Long bookingId){
        Booking booking = this.bookingRepository.findById(bookingId).orElse(null);
        if(booking == null) return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("No data")));
        booking.setStatus("Upcoming");
        booking.setConfirmDate(LocalDateTime.now());
        this.bookingRepository.save(booking);
        NotificationService.sendMessage(
                booking.getUser(),
                "BOOKING",
                "Your booking is confirmed.",
                "BOOKING/USER",
                booking.getUser().getId()
                );
        this.messagingTemplate.convertAndSend("/queue/booking",new Event(true));
        this.messagingTemplate.convertAndSendToUser(booking.getUser().getId().toString(),"/queue/booking/upcoming",new Event(true));
        return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> setInProgressStatus(Long bookingId){
        Booking booking = this.bookingRepository.findById(bookingId).orElse(null);
        if(booking == null) return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("No data")));
        booking.setStatus("In Progress");
        booking.setInProgressDate(LocalDateTime.now());
        this.bookingRepository.save(booking);
        NotificationService.sendMessage(
                booking.getUser(),
                "BOOKING",
                "Your service is now in InProgress.",
                "BOOKING/USER",
                booking.getUser().getId()
        );
        this.messagingTemplate.convertAndSend("/queue/booking",new Event(true));
        this.messagingTemplate.convertAndSendToUser(booking.getUser().getId().toString(),"/queue/booking/inprogress",new Event(true));
        return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> setCancelledStatus(Long bookingId){
        Booking booking = this.bookingRepository.findById(bookingId).orElse(null);
        if(booking == null) return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("No data")));
        booking.setStatus("Cancelled");
        this.bookingRepository.save(booking);
        NotificationService.sendMessage(
                booking.getUser(),
                "BOOKING",
                "This booking has been Cancelled",
                "BOOKING/USER",
                booking.getUser().getId()
        );
        this.messagingTemplate.convertAndSend("/queue/booking",new Event(true));
        this.messagingTemplate.convertAndSendToUser(booking.getUser().getId().toString(),"/queue/booking/cancelled",new Event(true));
        return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> setCancelledStatusFoUser(Long bookingId){
        Booking booking = this.bookingRepository.findById(bookingId).orElse(null);
        if(booking == null) return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("No data")));
        booking.setStatus("Cancelled");
        this.bookingRepository.save(booking);
        NotificationService.sendMessage(
                booking.getUser(),
                "BOOKING",
                "Your booking is Cancelled",
                "USER/PROVIDER",
                booking.getJobFocus().getJob().getProvider().getId()
        );
        this.messagingTemplate.convertAndSend("/queue/booking",new Event(true));
        return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> completeBooking(Long id,double price){
        Booking booking = this.bookingRepository.findById(id).orElse((null));
        if(booking==null) return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("No data")));
        booking.setStatus("Completed");
        booking.setFinalPrice(price);
        booking.setCompleteDate(LocalDateTime.now());
        this.bookingRepository.save(booking);
        NotificationService.sendMessage(
                booking.getUser(),
                "BOOKING",
                "Your booking is Completed",
                "USER",
                booking.getUser().getId()
        );
        this.messagingTemplate.convertAndSend("/queue/booking",new Event(true));
        this.messagingTemplate.convertAndSendToUser(booking.getUser().getId().toString(),"/queue/booking/completed",new Event(true));
        return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>>  getAvailableSlots(LocalDate workDate,Long jobFocusId){
       try{
           JobFocus jobFocus = this.jobFocusService.getById(jobFocusId);
           return this.timeOperationService.getTime(workDate,jobFocus.getJob().getProvider(),jobFocus);
       } catch (Exception e) {
           return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
       }
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> getAllBookingByProviderId(Long id,LocalDate date){
        List<BookingList> bookingLists = new ArrayList<>();
        if(date==null){
            this.bookingRepository.findByProviderId(id).forEach(
                    booking -> {
                        bookingLists.add(
                                new BookingList(
                                        booking.getId(),
                                        booking.getJobFocus().getSkill().getService().getName(),
                                        booking.getJobFocus().getSkill().getService().getImage(),
                                        booking.getJobFocus().getSkill().getName(),
                                        booking.getJobFocus().getSkill().getImage(),
                                        booking.getJobFocus().getPrice()+"-"+booking.getJobFocus().getPrice2(),
                                        booking.getUser().getName(),
                                        booking.getStatus(),
                                        new AddressDto(booking.getAddress().getLat(),booking.getAddress().getLon())
                                )
                        );
                    }
            );
            return CompletableFuture.completedFuture(ResponseEntity.ok(bookingLists));
        }
        this.bookingRepository.findByProviderIdAndBookingDate(id,date).forEach(
                booking -> {
                    bookingLists.add(
                            new BookingList(
                                    booking.getId(),
                                    booking.getJobFocus().getSkill().getService().getName(),
                                    booking.getJobFocus().getSkill().getService().getImage(),
                                    booking.getJobFocus().getSkill().getName(),
                                    booking.getJobFocus().getSkill().getImage(),
                                    booking.getJobFocus().getPrice()+"-"+booking.getJobFocus().getPrice2(),
                                    booking.getUser().getName(),
                                    booking.getStatus(),
                                    new AddressDto(booking.getAddress().getLat(),booking.getAddress().getLon())
                            )
                    );
                }
        );
        return CompletableFuture.completedFuture(ResponseEntity.ok(bookingLists));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> getAllRequestByProviderId(Long id,LocalDate date){
        List<BookingList> bookingLists = new ArrayList<>();
        if(date == null){
            this.bookingRepository.findByProviderIdAndRequest(id).forEach(
                    booking -> {
                        bookingLists.add(
                                new BookingList(
                                        booking.getId(),
                                        booking.getJobFocus().getSkill().getService().getName(),
                                        booking.getJobFocus().getSkill().getService().getImage(),
                                        booking.getJobFocus().getSkill().getName(),
                                        booking.getJobFocus().getSkill().getImage(),
                                        booking.getJobFocus().getPrice()+"-"+booking.getJobFocus().getPrice2(),
                                        booking.getUser().getName(),
                                        booking.getStatus(),
                                        new AddressDto(booking.getAddress().getLat(),booking.getAddress().getLon())
                                )
                        );
                    }
            );
            return CompletableFuture.completedFuture(ResponseEntity.ok(bookingLists));
        }
        this.bookingRepository.findByProviderIdAndStatusAndBookingDate(id,"Requested",date).forEach(
                booking -> {
                    bookingLists.add(
                            new BookingList(
                                    booking.getId(),
                                    booking.getJobFocus().getSkill().getService().getName(),
                                    booking.getJobFocus().getSkill().getService().getImage(),
                                    booking.getJobFocus().getSkill().getName(),
                                    booking.getJobFocus().getSkill().getImage(),
                                    booking.getJobFocus().getPrice()+"-"+booking.getJobFocus().getPrice2(),
                                    booking.getUser().getName(),
                                    booking.getStatus(),
                                    new AddressDto(booking.getAddress().getLat(),booking.getAddress().getLon())
                            )
                    );
                }
        );
        return CompletableFuture.completedFuture(ResponseEntity.ok(bookingLists));
    }


    @Async
    public CompletableFuture<ResponseEntity<?>> getAllUpcomingByProviderId(Long id,LocalDate date){
        List<BookingList> bookingLists = new ArrayList<>();
        if(date==null){
            this.bookingRepository.findByProviderIdAndStatus(id,"Upcoming").forEach(
                    booking -> {
                        bookingLists.add(
                                new BookingList(
                                        booking.getId(),
                                        booking.getJobFocus().getSkill().getService().getName(),
                                        booking.getJobFocus().getSkill().getService().getImage(),
                                        booking.getJobFocus().getSkill().getName(),
                                        booking.getJobFocus().getSkill().getImage(),
                                        booking.getJobFocus().getPrice()+"-"+booking.getJobFocus().getPrice2(),
                                        booking.getUser().getName(),
                                        booking.getStatus(),
                                        new AddressDto(booking.getAddress().getLat(),booking.getAddress().getLon())
                                )
                        );
                    }
            );
            return CompletableFuture.completedFuture(ResponseEntity.ok(bookingLists));
        }
        this.bookingRepository.findByProviderIdAndStatusAndWorkDate(id,"Upcoming",date).forEach(
                booking -> {
                    bookingLists.add(
                            new BookingList(
                                    booking.getId(),
                                    booking.getJobFocus().getSkill().getService().getName(),
                                    booking.getJobFocus().getSkill().getService().getImage(),
                                    booking.getJobFocus().getSkill().getName(),
                                    booking.getJobFocus().getSkill().getImage(),
                                    booking.getJobFocus().getPrice()+"-"+booking.getJobFocus().getPrice2(),
                                    booking.getUser().getName(),
                                    booking.getStatus(),
                                    new AddressDto(booking.getAddress().getLat(),booking.getAddress().getLon())
                            )
                    );
                }
        );
        return CompletableFuture.completedFuture(ResponseEntity.ok(bookingLists));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> getAllInProgress(Long id, Long cateId){
        List<BookingList> bookingLists = new ArrayList<>();
        List<ServiceDto> serviceDtoList = new ArrayList<>();
        this.jobRepository.findByProvider_Id(id).forEach(
                job -> {
                    serviceDtoList.add(
                            new ServiceDto(job.getService().getId(),job.getService().getName())
                    );
                }
        );

        if(cateId==null){
            this.bookingRepository.findByProviderIdAndStatus(id,"In Progress").forEach(
                    booking -> {
                        bookingLists.add(
                                new BookingList(
                                        booking.getId(),
                                        booking.getJobFocus().getSkill().getService().getName(),
                                        booking.getJobFocus().getSkill().getService().getImage(),
                                        booking.getJobFocus().getSkill().getName(),
                                        booking.getJobFocus().getSkill().getImage(),
                                        booking.getJobFocus().getPrice()+"-"+booking.getJobFocus().getPrice2(),
                                        booking.getUser().getName(),
                                        booking.getStatus(),
                                        new AddressDto(booking.getAddress().getLat(),booking.getAddress().getLon())
                                )
                        );
                    }
            );
            return CompletableFuture.completedFuture(ResponseEntity.ok(new InProgress(serviceDtoList,bookingLists)));
        }
        this.bookingRepository.findInProgressByProviderIdAndServiceId(id,cateId).forEach(
                booking -> {
                    bookingLists.add(
                            new BookingList(
                                    booking.getId(),
                                    booking.getJobFocus().getSkill().getService().getName(),
                                    booking.getJobFocus().getSkill().getService().getImage(),
                                    booking.getJobFocus().getSkill().getName(),
                                    booking.getJobFocus().getSkill().getImage(),
                                    booking.getJobFocus().getPrice()+"-"+booking.getJobFocus().getPrice2(),
                                    booking.getUser().getName(),
                                    booking.getStatus(),
                                    new AddressDto(booking.getAddress().getLat(),booking.getAddress().getLon())
                            )
                    );
                }
        );
        return CompletableFuture.completedFuture(ResponseEntity.ok(new InProgress(serviceDtoList,bookingLists)));
    }


    @Async
    public CompletableFuture<ResponseEntity<?>> getCancelledByProviderId(Long providerId){
        List<BookingList> bookingLists = new ArrayList<>();
        this.bookingRepository.findByProviderIdAndCancelled(providerId).forEach(
                booking -> {
                    bookingLists.add(
                            new BookingList(
                                    booking.getId(),
                                    booking.getJobFocus().getSkill().getService().getName(),
                                    booking.getJobFocus().getSkill().getService().getImage(),
                                    booking.getJobFocus().getSkill().getName(),
                                    booking.getJobFocus().getSkill().getImage(),
                                    booking.getJobFocus().getPrice()+"-"+booking.getJobFocus().getPrice2(),
                                    booking.getUser().getName(),
                                    booking.getStatus(),
                                    new AddressDto(booking.getAddress().getLat(),booking.getAddress().getLon())
                            )
                    );
                }
        );
        return CompletableFuture.completedFuture(ResponseEntity.ok(bookingLists));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> getCompleteByProviderId(Long providerId){
        List<BookingList> bookingLists = new ArrayList<>();
        this.bookingRepository.findByProviderIdAndComplete(providerId).forEach(
                booking -> {
                    bookingLists.add(
                            new BookingList(
                                    booking.getId(),
                                    booking.getJobFocus().getSkill().getService().getName(),
                                    booking.getJobFocus().getSkill().getService().getImage(),
                                    booking.getJobFocus().getSkill().getName(),
                                    booking.getJobFocus().getSkill().getImage(),
                                    booking.getJobFocus().getPrice()+"-"+booking.getJobFocus().getPrice2(),
                                    booking.getUser().getName(),
                                    booking.getStatus(),
                                    new AddressDto(booking.getAddress().getLat(),booking.getAddress().getLon())
                            )
                    );
                }
        );
        return CompletableFuture.completedFuture(ResponseEntity.ok(bookingLists));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> getBookingViewToProvider(Long id){
       try {
           Booking booking = this.bookingRepository.findById(id).orElse(new Booking());
           String confirm = null;
           String inprogress = null;
           String complete = null;
           if(booking.getConfirmDate()!=null) confirm =  booking.getConfirmDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a"));
           if(booking.getInProgressDate() !=null) inprogress =  booking.getInProgressDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a"));
           if(booking.getCompleteDate()!=null) complete =   booking.getCompleteDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a"));
           if(booking.getStatus().equals("Completed")){
               return CompletableFuture.completedFuture(ResponseEntity.ok(new BookingCompleteView(
                       booking.getId(),
                       new JobBookingView(
                               booking.getJobFocus().getSkill().getName(),
                               booking.getJobFocus().getPrice()+"-"+booking.getJobFocus().getPrice2(),
                               booking.getJobFocus().getSkill().getService().getName(),
                               booking.getJobFocus().getSkill().getImage(),
                               booking.getJobFocus().getDuration()+"H"
                       ),
                       new UserBookingView(
                               booking.getUser().getName(),
                               booking.getUser().getTel(),
                               booking.getUser().getProfile()
                       ),
                       new AddressDto(booking.getAddress().getLat(),booking.getAddress().getLon()),
                       booking.getWorkDate(),
                       booking.getWorkTime().format(DateTimeFormatter.ofPattern("hh:mm a")),
                       this.reviewService.getByBookingId(booking.getId()),
                       confirm,
                       inprogress,
                       complete,
                       booking.getBookingDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a"))
               )));
           }
           return CompletableFuture.completedFuture(ResponseEntity.ok(new BookingView(
                   booking.getId(),
                   new JobBookingView(
                           booking.getJobFocus().getSkill().getName(),
                           booking.getJobFocus().getPrice()+"-"+booking.getJobFocus().getPrice2(),
                           booking.getJobFocus().getSkill().getService().getName(),
                           booking.getJobFocus().getSkill().getImage(),
                           booking.getJobFocus().getDuration()+"H"
                   ),
                   new UserBookingView(
                           booking.getUser().getName(),
                           booking.getUser().getTel(),
                           booking.getUser().getProfile()
                   ),
                   new AddressDto(booking.getAddress().getLat(),booking.getAddress().getLon()),
                   booking.getWorkDate(),
                   booking.getWorkTime().format(DateTimeFormatter.ofPattern("hh:mm a")),
                   booking.getBookingDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a")),
                   confirm,
                   inprogress,
                   complete
           )));
       } catch (Exception e) {
          return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
       }
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> setStatus(Long bookingId,String status){
        Booking booking = this.bookingRepository.findById(bookingId).orElse(null);
        if(booking==null) return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("No data")));
        booking.setStatus(status);
        this.bookingRepository.save(booking);
        return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
    }

    // Get To Admin View
    @Async
    public CompletableFuture<ResponseEntity<?>> getToAdminLists(){
        List<BookingListView> listViews = new ArrayList<>();
        this.bookingRepository.findAll().forEach(
                booking -> {
                    listViews.add(
                            new BookingListView(
                                    booking.getId(),
                                    booking.getUser().getProfile(),
                                    booking.getUser().getName(),
                                    booking.getJobFocus().getJob().getProvider().getUser().getProfile(),
                                    booking.getJobFocus().getJob().getProvider().getBusinessName(),
                                    booking.getJobFocus().getSkill().getName(),
                                    booking.getStatus(),
                                    booking.getBookingDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a"))
                            )
                    );
                }
        );
        return CompletableFuture.completedFuture(ResponseEntity.ok(listViews));
    }

    // Get To Admin View
    @Async
    public CompletableFuture<ResponseEntity<?>> getToAdminView(Long id){
        Booking booking = this.bookingRepository.findBy_Id(id);
        if(booking == null) return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("No data")));
        Address address = this.addressService.getByUserIdAndStatus(booking.getUser().getId());
        double r = this.providerRepository.countRationByProviderId(booking.getJobFocus().getJob().getProvider().getId());
        long sr =0;
        double rate = 0;
        if( this.providerRepository.sumRationByProviderId(booking.getJobFocus().getJob().getProvider().getId())!=null) {
            rate = this.providerRepository.sumRationByProviderId(booking.getJobFocus().getJob().getProvider().getId())/r;
        }
        return CompletableFuture.completedFuture(ResponseEntity.ok(new BookingAdminView(
                new BookingInfo(
                        booking.getId(),
                        booking.getStatus(),
                        booking.getBookingDate().toLocalDate(),
                        booking.getBookingDate().toLocalTime().format(DateTimeFormatter.ofPattern("h:mm a"))
                ),
                new ServiceDetail(
                        booking.getJobFocus().getJob().getService().getName(),
                        booking.getJobFocus().getSkill().getName(),
                        booking.getWorkDate(),
                        booking.getWorkTime().format(DateTimeFormatter.ofPattern("h:mm a")),
                        booking.getJobFocus().getDuration()+"H",
                        booking.getJobFocus().getPrice()+"-"+booking.getJobFocus().getPrice2()
                ),
                new ProviderDto(
                        booking.getJobFocus().getJob().getProvider().getBusinessName(),
                        booking.getJobFocus().getJob().getProvider().getUser().getTel(),
                        booking.getJobFocus().getJob().getProvider().getEmail(),
                        new AddressDto( booking.getJobFocus().getJob().getProvider().getLat(), booking.getJobFocus().getJob().getProvider().getLon()),
                        booking.getJobFocus().getJob().getProvider().getUser().getProfile(),
                        rate
                ),
                new UserDto(
                        booking.getUser().getName(),
                        booking.getUser().getTel(),
                        new AddressDto(address.getLat(),address.getLon()),
                        booking.getUser().getProfile()
                )
        )));
    }

    // User
    @Async
    public CompletableFuture<ResponseEntity<?>> getAllBookingByUserId(Long userId){
        List<UserBooking> bookings = new ArrayList<>();
        this.bookingRepository.findByUser_Id(userId).forEach(
                booking ->{
                    bookings.add(
                            new UserBooking(
                                    booking.getId(),
                                    booking.getJobFocus().getSkill().getName(),
                                    booking.getJobFocus().getSkill().getImage(),
                                    booking.getJobFocus().getJob().getService().getName(),
                                    booking.getJobFocus().getJob().getProvider().getBusinessName(),
                                    booking.getJobFocus().getJob().getProvider().getProfile(),
                                    booking.getWorkDate(),
                                    booking.getWorkTime().format(DateTimeFormatter.ofPattern("h:mm a")),
                                    booking.getJobFocus().getPrice()+"-"+booking.getJobFocus().getPrice2(),
                                    (double) booking.getJobFocus().getJob().getProvider().getServiceRadius(),
                                    booking.getBookingDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a")),
                                    booking.getStatus()
                            )
                    );
                }
        );
        return CompletableFuture.completedFuture(ResponseEntity.ok(bookings));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> getAllBookingByUserIdAndStatus(Long userId,String status,String date){
        List<UserBooking> bookings = new ArrayList<>();
        this.bookingRepository.findByUser_IdAndStatus(userId,status,date).forEach(
                booking ->{
                    bookings.add(
                            new UserBooking(
                                    booking.getId(),
                                    booking.getJobFocus().getSkill().getName(),
                                    booking.getJobFocus().getSkill().getImage(),
                                    booking.getJobFocus().getJob().getService().getName(),
                                    booking.getJobFocus().getJob().getProvider().getBusinessName(),
                                    booking.getJobFocus().getJob().getProvider().getProfile(),
                                    booking.getWorkDate(),
                                    booking.getWorkTime().format(DateTimeFormatter.ofPattern("h:mm a")),
                                    booking.getJobFocus().getPrice()+"-"+booking.getJobFocus().getPrice2(),
                                    (double) booking.getJobFocus().getJob().getProvider().getServiceRadius(),
                                    booking.getBookingDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a")),
                                    booking.getStatus()
                            )
                    );
                }
        );
        return CompletableFuture.completedFuture(ResponseEntity.ok(bookings));
    }

    // User Booking Review
    @Async
    public CompletableFuture<ResponseEntity<?>> getUserBookingView(Long bookingId) throws ExecutionException, InterruptedException {
        Booking booking = this.bookingRepository.findById(bookingId).orElse(null);
        if(booking == null) return null;
        double totalRation = 0;
        long sumRation = 0;
        double rate = 0;
        String confirm = null;
        String inprogress = null;
        String complete = null;
        if(booking.getConfirmDate()!=null) confirm =  booking.getConfirmDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a"));
        if(booking.getInProgressDate() !=null) inprogress =  booking.getInProgressDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a"));
        if(booking.getCompleteDate()!=null) complete =   booking.getCompleteDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a"));
        if(this.providerRepository.countRationByProviderId(booking.getJobFocus().getJob().getProvider().getId())!=null) totalRation =this.providerRepository.countRationByProviderId(booking.getJobFocus().getJob().getProvider().getId());
        if(this.providerRepository.sumRationByProviderId(booking.getJobFocus().getJob().getProvider().getId())!=null) {
            sumRation = this.providerRepository.sumRationByProviderId(booking.getJobFocus().getJob().getProvider().getId());
            rate = sumRation/totalRation;
        }
        if(booking.getStatus().equals("Completed")){
            return CompletableFuture.completedFuture(ResponseEntity.ok(new UserCompleteBookingView(
                    booking.getId(),
                    booking.getBookingDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a")),
                    confirm,
                    inprogress,
                    new ServiceDetailUserBookingView(
                            booking.getJobFocus().getSkill().getName(),
                            booking.getJobFocus().getSkill().getImage(),
                            booking.getJobFocus().getJob().getService().getName(),
                            booking.getJobFocus().getDuration(),
                            booking.getJobFocus().getPrice()+"-"+booking.getJobFocus().getPrice2(),
                            booking.getJobFocus().getDescription()
                    ),
                    new ProviderBookingUserView(
                            booking.getJobFocus().getJob().getProvider().getBusinessName(),
                            booking.getJobFocus().getJob().getProvider().getProfile(),
                            rate,
                            (long)totalRation,
                            booking.getJobFocus().getJob().getProvider().getUser().getTel()
                    ),
                    booking.getWorkDate(),
                    booking.getWorkTime().format(DateTimeFormatter.ofPattern("h:mm a")),
                    new AddressDto(booking.getAddress().getLat(),booking.getAddress().getLon()),
                    this.reviewService.getByBookingId(booking.getId()),
                    complete
            )));
        }
        return CompletableFuture.completedFuture(ResponseEntity.ok(new UserViewBooking(
                booking.getId(),
                booking.getBookingDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a")),
                confirm,
                inprogress,
                new ServiceDetailUserBookingView(
                        booking.getJobFocus().getSkill().getName(),
                        booking.getJobFocus().getSkill().getImage(),
                        booking.getJobFocus().getJob().getService().getName(),
                        booking.getJobFocus().getDuration(),
                        booking.getJobFocus().getPrice()+"-"+booking.getJobFocus().getPrice2(),
                        booking.getJobFocus().getDescription()
                ),
                new ProviderBookingUserView(
                        booking.getJobFocus().getJob().getProvider().getBusinessName(),
                        booking.getJobFocus().getJob().getProvider().getProfile(),
                        rate,
                        (long)totalRation,
                        booking.getJobFocus().getJob().getProvider().getUser().getTel()
                ),
                booking.getWorkDate(),
                booking.getWorkTime().format(DateTimeFormatter.ofPattern("h:mm a")),
                new AddressDto(booking.getAddress().getLat(),booking.getAddress().getLon()),
                complete
        )));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> bookingReport(){
        try{
            List<BookingReportList> bookingReportLists = new ArrayList<>();
            this.bookingRepository.findAll().forEach(
                    booking -> {
                        float total= 0;
                        String complete = null;
                        if(booking.getCompleteDate()!=null) complete=booking.getCompleteDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a"));
                        if(booking.getFinalPrice()!=0) total =(float) booking.getFinalPrice();
                        bookingReportLists.add(
                                new BookingReportList(
                                        booking.getId(),
                                        booking.getUser().getName(),
                                        booking.getJobFocus().getJob().getProvider().getBusinessName(),
                                        booking.getJobFocus().getSkill().getName(),
                                        booking.getStatus(),
                                        booking.getBookingDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a")),
                                        complete,
                                        total
                                )
                        );

                    }
            );
            return CompletableFuture.completedFuture(ResponseEntity.ok(
                    new BookingReport(
                            this.bookingRepository.getBookingPerMonth(LocalDate.now().getYear()),
                            bookingReportLists,
                            new BookingStatusCount(
                                    this.bookingRepository.countByStatus("Requested"),
                                    this.bookingRepository.countByStatus("Upcoming"),
                                    this.bookingRepository.countByStatus("In Progress"),
                                    this.bookingRepository.countByStatus("Completed"),
                                    this.bookingRepository.countByStatus("Cancelled")
                            )
                    )
            ));
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }
}

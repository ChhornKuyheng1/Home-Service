package com.example.Backend.Services;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.example.Backend.Components.WebSocketUserRegistry;
import com.example.Backend.DTOS.AddressDto;
import com.example.Backend.DTOS.AdminDashboards.ProviderReports.ProviderListReport;
import com.example.Backend.DTOS.AdminDashboards.ProviderReports.ProviderReport;
import com.example.Backend.DTOS.AdminDashboards.ProviderReports.ProviderStatusCount;
import com.example.Backend.DTOS.Contact;
import com.example.Backend.DTOS.JobFocus.Mobiles.TopSkill;
import com.example.Backend.DTOS.MobileProviders.Insights.Insights;
import com.example.Backend.DTOS.MobileProviders.Insights.TopServiceInsights;
import com.example.Backend.DTOS.MobileProviders.Profiles.Personal;
import com.example.Backend.DTOS.MobileProviders.Profiles.Profile;
import com.example.Backend.DTOS.MobileProviders.ProviderViewDetails.Abouts.About;
import com.example.Backend.DTOS.MobileProviders.ProviderViewDetails.Abouts.WorkingInfo;
import com.example.Backend.DTOS.MobileProviders.RevenueAnalytics.Revenue;
import com.example.Backend.DTOS.MobileProviders.RevenueAnalytics.RevenueBreakdown;
import com.example.Backend.DTOS.MobileProviders.RevenueAnalytics.TopServiceRevenue;
import com.example.Backend.DTOS.MobileProviders.RevenueAnalytics.TotalRevenue;
import com.example.Backend.DTOS.MobileProviders.Services.ProviderJob;
import com.example.Backend.DTOS.MobileProviders.Services.ProviderJobFocus;
import com.example.Backend.DTOS.MobileProviders.Services.ProviderServices;
import com.example.Backend.DTOS.MobileProviders.planViews.BillingHistory;
import com.example.Backend.DTOS.MobileProviders.planViews.Plan;
import com.example.Backend.DTOS.MobileProviders.planViews.PlanBenefits;
import com.example.Backend.DTOS.MobileProviders.planViews.PlanView;
import com.example.Backend.DTOS.Providers.HomeProfile;
import com.example.Backend.DTOS.Providers.ProviderAnalytics;
import com.example.Backend.DTOS.Providers.ProviderApproveDto;
import com.example.Backend.DTOS.Providers.ProviderDetail;
import com.example.Backend.DTOS.Providers.ProviderInformation;
import com.example.Backend.DTOS.Providers.ProviderListView;
import com.example.Backend.DTOS.Providers.ProviderNear;
import com.example.Backend.DTOS.Providers.ProviderNearView;
import com.example.Backend.DTOS.Providers.ProviderPending;
import com.example.Backend.DTOS.Providers.ProviderView;
import com.example.Backend.DTOS.Providers.ProviderViewProfile;
import com.example.Backend.DTOS.Providers.ServiceInformation;
import com.example.Backend.Events.Event;
import com.example.Backend.Models.Address;
import com.example.Backend.Models.JobFocus;
import com.example.Backend.Models.Provider;
import com.example.Backend.Models.Subscription;
import com.example.Backend.Models.User;
import com.example.Backend.Repositories.AddressRepository;
import com.example.Backend.Repositories.BookingRepository;
import com.example.Backend.Repositories.JobFocusRepository;
import com.example.Backend.Repositories.JobRepository;
import com.example.Backend.Repositories.ProviderRepository;
import com.example.Backend.Repositories.SubscriptionRepository;
import com.example.Backend.Repositories.UserRepository;
import com.example.Backend.Requests.PersonalRequest;
import com.example.Backend.Responses.Messages.Message;
import com.example.Backend.Responses.Users.UserResponse;
import com.example.Backend.Utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class ProviderService {
    private static final Logger log = LoggerFactory.getLogger(ProviderService.class);
    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private JobFocusRepository jobFocusRepository;

    @Autowired
    private WebSocketUserRegistry registry;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private NotificationService NotificationService;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    private final ZoneId cambodiaZone = ZoneId.of("Asia/Phnom_Penh");

    @Async
    public CompletableFuture<ResponseEntity<?>> getByUserId(Long userId) {
        if (this.providerRepository.findByUser_Id(userId).isPresent()) {
            Provider provider = this.providerRepository.findByUser_Id(userId).get();
            if (provider.getJobStatus().equals("Pending"))
                return CompletableFuture.completedFuture(ResponseEntity.status(409).body(new Message("This account is in Pending")));
            if (provider.getJobStatus().equals("Rejected"))
                return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("No account")));
            return CompletableFuture.completedFuture(ResponseEntity.ok(new UserResponse(provider.getId(), provider.getUser().getToken())));
        }
        return CompletableFuture.completedFuture(ResponseEntity.status(404).body("No Account"));
    }

    public ResponseEntity<?> setp(Long id) {
        Provider provider = this.providerRepository.findById(id).orElse(null);
        if (provider == null) return ResponseEntity.status(404).body("no data");
        provider.setJobStatus("Pending");
        this.providerRepository.save(provider);
        return ResponseEntity.ok("Successfully");
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> setProfile(Long providerId, MultipartFile file) {
        try {
            Provider provider = this.providerRepository.findById(providerId).orElse(null);
            if (provider == null)
                return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("No data")));
            if (file == null) {
                this.providerRepository.save(provider);
                return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
            }
            if (provider.getProfile() != null)
                cloudinary.uploader().destroy(getPublicIdFromUrl(provider.getProfile()), ObjectUtils.emptyMap());
            provider.setProfile(cloudinary.url().transformation(new Transformation()
                            .width(800)
                            .height(800)
                            .crop("fill")
                            .gravity("auto")
                            .fetchFormat("auto")
                            .quality("auto"))
                    .generate(cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("public_id").toString())
            );
            this.providerRepository.save(provider);
            return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> setStatus(Long providerId, String status) {
        try {
            Provider provider = this.providerRepository.findById(providerId).orElse(null);

            if (provider == null)
                return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("No data")));

            Subscription subscription = this.subscriptionRepository.findByProvider_Id(provider.getId());

            if (provider.getJobStatus().equals("Block"))
                return CompletableFuture.completedFuture(ResponseEntity.status(403).body(new Message("Your account is block")));

            if (!provider.getJobStatus().equals("Approved"))
                return CompletableFuture.completedFuture(ResponseEntity.status(403).body(new Message("Your account is not approved")));

            if (subscription == null || !subscription.getStatus().equals("Active"))
                return CompletableFuture.completedFuture(ResponseEntity.status(403).body(new Message("Please Subscription")));

            provider.setStatus(status);
            this.providerRepository.save(provider);
            return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> add(Provider provider, long userId) {
        User user = this.userService.getById(userId);
        if (user == null)
            return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("No user data")));
        Provider providerExistion = this.providerRepository.findByUser_Id(userId).orElse(null);
        if (providerExistion == null) {
            provider.setCreateDate(LocalDateTime.now(cambodiaZone));
            provider.setUser(user);
            provider.setJobStatus("Pending");
            provider.setStatus("Inactive");
            Provider save = this.providerRepository.save(provider);
            String token = JwtUtil.generateToken(user.getId().toString(), "USER");
            user.setToken(token);
            this.userRepository.save(user);
            this.messagingTemplate.convertAndSend("/queue/provider", new Event(true));
            this.messagingTemplate.convertAndSend("/queue/dashboard", new Event(true));
            return CompletableFuture.completedFuture(ResponseEntity.ok(new UserResponse(save.getUser().getId(), token)));
        }
        providerExistion.setUser(user);
        providerExistion.setBusinessName(provider.getBusinessName());
        providerExistion.setStartDate(provider.getStartDate());
        providerExistion.setEndDate(provider.getEndDate());
        providerExistion.setStartTime(provider.getStartTime());
        providerExistion.setEndTime(provider.getEndTime());
        providerExistion.setJobStatus("Pending");
        providerExistion.setStatus("Inactive");
        providerExistion.setEmail(provider.getEmail());
        String token = JwtUtil.generateToken(user.getId().toString(), "USER");
        user.setToken(token);
        Provider save = this.providerRepository.save(providerExistion);
        this.messagingTemplate.convertAndSend("/queue/provider", new Event(true));
        this.messagingTemplate.convertAndSend("/queue/dashboard", new Event(true));
        return CompletableFuture.completedFuture(ResponseEntity.ok(new UserResponse(save.getUser().getId(), token)));
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
    public CompletableFuture<ResponseEntity<?>> setDocument(long providerId, MultipartFile id, MultipartFile profile) throws Exception {
        Provider provider = this.providerRepository.findById(providerId).orElse(null);
        if (provider == null)
            return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("No data")));
        if (provider.getIdCard() != null)
            cloudinary.uploader().destroy(getPublicIdFromUrl(provider.getIdCard()), ObjectUtils.emptyMap());
        if (provider.getProfile() != null)
            cloudinary.uploader().destroy(getPublicIdFromUrl(provider.getProfile()), ObjectUtils.emptyMap());
        provider.setIdCard(cloudinary.url().transformation(new Transformation()
                        .width(800)
                        .height(800)
                        .crop("scale")
                        .gravity("auto")
                        .fetchFormat("auto")
                        .quality("auto"))
                .generate(cloudinary.uploader().upload(id.getBytes(), ObjectUtils.emptyMap()).get("public_id").toString())
        );
        provider.setProfile(cloudinary.url().transformation(new Transformation()
                        .width(800)
                        .height(800)
                        .crop("scale")
                        .gravity("auto")
                        .fetchFormat("auto")
                        .quality("auto"))
                .generate(cloudinary.uploader().upload(profile.getBytes(), ObjectUtils.emptyMap()).get("public_id").toString())
        );
        this.providerRepository.save(provider);
        return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
    }

    private CompletableFuture<ResponseEntity<?>> existionProvider(Provider provider, MultipartFile idCard, MultipartFile profile, Provider providerExistion, User user) {
        try {
            providerExistion.setUser(user);
            providerExistion.setCreateDate(LocalDateTime.now());
            providerExistion.setBusinessName(provider.getBusinessName());
            providerExistion.setStartDate(provider.getStartDate());
            providerExistion.setEndDate(provider.getEndDate());
            providerExistion.setStartTime(provider.getStartTime());
            providerExistion.setEndTime(provider.getEndTime());
            providerExistion.setJobStatus("Pending");
            providerExistion.setStatus("Inactive");
            providerExistion.setIdCard(
                    this.cloudinary.url().transformation(new Transformation()
                            .crop("fit")
                            .width(1000)
                            .height(1000)
                    ).generate(cloudinary.uploader().upload(idCard.getBytes(), ObjectUtils.emptyMap()).get("public_id").toString())
            );
            providerExistion.setProfile(
                    this.cloudinary.url().transformation(new Transformation()
                            .crop("fit")
                            .width(1000)
                            .height(1000)
                    ).generate(cloudinary.uploader().upload(profile.getBytes(), ObjectUtils.emptyMap()).get("public_id").toString())
            );
            providerExistion.setEmail(provider.getEmail());
            String token = JwtUtil.generateToken(user.getId().toString(), "USER");
            user.setToken(token);
            Provider save = this.providerRepository.save(providerExistion);
            this.messagingTemplate.convertAndSend("/queue/provider", new Event(true));
            this.messagingTemplate.convertAndSend("/queue/dashboard", new Event(true));
            return CompletableFuture.completedFuture(ResponseEntity.ok(new UserResponse(save.getUser().getId(), token)));
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> checkEmail(String email, Long userId) {
        try {
            if (this.providerRepository.findByEmailAndUserId(email, userId).isPresent())
                return CompletableFuture.completedFuture(ResponseEntity.status(409).body(""));

            return CompletableFuture.completedFuture(ResponseEntity.status(200).body(""));
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> addProvider(Provider provider, Long userId, MultipartFile idCard, MultipartFile profile) {
        try {

            User user = this.userRepository.findById(userId).orElseThrow();

            Optional<Provider> existion = this.providerRepository.findById(user.getId());

            if (existion.isPresent()) {
                return this.existionProvider(provider, idCard, profile, existion.get(), user);
            }

            provider.setCreateDate(LocalDateTime.now(cambodiaZone));

            provider.setUser(user);

            provider.setJobStatus("Pending");

            provider.setStatus("Inactive");

            provider.setIdCard(this.cloudinary
                    .url().transformation(new Transformation()
                            .crop("fit")
                            .width(1000)
                            .height(1000)
                    ).generate(cloudinary.uploader().upload(idCard.getBytes(), ObjectUtils.emptyMap()).get("public_id").toString())
            );

            provider.setProfile(this.cloudinary
                    .url().transformation(new Transformation()
                            .crop("fit")
                            .width(1000)
                            .height(1000)
                    ).generate(cloudinary.uploader().upload(profile.getBytes(), ObjectUtils.emptyMap()).get("public_id").toString())
            );

            Provider save = this.providerRepository.save(provider);

            String token = JwtUtil.generateToken(user.getId().toString(), "USER");

            user.setToken(token);

            this.userRepository.save(user);

            this.messagingTemplate.convertAndSend("/queue/provider", new Event(true));

            this.messagingTemplate.convertAndSend("/queue/dashboard", new Event(true));

            return CompletableFuture.completedFuture(ResponseEntity.ok(new UserResponse(save.getUser().getId(), token)));

        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> setAddressWork(long providerId, double lat, double lon, int serviceRadius) {
        Provider provider = this.providerRepository.findById(providerId).orElse(null);
        if (provider == null)
            return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("No data")));
        provider.setLat(lat);
        provider.setLon(lon);
        provider.setServiceRadius(serviceRadius);
        this.providerRepository.save(provider);
        return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> editeTel(Long id, String tel) {
        Provider provider = this.providerRepository.findById(id).orElse(null);
        if (provider == null)
            return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("No data")));
        provider.getUser().setTel(tel);
        User user = provider.getUser();
        return CompletableFuture.completedFuture(this.userService.editeTel(user));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> editeEmail(Long id, String email) {
        Provider provider = this.providerRepository.findById(id).orElse(null);
        if (provider == null)
            return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("No data")));
        provider.setEmail(email);
        this.providerRepository.save(provider);
        return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> setJobStatus(Long id, String jobStatus) {
        Provider provider = this.providerRepository.findById(id).orElse(null);
        if (provider == null)
            return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("No data")));
        provider.setJobStatus(jobStatus);
        this.providerRepository.save(provider);
        return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> approvedProvider(Long providerId) {
        Provider provider = this.providerRepository.findById(providerId).orElse(null);
        if (provider == null)
            return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("No data")));
        provider.setJobStatus("Approved");
        this.providerRepository.save(provider);
        if (this.NotificationService.getNotificationByUserIdAndType(providerId, "REJECTED") != null) {
            this.NotificationService.delete(this.NotificationService.getNotificationByUserIdAndType(providerId, "REJECTED"));
        }
        this.messagingTemplate.convertAndSendToUser(
                provider.getId().toString(),
                "/queue/approved/provider",
                new Event(true)
        );
        this.NotificationService.sendMessage(
                provider.getUser(),
                "Register",
                "Your account is approved",
                "APPROVED",
                provider.getId()

        );
        this.messagingTemplate.convertAndSend("/queue/provider", new Event(true));
        this.messagingTemplate.convertAndSend("/queue/dashboard", new Event(true));
        return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> rejectedProvider(Long id, String response) {
        try {
            Provider provider = this.providerRepository.findById(id).orElse(new Provider());
            provider.setJobStatus("Rejected");
            if (response.equals("") || response == null) response = "Rejected";
            if (this.NotificationService.getNotificationByUserIdAndType(id, "REJECTED") != null) {
                this.NotificationService.delete(this.NotificationService.getNotificationByUserIdAndType(id, "REJECTED"));
            }
            this.providerRepository.save(provider);
            this.messagingTemplate.convertAndSendToUser(
                    provider.getId().toString(),
                    "/queue/approved/provider",
                    new Event(false)
            );
            this.NotificationService.sendMessage(
                    provider.getUser(),
                    "Register",
                    response,
                    "REJECTED",
                    provider.getId()
            );
            this.messagingTemplate.convertAndSend("/queue/provider", new Event(true));
            this.messagingTemplate.convertAndSend("/queue/dashboard", new Event(true));
            return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    public Provider getById(long id) {
        return this.providerRepository.findById(id).orElse(null);
    }



    //Revenue Analytics
    @Async
    public CompletableFuture<ResponseEntity<?>> revenueAnalyticsAllDay(Long id) {
        double total = 0.0;
        double maxPrice = 0;
        double avg = 0;
        if (this.providerRepository.totalRevenueAll(id)!=null) {
            total = this.providerRepository.totalRevenueAll(id);
            avg = (total / this.providerRepository.countAllBookingByProviderId(id));
        }
        if (this.providerRepository.maxPriceAll(id) != null) maxPrice = this.providerRepository.maxPriceAll(id);

        List<TopServiceRevenue> topService = new ArrayList<>();

        List<RevenueBreakdown> breakdowns = new ArrayList<>();

        this.jobRepository.findByProvider_Id(id).forEach(
                job -> {
                    double totalPrice = 0;
                    if (this.providerRepository.totalPriceBookingByServiceAllDay(id, job.getService().getId()) != null) totalPrice = this.providerRepository.totalPriceBookingByServiceAllDay(id, job.getService().getId());
                    if (this.providerRepository.countBookingByServiceAllDay(id, job.getService().getId()) != 0) {
                        topService.add(
                                new TopServiceRevenue(
                                        job.getService().getName(),
                                        this.providerRepository.countBookingByServiceAllDay(id, job.getService().getId()),
                                        totalPrice
                                )
                        );
                        double a = this.providerRepository.countBookingByServiceAllDay(id, job.getService().getId());
                        double b = this.providerRepository.countAllCompletedBooking(id);
                        double v = (a / b) * 100;
                        breakdowns.add(
                                new RevenueBreakdown(
                                        job.getService().getName(),
                                        (int) v
                                ));
                    }
                }
        );
        return CompletableFuture.completedFuture(ResponseEntity.ok(new Revenue(
                new TotalRevenue(total, avg, maxPrice),
                topService,
                breakdowns
        )));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> revenueAnalytics1Day(Long id) {
        double total = 0.0;
        double maxPrice = 0;
        double avg = 0;
        if (this.providerRepository.totalRevenue1Day(id, LocalDate.now()) != null) {
            total = this.providerRepository.totalRevenue1Day(id, LocalDate.now());
            avg = total / this.providerRepository.count1Day(id, LocalDate.now());
        }
        if (this.providerRepository.maxPrice1Day(id, LocalDate.now()) != null)
            maxPrice = this.providerRepository.maxPrice1Day(id, LocalDate.now());
        List<TopServiceRevenue> topService = new ArrayList<>();
        List<RevenueBreakdown> breakdowns = new ArrayList<>();
        this.jobRepository.findByProvider_Id(id).forEach(
                job -> {
                    double totalPrice = 0;
                    if (this.providerRepository.totalPriceBookingByService1Day(id, job.getService().getId(), LocalDate.now()) != null) {
                        totalPrice = this.providerRepository.totalPriceBookingByService1Day(id, job.getService().getId(), LocalDate.now());
                    }
                    if (this.providerRepository.countBookingByService1Day(id, job.getService().getId(), LocalDate.now()) != 0) {
                        topService.add(
                                new TopServiceRevenue(
                                        job.getService().getName(),
                                        this.providerRepository.countBookingByService1Day(id, job.getService().getId(), LocalDate.now()),
                                        totalPrice
                                )
                        );
                        double a = this.providerRepository.countBookingByService1Day(id, job.getService().getId(), LocalDate.now());
                        double b = this.providerRepository.count1Day(id,LocalDate.now());
                        double v = (a / b) * 100;
                        breakdowns.add(
                                new RevenueBreakdown(
                                        job.getService().getName(),
                                        (int) v
                                ));
                    }
                }
        );
        return CompletableFuture.completedFuture(ResponseEntity.ok(new Revenue(
                new TotalRevenue(total, avg, maxPrice),
                topService,
                breakdowns
        )));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> revenueAnalytics7Day(Long id) {
        double total = 0;
        double maxPrice = 0;
        double avg = 0;
        if (this.providerRepository.totalRevenue7Day(id, LocalDate.now(), LocalDate.now().minusDays(7), LocalDate.now().getMonthValue()) != null) {
            total = this.providerRepository.totalRevenue7Day(id, LocalDate.now(), LocalDate.now().minusDays(7), LocalDate.now().getMonthValue());
            avg = total / this.providerRepository.count7Day(id, LocalDate.now(), LocalDate.now().minusDays(7), LocalDate.now().getMonthValue());
        }
        if (this.providerRepository.maxPrice7Day(id, LocalDate.now(), LocalDate.now().minusDays(7), LocalDate.now().getMonthValue()) != null)
            maxPrice = this.providerRepository.maxPrice7Day(id, LocalDate.now(), LocalDate.now().minusDays(7), LocalDate.now().getMonthValue());
        List<TopServiceRevenue> topService = new ArrayList<>();
        List<RevenueBreakdown> breakdowns = new ArrayList<>();
        this.jobRepository.findByProvider_Id(id).forEach(
                job -> {
                    double totalPrice = 0;
                    if (this.providerRepository.totalPriceBookingByService7Day(id, job.getService().getId(), LocalDate.now(), LocalDate.now().minusDays(7), LocalDate.now().getMonthValue()) != null)
                        totalPrice = this.providerRepository.totalPriceBookingByService7Day(id, job.getService().getId(), LocalDate.now(), LocalDate.now().minusDays(7), LocalDate.now().getMonthValue());
                    if (this.providerRepository.countBookingByService7Day(id, job.getService().getId(), LocalDate.now(), LocalDate.now().minusDays(7), LocalDate.now().getMonthValue()) != 0) {
                        topService.add(
                                new TopServiceRevenue(
                                        job.getService().getName(),
                                        this.providerRepository.countBookingByService7Day(id, job.getService().getId(), LocalDate.now(), LocalDate.now().minusDays(7), LocalDate.now().getMonthValue()),
                                        totalPrice
                                )
                        );
                        double a = this.providerRepository.countBookingByService7Day(id, job.getService().getId(), LocalDate.now(), LocalDate.now().minusDays(7), LocalDate.now().getMonthValue());
                        double b = this.providerRepository.count7Day(id,LocalDate.now(), LocalDate.now().minusDays(7), LocalDate.now().getMonthValue());
                        double v = (a / b)*100;
                        breakdowns.add(
                                new RevenueBreakdown(
                                        job.getService().getName(),
                                        (int) v
                                ));
                    }
                }
        );
        return CompletableFuture.completedFuture(ResponseEntity.ok(new Revenue(
                new TotalRevenue(total, avg, maxPrice),
                topService,
                breakdowns

        )));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> revenueAnalytics1Month(Long id) {
        double total = 0;
        double maxPrice = 0;
        double avg = 0;
        if (this.providerRepository.totalRevenue1Month(id, LocalDate.now().getMonthValue()) != null) {
            total = this.providerRepository.totalRevenue1Month(id, LocalDate.now().getMonthValue());
            avg = total / this.providerRepository.count1Month(id, LocalDate.now().getMonthValue());
        }
        if (this.providerRepository.maxPrice1Month(id, LocalDate.now().getMonthValue()) != null)
            maxPrice = this.providerRepository.maxPrice1Month(id, LocalDate.now().getMonthValue());
        List<TopServiceRevenue> topService = new ArrayList<>();
        List<RevenueBreakdown> breakdowns = new ArrayList<>();
        this.jobRepository.findByProvider_Id(id).forEach(
                job -> {
                    double totalPrice = 0;
                    if (this.providerRepository.totalPriceBookingByService1Month(id, job.getService().getId(), LocalDate.now().getMonthValue()) != null)
                        totalPrice = this.providerRepository.totalPriceBookingByService1Month(id, job.getService().getId(), LocalDate.now().getMonthValue());
                    if (this.providerRepository.countBookingByService1Month(id, job.getService().getId(), LocalDate.now().getMonthValue()) != 0) {
                        topService.add(
                                new TopServiceRevenue(
                                        job.getService().getName(),
                                        this.providerRepository.countBookingByService1Month(id, job.getService().getId(), LocalDate.now().getMonthValue()),
                                        totalPrice
                                )
                        );
                        double a = this.providerRepository.countBookingByService1Month(id, job.getService().getId(), LocalDate.now().getMonthValue());
                        double b = this.providerRepository.count1Month(id,LocalDate.now().getMonthValue());
                        double v = (a / b) * 100 ;
                        breakdowns.add(
                                new RevenueBreakdown(
                                        job.getService().getName(),
                                        (int) v
                                ));
                    }
                }
        );
        return CompletableFuture.completedFuture(ResponseEntity.ok(new Revenue(
                new TotalRevenue(total, avg, maxPrice),
                topService,
                breakdowns
        )));
    }

    // Insights
    @Async
    public CompletableFuture<ResponseEntity<?>> insights(Long id) {
        double totalJob = this.providerRepository.countAllCompletedBooking(id);
        double totalBooking = 0;
        double avg = 0;
        if (this.providerRepository.findAvg(id) != null) avg = this.providerRepository.findAvg(id);
        double jobActive = this.providerRepository.totalJobFocusActive(id);
        Long totalJobFocus = this.providerRepository.totalJobFocus(id);
        List<TopServiceInsights> topService = new ArrayList<>();
        double v = 0;
        if (this.providerRepository.countAllCompletedBooking(id) != null) v = totalJob / totalBooking * 100;
        double f = jobActive / totalJobFocus * 100;
        double totalRate = 0;
        if (this.providerRepository.countRationByProviderId(id) != null) totalRate = this.providerRepository.countRationByProviderId(id);
        long sumRate = 0;
        double rate = 0;
        if (this.providerRepository.sumRationByProviderId(id) != null) {
            sumRate = this.providerRepository.sumRationByProviderId(id);
            rate = sumRate / totalRate;
        }
        this.jobRepository.findByProvider_Id(id).forEach(
                job -> {
                    Long total = this.providerRepository.countBookingByService(id, job.getService().getId());
                    if (total != 0) {
                        topService.add(
                                new TopServiceInsights(job.getService().getName(), total)
                        );
                    }
                }
        );

        return CompletableFuture.completedFuture(ResponseEntity.ok(new Insights(
                (long) totalJob,
                avg,
                rate,
                (int) v,
                (int) jobActive,
                (int) f,
                topService,
                (long) totalRate,
                this.providerRepository.countAllBookingByProviderId(id)
        )));
    }

    // Subscription View
    @Async
    public CompletableFuture<ResponseEntity<?>> getSubscription(Long id) {
        Subscription subscription = this.providerRepository.findSubscriptionByProviderId(id);
        List<BillingHistory> billingHistories = new ArrayList<>();
        if (subscription == null) return CompletableFuture.completedFuture(ResponseEntity.ok(null));
        this.providerRepository.findTransactionByProviderId(id).forEach(
                transaction -> {
                    billingHistories.add(
                            new BillingHistory(
                                    transaction.getPlan().getName(),
                                    transaction.getPayDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a")),
                                    transaction.getStatus(),
                                    transaction.getTotal()
                            )
                    );
                }
        );
        return CompletableFuture.completedFuture(ResponseEntity.ok(new PlanView(
                new Plan(
                        subscription.getPlan().getName(),
                        subscription.getPlan().getDuration(),
                        subscription.getStatus(),
                        subscription.getStartDate(),
                        subscription.getEndDate(),
                        subscription.getPlan().getPrice(),
                        subscription.getPlan().getImage()
                ),
                new PlanBenefits(subscription.getPlan().getMaxService(), subscription.getPlan().getMaxSkill()),
                billingHistories
        )));
    }

    // Get To Admin List View
    @Async
    public CompletableFuture<ResponseEntity<?>> getToAdminListView() {
        List<ProviderListView> lists = new ArrayList<>();
        this.providerRepository.findAll().forEach(
                provider -> {
                    lists.add(
                            new ProviderListView(
                                    provider.getId(),
                                    provider.getBusinessName(),
                                    this.jobRepository.findServiceByProviderId(provider.getId()),
                                    provider.getEmail(),
                                    provider.getStatus(),
                                    provider.getJobStatus(),
                                    provider.getUser().getTel(),
                                    provider.getUser().getProfile()
                            )
                    );
                }
        );
        return CompletableFuture.completedFuture(ResponseEntity.ok(lists));
    }

    // Get To Admin View
    @Async
    public CompletableFuture<ResponseEntity<?>> getToAdminView(Long id) {
        Provider provider = this.providerRepository.findById(id).orElse(new Provider());
        List<String> image = Arrays.asList(provider.getIdCard(), provider.getProfile());
        Long totalJob = this.providerRepository.countAllBookingByProviderId(id);
        long complete = this.providerRepository.countAllCompletedBooking(id);
        double completeJob = 0.0;
        double totalRation = 0;
        long sumRation = 0;
        double rate = 0;
        if (this.providerRepository.countAllCompletedBooking(provider.getId()) > 0) {
            completeJob = (double) complete / totalJob * 100;
        }
        if (this.providerRepository.countRationByProviderId(id) != null)
            totalRation = this.providerRepository.countRationByProviderId(id);
        if (this.providerRepository.sumRationByProviderId(id) != null) {
            sumRation = this.providerRepository.sumRationByProviderId(id);
            rate = sumRation / totalRation;
        }
        return CompletableFuture.completedFuture(ResponseEntity.ok(new ProviderView(
                new ProviderDetail(
                        provider.getBusinessName(),
                        provider.getStatus(),
                        provider.getJobStatus(),
                        provider.getCreateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a")),
                        provider.getUser().getProfile()
                ),
                new ProviderAnalytics(
                        totalJob,
                        complete,
                        completeJob,
                        rate
                ),
                new ProviderInformation(
                        provider.getUser().getName(),
                        provider.getUser().getGender(),
                        LocalDate.now().getYear() - provider.getUser().getDob().getYear(),
                        provider.getUser().getTel(),
                        provider.getEmail(),
                        provider.getUser().getDob(),
                        new AddressDto(provider.getLat(), provider.getLon()),
                        image
                ),
                new ServiceInformation(
                        this.jobRepository.findServiceByProviderId(provider.getId()),
                        provider.getServiceRadius(),
                        this.providerRepository.findJobFocusByProviderId(provider.getId())
                )
        )));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> getProviderViewProfile(Long id) {
        Provider provider = this.providerRepository.findById(id).orElse(new Provider());
        double totalRation = 0;
        long sumRation = 0;
        double rate = 0;
        if (this.providerRepository.countRationByProviderId(id) != null)
            totalRation = this.providerRepository.countRationByProviderId(id);
        if (this.providerRepository.sumRationByProviderId(id) != null) {
            sumRation = this.providerRepository.sumRationByProviderId(id);
            rate = sumRation / totalRation;
        }
        return CompletableFuture.completedFuture(
                ResponseEntity.ok(
                        new ProviderViewProfile(
                                provider.getBusinessName(),
                                provider.getUser().getName(),
                                rate,
                                this.providerRepository.countAllBookingByProviderId(provider.getId()),
                                this.providerRepository.countActiveJob(provider.getId())
                        )
                )
        );
    }


    // Get Profile To Settings

    @Async
    public CompletableFuture<ResponseEntity<?>> getToSettings(Long providerId) {
        Provider provider = this.providerRepository.findById(providerId).orElse(null);
        if (provider == null) return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Provider()));
        return CompletableFuture.completedFuture(ResponseEntity.ok(new Profile(
                provider.getBusinessName(),
                provider.getUser().getProfile(),
                this.providerRepository.getPlanName(providerId),
                this.jobRepository.findServiceByProviderId(provider.getId())
        )));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> getPersonal(Long providerId) {
        Provider provider = this.providerRepository.findById(providerId).orElse(new Provider());
        List<TopSkill> topSkills = new ArrayList<>();
        this.providerRepository.TopJobFocus(providerId, true).forEach(
                jobFocus -> {
                    topSkills.add(
                            new TopSkill(jobFocus.getId(), jobFocus.getSkill().getName())
                    );
                }
        );
        return CompletableFuture.completedFuture(ResponseEntity.ok(new Personal(
                provider.getBusinessName(),
                provider.getDescription(),
                provider.getServiceRadius(),
                topSkills,
                new Contact(provider.getUser().getTel(), provider.getEmail()),
                new AddressDto(provider.getLat(), provider.getLon())
        )));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> editePersonal(Long providerId, PersonalRequest personal) {
        Provider provider = this.providerRepository.findById(providerId).orElse(null);
        if (provider == null)
            return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("No data")));
        provider.setBusinessName(personal.getBusinessName());
        provider.setDescription(personal.getDescription());
        provider.setServiceRadius(personal.getServiceRadius());
        this.providerRepository.save(provider);
        this.jobFocusRepository.setAllTopToFalseByProviderId(providerId, false);
        if (!personal.getTopSkill().isEmpty()) {
            personal.getTopSkill().forEach(
                    topSkill -> {
                        JobFocus jobFocus = this.jobFocusRepository.findById(topSkill).orElse(null);
                        if (jobFocus != null) {
                            System.out.println(jobFocus.getId());
                            jobFocus.setTop(true);
                            this.jobFocusRepository.save(jobFocus);
                        }

                    }
            );
        }
        System.out.println(personal.getTopSkill());
        this.messagingTemplate.convertAndSend("/queue/provider", new Event(true));
        return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> getToService(Long providerId) {
        List<ProviderJob> jobs = new ArrayList<>();
        this.providerRepository.findJobByProviderId(providerId).forEach(
                job -> {
                    List<ProviderJobFocus> jobFocus = new ArrayList<>();
                    this.providerRepository.findJobFocusByJobId(job.getId()).forEach(
                            jobFocus1 -> {
                                jobFocus.add(
                                        new ProviderJobFocus(
                                                jobFocus1.getId(),
                                                jobFocus1.getSkill().getName(),
                                                jobFocus1.getPrice(),
                                                jobFocus1.getPrice2(),
                                                jobFocus1.getDuration(),
                                                jobFocus1.getDescription(),
                                                jobFocus1.getStatus(),
                                                jobFocus1.getSkill().getImage()
                                        )
                                );
                            }
                    );
                    jobs.add(
                            new ProviderJob(
                                    job.getId(),
                                    job.getService().getName(),
                                    job.getStatus(),
                                    job.getService().getImage(),
                                    jobFocus
                            )
                    );
                }
        );
        return CompletableFuture.completedFuture(ResponseEntity.ok(new ProviderServices(
                this.providerRepository.countActiveJob(providerId),
                this.providerRepository.countActiveJobFocus(providerId),
                jobs
        )));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> getToServiceToCustomer(Long providerId) {
        List<ProviderJob> jobs = new ArrayList<>();
        this.providerRepository.findJobByProviderIdToCustomer(providerId).forEach(
                job -> {
                    List<ProviderJobFocus> jobFocus = new ArrayList<>();
                    this.providerRepository.findJobFocusByJobIdToCustomer(job.getId()).forEach(
                            jobFocus1 -> {
                                jobFocus.add(
                                        new ProviderJobFocus(
                                                jobFocus1.getId(),
                                                jobFocus1.getSkill().getName(),
                                                jobFocus1.getPrice(),
                                                jobFocus1.getPrice2(),
                                                jobFocus1.getDuration(),
                                                jobFocus1.getDescription(),
                                                jobFocus1.getStatus(),
                                                jobFocus1.getSkill().getImage()
                                        )
                                );
                            }
                    );
                    jobs.add(
                            new ProviderJob(
                                    job.getId(),
                                    job.getService().getName(),
                                    job.getStatus(),
                                    job.getService().getImage(),
                                    jobFocus
                            )
                    );
                }
        );
        return CompletableFuture.completedFuture(ResponseEntity.ok(new ProviderServices(
                this.providerRepository.countActiveJob(providerId),
                this.providerRepository.countActiveJobFocus(providerId),
                jobs
        )));
    }


    public Contact getContact(Long id) {
        Provider provider = this.providerRepository.findById(id).orElse(null);
        return new Contact(provider.getUser().getTel(), provider.getEmail());
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> getProviderNearCustomer(Long userId) {
        List<ProviderNear> providerNears = new ArrayList<>();
        this.providerRepository.findProviderNearCustomer(userId).forEach(
                providerNear -> {
                    providerNear.setOnline(false);
                    double rate = 0;
                    double totalRation = 0;
                    long sumRation = 0;
                    if (this.providerRepository.countRationByProviderId(providerNear.getId()) != null)
                        totalRation = this.providerRepository.countRationByProviderId(providerNear.getId());
                    if (this.providerRepository.sumRationByProviderId(providerNear.getId()) != null) {
                        sumRation = this.providerRepository.sumRationByProviderId(providerNear.getId());
                        rate = sumRation / totalRation;
                    }
                    if (this.registry.isOnline(providerNear.getId().toString())) providerNear.setOnline(true);
                    providerNears.add(
                            new ProviderNear(
                                    providerNear.getId(),
                                    providerNear.getName(),
                                    providerNear.getImage(),
                                    rate,
                                    providerNear.isOnline()
                            )
                    );
                }
        );
        return CompletableFuture.completedFuture(ResponseEntity.ok(providerNears));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> getProviderNearViewByServiceId(Long userId, Long serviceId) {
        List<ProviderNearView> providerNearViews = new ArrayList<>();
        this.providerRepository.findProviderNearByServiceId(userId, serviceId).forEach(
                providerNearView -> {
                    double rate = 0;
                    double totalRation = 0;
                    long sumRation = 0;
                    if (this.providerRepository.countRationByProviderId(providerNearView.getId()) != null)
                        totalRation = this.providerRepository.countRationByProviderId(providerNearView.getId());
                    if (this.providerRepository.sumRationByProviderId(providerNearView.getId()) != null) {
                        sumRation = this.providerRepository.sumRationByProviderId(providerNearView.getId());
                        rate = sumRation / totalRation;
                    }
                    providerNearView.setService(Arrays.asList(this.jobRepository.findByProvider_IdAndService_Id(providerNearView.getId(), serviceId).getService().getName()));
                    providerNearView.setRate(rate);
                    providerNearView.setTotalBooking(this.providerRepository.countAllBookingByProviderId(providerNearView.getId()));
                    providerNearViews.add(providerNearView);
                }
        );
        return CompletableFuture.completedFuture(ResponseEntity.ok(providerNearViews));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> getProviderNearView(Long userId) {
        List<ProviderNearView> providerNearViews = new ArrayList<>();
        this.providerRepository.findProviderNear(userId).forEach(
                providerNearView -> {
                    double rate = 0;
                    double totalRation = 0;
                    long sumRation = 0;
                    if (this.providerRepository.countRationByProviderId(providerNearView.getId()) != null)
                        totalRation = this.providerRepository.countRationByProviderId(providerNearView.getId());
                    if (this.providerRepository.sumRationByProviderId(providerNearView.getId()) != null) {
                        sumRation = this.providerRepository.sumRationByProviderId(providerNearView.getId());
                        rate = sumRation / totalRation;
                    }
                    providerNearView.setService(this.jobRepository.findServiceByProviderId(providerNearView.getId()));
                    providerNearView.setRate(rate);
                    providerNearView.setTotalBooking(this.providerRepository.countAllBookingByProviderId(providerNearView.getId()));
                    providerNearViews.add(providerNearView);
                }
        );
        return CompletableFuture.completedFuture(ResponseEntity.ok(providerNearViews));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> getProviderNearBySkill(Long userId, Long skillId) {
        try {
            List<ProviderNearView> providerNearViews = new ArrayList<>();
            this.providerRepository.findProviderNearByJobFocusId(userId, skillId).forEach(
                    providerNearView -> {
                        double rate = 0;
                        double totalRation = 0;
                        long sumRation = 0;
                        if (this.providerRepository.countRationByProviderId(providerNearView.getId()) != null)
                            totalRation = this.providerRepository.countRationByProviderId(providerNearView.getId());
                        if (this.providerRepository.sumRationByProviderId(providerNearView.getId()) != null) {
                            sumRation = this.providerRepository.sumRationByProviderId(providerNearView.getId());
                            rate = sumRation / totalRation;
                        }
                        providerNearView.setService(this.jobRepository.findServiceByProviderId(providerNearView.getId()));
                        providerNearView.setRate(rate);
                        providerNearView.setTotalBooking(this.providerRepository.countAllBookingByProviderId(providerNearView.getId()));
                        providerNearViews.add(providerNearView);
                    }
            );
            return CompletableFuture.completedFuture(ResponseEntity.ok(providerNearViews));
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> getAboutProviderById(Long providerId) {
        Provider provider = this.providerRepository.findById(providerId).orElse(null);
        if (provider == null) return null;
        List<String> topSkills = new ArrayList<>();
        this.providerRepository.TopJobFocus(providerId, true).forEach(
                jobFocus -> {
                    topSkills.add(
                            jobFocus.getSkill().getName()
                    );
                }
        );
        return CompletableFuture.completedFuture(ResponseEntity.ok(new About(
                provider.getDescription(),
                provider.getUser().getTel(),
                new AddressDto(provider.getLat(), provider.getLon()),
                new WorkingInfo(
                        provider.getStartDate(),
                        provider.getEndDate(),
                        provider.getStartTime().format(DateTimeFormatter.ofPattern("h:mm a")),
                        provider.getEndTime().format(DateTimeFormatter.ofPattern("h:mm a"))
                ),
                topSkills,
                provider.getEmail()
        )));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> getAllProviderApproved() {
        List<ProviderApproveDto> providers = new ArrayList<>();
        this.providerRepository.findByProviderApprove().forEach(
                provider -> {
                    double rate = 0;
                    double totalRation = 0;
                    long sumRation = 0;
                    if (this.providerRepository.countRationByProviderId(provider.getId()) != null)
                        totalRation = this.providerRepository.countRationByProviderId(provider.getId());
                    if (this.providerRepository.sumRationByProviderId(provider.getId()) != null) {
                        sumRation = this.providerRepository.sumRationByProviderId(provider.getId());
                        rate = sumRation / totalRation;
                    }
                    providers.add(
                            new ProviderApproveDto(
                                    provider.getId(),
                                    provider.getBusinessName(),
                                    provider.getUser().getProfile(),
                                    this.jobRepository.findServiceByProviderId(provider.getId()),
                                    rate,
                                    this.providerRepository.countAllBookingByProviderId(provider.getId()),
                                    provider.getEmail(),
                                    provider.getUser().getTel()
                            )
                    );
                }
        );
        return CompletableFuture.completedFuture(ResponseEntity.ok(providers));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> getAllProviderPending() {
        List<ProviderPending> providers = new ArrayList<>();
        this.providerRepository.findByjob_status("Pending").forEach(
                provider -> {
                    providers.add(
                            new ProviderPending(
                                    provider.getId(),
                                    provider.getBusinessName(),
                                    provider.getEmail(),
                                    provider.getUser().getTel(),
                                    provider.getUser().getDob(),
                                    provider.getUser().getProfile(),
                                    provider.getCreateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a")),
                                    provider.getJobStatus()
                            )
                    );
                }
        );
        return CompletableFuture.completedFuture(ResponseEntity.ok(providers));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> getToReport() {
        try {
            List<ProviderListReport> providers = new ArrayList<>();
            this.providerRepository.findAll().forEach(
                    provider -> {
                        double totalRation = 0;
                        long sumRation = 0;
                        double rate = 0;
                        double amount = 0;
                        if(this.providerRepository.totalPriceCompleted(provider.getId())!=null) amount = this.providerRepository.totalPriceCompleted(provider.getId());
                        if (this.providerRepository.countRationByProviderId(provider.getId()) != null)
                            totalRation = this.providerRepository.countRationByProviderId(provider.getId());
                        if (this.providerRepository.sumRationByProviderId(provider.getId()) != null) {
                            sumRation = this.providerRepository.sumRationByProviderId(provider.getId());
                            rate = sumRation / totalRation;
                        }
                        providers.add(
                                new ProviderListReport(
                                        provider.getId(),
                                        provider.getBusinessName(),
                                        provider.getUser().getTel(),
                                        provider.getEmail(),
                                        this.providerRepository.countAllBookingByProviderId(provider.getId()),
                                        this.providerRepository.countAllCompletedBooking(provider.getId()),
                                        this.providerRepository.countAllCancelledBooking(provider.getId()),
                                        provider.getCreateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a")),
                                        rate,
                                        amount
                                )
                        );
                    }
            );
            return CompletableFuture.completedFuture(ResponseEntity.ok(
                    new ProviderReport(
                            providers,
                            this.providerRepository.getGrowthPerMonth(LocalDate.now().getYear()),
                            new ProviderStatusCount(
                                    this.providerRepository.count(),
                                    this.providerRepository.countByjob_status("Approved"),
                                    this.providerRepository.countByjob_status("Pending"),
                                    this.providerRepository.countByjob_status("Rejected")
                            )

                    )
            ));
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> getServiceNearCustomer(Long userId) {
        try {
            Address address = this.addressRepository.findByUser_IdAndStatus(userId, "Default");
            return CompletableFuture.completedFuture(ResponseEntity.ok(this.providerRepository.getServiceNear(address.getLat(), address.getLon(),userId)));
        } catch (Exception e) {
            return CompletableFuture.completedFuture(
                    ResponseEntity.status(500).body(new Message(e.getMessage()))
            );
        }
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> getProviderProfile(Long providerId) {
        try {
            Provider provider = this.providerRepository.findById(providerId).orElse(null);
            if (provider == null)
                return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("No data")));
            Long totalRation = this.providerRepository.countRationByProviderId(provider.getId());
            ;
            double rate = 0;
            double avg = 0;
            boolean online = false;
            if (provider.getStatus().equals("Active")) {
                online = true;
            }
            if (this.NotificationService.checkSubscriptionToProvider(providerId)) {
                online = false;
            }
            if (this.providerRepository.sumRationByProviderId(provider.getId()) != null) {
                rate = this.providerRepository.sumRationByProviderId(provider.getId()) / totalRation;
            }
            if (this.providerRepository.findAvg(provider.getId()) != null)
                avg = this.providerRepository.findAvg(provider.getId());
            return CompletableFuture.completedFuture(
                    ResponseEntity.ok(
                            new HomeProfile(
                                    provider.getBusinessName(),
                                    provider.getProfile(),
                                    this.jobRepository.findServiceByProviderId(provider.getId()),
                                    rate,
                                    this.providerRepository.countAllCompletedBooking(provider.getId()),
                                    avg,
                                    online
                            )
                    )
            );
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> getNewCompleted(Long providerId) {
        try {
            return CompletableFuture.completedFuture(ResponseEntity.ok(this.providerRepository.newCompleted(providerId)));
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> block(Long providerId) {
        try {
            Provider provider = this.providerRepository.findById(providerId).orElse(null);
            if (provider == null)
                return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("No data")));
            provider.setJobStatus("Block");
            provider.setStatus("Block");
            this.providerRepository.save(provider);
            this.NotificationService.sendMessage(provider.getUser(), "BLOCK", "Your account is block", "PROVIDER", provider.getUser().getId());
            return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> unblock(Long providerId) {
        try {
            Provider provider = this.providerRepository.findById(providerId).orElse(null);
            if (provider == null)
                return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("No data")));
            provider.setJobStatus("Approved");
            provider.setStatus("Active");
            this.providerRepository.save(provider);
            this.NotificationService.sendMessage(provider.getUser(), "UNBLOCK", "Your account is unblock", "PROVIDER", provider.getUser().getId());
            return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

}

package com.example.Backend.Services;

import com.example.Backend.DTOS.AddressDto;
import com.example.Backend.DTOS.AdminDashboards.TransactionReports.TransactionReportList;
import com.example.Backend.DTOS.AdminDashboards.TransactionReports.TransactionsReport;
import com.example.Backend.DTOS.SubscriptionExpired;
import com.example.Backend.DTOS.Subscriptions.BillingInfo;
import com.example.Backend.DTOS.Subscriptions.SubscriptionInfo;
import com.example.Backend.DTOS.Subscriptions.SubscriptionListView;
import com.example.Backend.DTOS.Subscriptions.SubscriptionView;
import com.example.Backend.DTOS.Transactions.*;
import com.example.Backend.Events.Event;
import com.example.Backend.Models.Plan;
import com.example.Backend.Models.Provider;
import com.example.Backend.Models.Subscription;
import com.example.Backend.Models.Transaction;
import com.example.Backend.Repositories.*;
import com.example.Backend.Responses.Messages.Message;
import com.example.Backend.Responses.Subscriptions.SubscriptionResponse;
import org.checkerframework.checker.units.qual.C;
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

@Service
public class SubscriptionService {
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private ProviderService providerService;

    @Autowired
    private PlanService planService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private NotificationService NotificationService;


    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobFocusRepository jobFocusRepository;

    @Autowired
    private ProviderRepository providerRepository;


    private ResponseEntity<?> subscriptionForMonth(Provider provider ,Plan plan){
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(30);
        float total = plan.getPrice();
        Transaction transaction = this.transactionRepository.save(new Transaction(
                provider,
                plan,
                startDate,
                endDate,
                plan.getDuration(),
                "Pending",
                total,
                LocalDateTime.now())
        );
        this.messagingTemplate.convertAndSend("/queue/transaction", new Event(true));
        return ResponseEntity.ok(new SubscriptionResponse(transaction.getId(),plan.getName(), plan.getDuration(),startDate,endDate,total,true,"Full subscription"));
    }

    private ResponseEntity<?> subscriptionForYear(Provider provider,Plan plan){
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusMonths(12);
        float total = plan.getPrice();
        Transaction transaction = this.transactionRepository.save(new Transaction(
                provider,
                plan,
                startDate,
                endDate,
                plan.getDuration(),
                "Pending",
                total,
                LocalDateTime.now())
        );
        this.messagingTemplate.convertAndSend("/queue/transaction", new Event(true));
        return ResponseEntity.ok(new SubscriptionResponse(transaction.getId(),plan.getName(), plan.getDuration(),startDate,endDate,total,true,"Full subscription"));
    }

    public Subscription getByProvider(Long providerId){
        return this.subscriptionRepository.findByProvider_Id(providerId);
    }

    public CompletableFuture<ResponseEntity<?>> firstSubscription(Long providerId){
        Provider provider = this.providerService.getById(providerId);
        if(!provider.getJobStatus().equals("Approved")) return CompletableFuture.completedFuture(ResponseEntity.status(403).body(new Message("Your account is not already")));
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(30);
        Plan plan = this.planService.getFreePlan();
        if(plan==null)  return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("Sorry")));
        Transaction save = this.transactionRepository.save(new Transaction(
                provider,
                plan,
                startDate,
                endDate,
                plan.getDuration(),
                "Pending",
                plan.getPrice(),
                LocalDateTime.now()
        ));
        return this.payment(save.getId());
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> transaction(Long providerId,Long planId){
        Provider provider = this.providerService.getById(providerId);
        Subscription subscription = this.subscriptionRepository.findByProvider_Id(providerId);
        if(!provider.getJobStatus().equals("Approved")) return CompletableFuture.completedFuture(ResponseEntity.status(403).body(new Message("Your account is not already")));
        if(subscription != null){
            if(subscription.getPlan().getId().equals(planId) && subscription.getEndDate().isBefore(LocalDate.now())
            ) return CompletableFuture.completedFuture(ResponseEntity.status(409).body(new Message("This plan is in your subscription and not expired ")));
        }
        Plan plan = this.planService.getById(planId);
        this.transactionRepository.update(providerId,"Pending","Declined");
        if(plan.getDuration().equals("Monthly")) return CompletableFuture.completedFuture(this.subscriptionForMonth(provider,plan));
        return CompletableFuture.completedFuture(this.subscriptionForYear(provider,plan));
    }

    public boolean subscription(Transaction transaction){
       try{
           Subscription subscription = this.subscriptionRepository.findByProvider_Id(transaction.getProvider().getId());
           if(subscription==null){
               this.subscriptionRepository.save(new Subscription(
                       transaction.getProvider(),
                       transaction.getPlan(),
                       transaction.getStartDate(),
                       transaction.getEndDate(),
                       "Active"
               ));
               this.messagingTemplate.convertAndSend("/queue/subscription", new Event(true));
               return true;
           }
           Plan plan = planService.getById(transaction.getPlan().getId());
           if(plan.getMaxService()<subscription.getPlan().getMaxService()) {
               this.jobRepository.setInActiveAllByProviderId(subscription.getProvider().getId());
               this.jobFocusRepository.setStatusByProviderIdAndStatus(subscription.getProvider().getId(),"Inactive");
           }
           subscription.setPlan(transaction.getPlan());
           subscription.setStartDate(transaction.getStartDate());
           subscription.setEndDate(transaction.getEndDate());
           subscription.setStatus("Active");
           this.subscriptionRepository.save(subscription);
           this.messagingTemplate.convertAndSend("/queue/subscription", new Event(true));
           return  true;
       } catch (Exception e) {
           System.out.println(e.getMessage());
           return false;
       }
    }

    @Async
    public CompletableFuture<ResponseEntity<?> >payment(Long id){
        Transaction transaction = this.transactionRepository.findById(id).orElse(null);
        if(transaction == null) return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("No data")));
        if(transaction.getStatus().equals("Completed") || transaction.getStatus().equals("Declined")) return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully Ok")));
        transaction.setStatus("Completed");
        Transaction save = this.transactionRepository.save(transaction);
        if(!this.subscription(save)){
            NotificationService.sendMessage(
                    transaction.getProvider().getUser(),
                    "PAYMENT",
                    "Payment completed successfully.",
                    "PAYMENT",
                    transaction.getId()
            );
            this.messagingTemplate.convertAndSend("/queue/transaction", new Event(true));
            this.messagingTemplate.convertAndSend("/queue/dashboard", new Event(true));
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message("Your Payment is Success buf system server is Error please context to admin system")));
        }
        NotificationService.sendMessage(
                transaction.getProvider().getUser(),
                "PAYMENT",
                "Payment completed successfully.",
                "PAYMENT",
                transaction.getId()
        );
        this.messagingTemplate.convertAndSend("/queue/transaction", new Event(true));
        this.messagingTemplate.convertAndSend("/queue/dashboard", new Event(true));
        return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
    }

    //Transaction

    @Async
    public CompletableFuture<ResponseEntity<?>> getToList(){
        TransactionOverView transaction = new TransactionOverView();
        transaction.setTotalTransaction(this.transactionRepository.count());
        transaction.setCompleted(this.transactionRepository.countByStatus("Completed"));
        transaction.setPending(this.transactionRepository.countByStatus("Pending"));
        Double total =0.0;
        if(this.transactionRepository.sumPriceByStatus("Completed")!=null) total =  this.transactionRepository.sumPriceByStatus("Completed");
        transaction.setTotalRevenue(total);
        List<TransactionList> transactionLists = new ArrayList<>();
        this.transactionRepository.findAll().forEach(
                transactions ->{
                    transactionLists.add(
                            new TransactionList(
                                    transactions.getId(),
                                    transactions.getProvider().getBusinessName(),
                                    transactions.getProvider().getUser().getTel(),
                                    transactions.getProvider().getEmail(),
                                    transactions.getPlan().getName(),
                                    (float) transactions.getTotal(),
                                    transactions.getPayDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a")),
                                    transactions.getStatus(),
                                    transactions.getProvider().getProfile()
                            )
                    );
                }
        );
        transaction.setTransactions(transactionLists);
        return CompletableFuture.completedFuture(ResponseEntity.ok(transaction));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> getTransactionToAdminView(Long id){
        Transaction transaction = this.transactionRepository.findById(id).orElse(new Transaction());
        return CompletableFuture.completedFuture(ResponseEntity.ok(new TransactionView(
                new TransactionDetail(
                        transaction.getId(),
                        transaction.getStatus(),
                        transaction.getPayDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a")),
                        transaction.getTotal()
                ),
                new ProviderInfo(
                        transaction.getProvider().getBusinessName(),
                        transaction.getProvider().getUser().getTel(),
                        transaction.getProvider().getEmail(),
                        transaction.getProvider().getUser().getDob(),
                        new AddressDto(transaction.getProvider().getLat(),transaction.getProvider().getLon()),
                        transaction.getProvider().getProfile()
                ),
                new SubscriptionPlan(
                        transaction.getPlan().getName(),
                        transaction.getCategory(),
                        transaction.getStartDate(),
                        transaction.getEndDate(),
                        transaction.getPlan().getMaxService(),
                        transaction.getPlan().getMaxSkill()

                )
        )));
    }

    // Subscription
    @Async
    public CompletableFuture<ResponseEntity<?>> getAllSubscriptionListView(){
        List<SubscriptionListView> lists = new ArrayList<>();
        this.subscriptionRepository.findAll().forEach(
               subscription -> {
                   lists.add(
                           new SubscriptionListView(
                                   subscription.getId(),
                                   subscription.getProvider().getBusinessName(),
                                   subscription.getProvider().getProfile(),
                                   subscription.getPlan().getName(),
                                   subscription.getPlan().getPrice(),
                                   subscription.getStatus(),
                                   subscription.getStartDate(),
                                   subscription.getEndDate(),
                                   subscription.getPlan().getDuration()
                           )
                   );
               }
        );
        return CompletableFuture.completedFuture(ResponseEntity.ok(lists));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> getSubscriptionView(Long id){
        Subscription subscription = this.subscriptionRepository.findById(id).orElse(new Subscription());
        double price = subscription.getPlan().getPrice();
        if(subscription.getPlan().getDuration().equals("Year")) price = subscription.getPlan().getPrice()*12;
        return CompletableFuture.completedFuture(ResponseEntity.ok(new SubscriptionView(
                new SubscriptionInfo(
                        subscription.getId(),
                        subscription.getStatus(),
                        subscription.getPlan().getName(),
                        subscription.getPlan().getPrice(),
                        subscription.getStartDate(),
                        subscription.getEndDate()
                ),
                new com.example.Backend.DTOS.Subscriptions.ProviderInfo(
                        subscription.getProvider().getBusinessName(),
                        subscription.getProvider().getUser().getTel(),
                        subscription.getProvider().getEmail(),
                        new AddressDto(subscription.getProvider().getLat(),subscription.getProvider().getLon()),
                        subscription.getProvider().getProfile()
                ),
                new BillingInfo(price,subscription.getPlan().getDuration())
        )));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> transactionReport(){
        try{
            List<TransactionReportList> transactionReportLists = new ArrayList<>();
            this.transactionRepository.findAll().forEach(
                    transaction -> {
                        transactionReportLists.add(
                                new TransactionReportList(
                                        transaction.getId(),
                                        transaction.getProvider().getBusinessName(),
                                        transaction.getProvider().getUser().getTel(),
                                        transaction.getProvider().getEmail(),
                                        transaction.getPlan().getName(),
                                        (float) transaction.getTotal(),
                                        transaction.getPayDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a")),
                                        transaction.getStatus()
                                )
                        );
                    }
            );
            return CompletableFuture.completedFuture(
                    ResponseEntity.ok(
                            new TransactionsReport(
                                    this.transactionRepository.getEarnings(LocalDate.now().getYear()),
                                    transactionReportLists
                            )
                    )
            );
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    public ResponseEntity<?> checkSubscriptionToProvider(Long providerId){
        try{
            Subscription subscription = this.subscriptionRepository.findByProvider_Id(providerId);
            if(subscription!=null){

                if(!LocalDate.now().isBefore(subscription.getEndDate())){
                    return ResponseEntity.ok(new SubscriptionExpired(false));
                }
                return ResponseEntity.ok(new SubscriptionExpired(true));
            }
            return ResponseEntity.ok(new SubscriptionExpired(false));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.ok(new SubscriptionExpired(false));
        }
    }

}

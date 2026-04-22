package com.example.Backend.Services;

import com.example.Backend.DTOS.Jobs.BookingJob;
import com.example.Backend.DTOS.Jobs.ProviderJob;
import com.example.Backend.DTOS.MobileProviders.ProviderViewDetails.Services.ServiceDetail;
import com.example.Backend.DTOS.MobileProviders.ProviderViewDetails.Services.SkillDetail;
import com.example.Backend.Events.Event;
import com.example.Backend.Models.Job;
import com.example.Backend.Models.Provider;
import com.example.Backend.Models.Services;
import com.example.Backend.Models.Subscription;
import com.example.Backend.Repositories.JobFocusRepository;
import com.example.Backend.Repositories.JobRepository;
import com.example.Backend.Responses.Messages.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private ProviderService providerService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private JobFocusRepository jobFocusRepository;

    @Async
    public CompletableFuture<ResponseEntity<?>> add(Long providerId,Long serviceId){
        Subscription subscription = this.subscriptionService.getByProvider(providerId);
        Services services = this.categoryService.getById(serviceId);
        Provider provider = this.providerService.getById(providerId);
        if(!provider.getJobStatus().equals("Approved")) return CompletableFuture.completedFuture(ResponseEntity.status(403).body(new Message("Your account is not approved")));
        if(subscription==null|| !subscription.getStatus().equals("Active")) return CompletableFuture.completedFuture(ResponseEntity.status(403).body(new Message("Please subscription")));
        if(this.jobRepository.countByProvider_Id(providerId)>=subscription.getPlan().getMaxService()) return CompletableFuture.completedFuture(ResponseEntity.status(403).body(new Message("Full")));
        this.jobRepository.save(
                new Job(
                        provider,
                        services,
                        "Active",
                        LocalDate.now()
                )
        );
        return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> setStatusActive(Long id){
        Job job = this.jobRepository.findById(id).orElse(null);
        if(job == null) return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("No data")));
        Provider provider = this.providerService.getById(job.getProvider().getId());
        if(!provider.getJobStatus().equals("Approved")) return CompletableFuture.completedFuture(ResponseEntity.status(403).body(new Message("Your account is not approved")));
        Subscription subscription = this.subscriptionService.getByProvider(job.getProvider().getId());
        if(subscription==null|| !subscription.getStatus().equals("Active")) return CompletableFuture.completedFuture(ResponseEntity.status(403).body(new Message("Please subscription")));
        if(subscription.getPlan().getMaxService()<=this.jobRepository.countByProvider_IdAndStatus(job.getProvider().getId(),"Active"))
            return CompletableFuture.completedFuture(ResponseEntity.status(403).body(new Message("Your plan is full")));
        job.setStatus("Active");
        this.jobRepository.save(job);
        return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> setStatusInactive(Long id){
        Job job = this.jobRepository.findById(id).orElse(null);
        if(job == null) return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("No data")));
        Provider provider = this.providerService.getById(job.getProvider().getId());
        if(!provider.getJobStatus().equals("Approved")) return CompletableFuture.completedFuture(ResponseEntity.status(403).body(new Message("Your account is not approved")));
        Subscription subscription = this.subscriptionService.getByProvider(job.getProvider().getId());
        if(subscription==null|| !subscription.getStatus().equals("Active")) return CompletableFuture.completedFuture(ResponseEntity.status(403).body(new Message("Please subscription")));
        job.setStatus("Inactive");
        this.jobRepository.save(job);
        this.jobFocusRepository.setStatusByJobStatus(job.getId(),"Inactive");
        return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
    }


    public Job getById(Long id){
        return this.jobRepository.findById(id).orElse(null);
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> getAllJobByProviderId(Long providerId){
        List<ServiceDetail> serviceDetails = new ArrayList<>();
        this.jobRepository.findByProvider_Id(providerId).forEach(
                job -> {
                    List<SkillDetail> skillDetails = new ArrayList<>();
                    this.jobRepository.findJobFocusByJobId(job.getId()).forEach(
                            jobFocus -> {
                                skillDetails.add(
                                        new SkillDetail(
                                                jobFocus.getId(),
                                                jobFocus.getSkill().getName(),
                                                (int)jobFocus.getDuration(),
                                                jobFocus.getPrice()+"-"+jobFocus.getPrice2(),
                                                jobFocus.getSkill().getImage()
                                        )
                                );
                            }
                    );
                    serviceDetails.add(
                            new ServiceDetail(
                                    job.getService().getName(),
                                    skillDetails
                            )
                    );
                }
        );
        return CompletableFuture.completedFuture(ResponseEntity.ok(serviceDetails));
    }


}

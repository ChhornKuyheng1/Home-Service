package com.example.Backend.Services;
import com.example.Backend.DTOS.JobFocus.Mobiles.JobFocusDetail;
import com.example.Backend.DTOS.JobFocus.Mobiles.JobFocusDtoToM;
import com.example.Backend.DTOS.JobFocus.Mobiles.JobFocusProviderView;
import com.example.Backend.DTOS.JobFocus.Mobiles.TopSkill;
import com.example.Backend.Events.Event;
import com.example.Backend.Models.*;
import com.example.Backend.Repositories.JobFocusRepository;
import com.example.Backend.Repositories.ProviderRepository;
import com.example.Backend.Repositories.ReviewRepository;
import com.example.Backend.Requests.JobFocusRequest;
import com.example.Backend.Requests.UpdateJobFocusRequest;
import com.example.Backend.Responses.Messages.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class JobFocusService {

    @Autowired
    private JobFocusRepository jobFocusRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private JobService jobService;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ProviderRepository providerRepository;

    public JobFocus getById(Long id){
        return this.jobFocusRepository.findById(id).orElse(null);
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> add(JobFocusRequest jobFocus){
        Job job = this.jobService.getById(jobFocus.getJobId());
        Skill skill = this.skillService.getById(jobFocus.getSkillId());
        Provider provider = this.providerRepository.findById(job.getProvider().getId()).orElse(null);
        Subscription subscription = this.subscriptionService.getByProvider(job.getProvider().getId());
        if(!provider.getJobStatus().equals("Approved")) return CompletableFuture.completedFuture(ResponseEntity.status(403).body(new Message("Your account is not approved")));
        if(!subscription.getStatus().equals("Active"))return CompletableFuture.completedFuture(ResponseEntity.status(403).body(new Message("Please subscription")));
        if(this.jobFocusRepository.countByJob_Id(job.getId())>= subscription.getPlan().getMaxSkill()) return CompletableFuture.completedFuture(ResponseEntity.status(403).body(new Message("Full")));
        this.jobFocusRepository.save(new JobFocus(
                job,
                skill,
                jobFocus.getPrice1(),
                jobFocus.getPrice2(),
                jobFocus.getDuration(),
                "Active",
                jobFocus.getDescription(),
                LocalDate.now()
        ));
        this.messagingTemplate.convertAndSend(
                "/queue/service/report",
                new Event(true)
        );
        return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>>edite(UpdateJobFocusRequest job){
        JobFocus existion = this.jobFocusRepository.findById(job.getId()).orElse(null);
        if(existion==null) return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("No data")));
        Provider provider = this.providerRepository.findById(existion.getJob().getProvider().getId()).orElse(null);
        Subscription subscription = this.subscriptionService.getByProvider(provider.getId());
        if(!provider.getJobStatus().equals("Approved")) return CompletableFuture.completedFuture(ResponseEntity.status(403).body(new Message("Your account is not approved")));
        if(!subscription.getStatus().equals("Active"))return CompletableFuture.completedFuture(ResponseEntity.status(403).body(new Message("Please subscription")));
        existion.setPrice(job.getPrice1());
        existion.setPrice2(job.getPrice2());
        existion.setDuration(job.getDuration());
        existion.setDescription(job.getDescription());
        this.jobFocusRepository.save(existion);
        return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> setStatusActive(Long id){
        JobFocus jobFocus = this.jobFocusRepository.findById(id).orElse(null);
        if(jobFocus==null) return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("No data")));
        Provider provider = this.providerRepository.findById(jobFocus.getJob().getProvider().getId()).orElse(null);
        if(!provider.getJobStatus().equals("Approved")) return CompletableFuture.completedFuture(ResponseEntity.status(403).body(new Message("Your account is not approved")));
        Subscription subscription = this.subscriptionService.getByProvider(jobFocus.getJob().getProvider().getId());
        if(!subscription.getStatus().equals("Active"))return CompletableFuture.completedFuture(ResponseEntity.status(403).body(new Message("Please subscription")));
        if(subscription.getPlan().getMaxSkill()<=this.jobFocusRepository.countByJob_IdAndStatus(jobFocus.getJob().getId(),"Active")) return CompletableFuture.completedFuture(ResponseEntity.status(403).body(new Message("Your plan is full")));
        jobFocus.setStatus("Active");
        this.jobFocusRepository.save(jobFocus);
        return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> setStatusInActive(Long id){
        JobFocus jobFocus = this.jobFocusRepository.findById(id).orElse(null);
        if(jobFocus==null) return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("No data")));
        Provider provider = this.providerRepository.findById(jobFocus.getJob().getProvider().getId()).orElse(null);
        if(!provider.getJobStatus().equals("Approved")) return CompletableFuture.completedFuture(ResponseEntity.status(403).body(new Message("Your account is not approved")));
        Subscription subscription = this.subscriptionService.getByProvider(jobFocus.getJob().getProvider().getId());
        if(!subscription.getStatus().equals("Active"))return CompletableFuture.completedFuture(ResponseEntity.status(403).body(new Message("Please subscription")));
        jobFocus.setStatus("Inactive");
        this.jobFocusRepository.save(jobFocus);
        return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> getByProviderId(Long providerId){
        List<TopSkill> skills = new ArrayList<>();
        this.jobFocusRepository.findByProviderId(providerId).forEach(
                jobFocus -> {
                    skills.add(
                            new TopSkill(jobFocus.getId(),jobFocus.getSkill().getName())
                    );
                }
        );
        return CompletableFuture.completedFuture(ResponseEntity.ok(skills));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> getToView(Long jobFocusId){
        try{
            JobFocus jobFocus = this.jobFocusRepository.findById(jobFocusId).orElse(null);
            if(jobFocus == null) return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("No data")));
            Provider provider = this.providerRepository.findById(jobFocus.getJob().getProvider().getId()).orElse(null);
            if(provider == null) return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("No provider data")));
            double totalRation = this.providerRepository.countRationByProviderId(provider.getId());
            Long  sumRation = this.providerRepository.sumRationByProviderId(provider.getId());
            double totalJobRate = this.reviewRepository.countByJobFocusId(jobFocusId);
            Long sumjobRate = this.reviewRepository.sumByJobFocusId(jobFocusId);
            double jobRate = 0;
            double rate = 0;
            if(sumRation != null) rate = sumRation/totalRation;
            if(sumjobRate!=null) jobRate = sumjobRate/totalJobRate;
            return CompletableFuture.completedFuture(
                    ResponseEntity.ok(
                            new JobFocusDetail(
                                    new JobFocusDtoToM(
                                            jobFocus.getId(),
                                            jobFocus.getSkill().getName(),
                                            jobRate,
                                            (long) totalJobRate,
                                            "$"+jobFocus.getPrice()+"-"+"$"+jobFocus.getPrice2(),
                                            jobFocus.getDescription(),
                                            jobFocus.getSkill().getImage()
                                    ),
                                    new JobFocusProviderView(
                                            provider.getBusinessName(),
                                            rate,
                                            (long) totalRation,
                                            provider.getProfile(),
                                            provider.getId()
                                    )
                            )
                    )
            );
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

}

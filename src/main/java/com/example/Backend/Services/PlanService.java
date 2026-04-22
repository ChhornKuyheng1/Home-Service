package com.example.Backend.Services;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.example.Backend.DTOS.AdminDashboards.PlanReports.PlanPriView;
import com.example.Backend.DTOS.AdminDashboards.PlanReports.PlanReport;
import com.example.Backend.DTOS.PlanDto;
import com.example.Backend.DTOS.PlanToUserDto;
import com.example.Backend.Events.Event;
import com.example.Backend.Models.Plan;
import com.example.Backend.Repositories.PlanRepository;
import com.example.Backend.Repositories.SubscriptionRepository;
import com.example.Backend.Responses.Messages.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class PlanService {
    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private Cloudinary cloudinary;



    @Async
    public CompletableFuture<ResponseEntity<?>> add(Plan plan, MultipartFile file) throws  Exception{
        if(this.planRepository.findByName(plan.getName()).isPresent())
            return CompletableFuture.completedFuture(ResponseEntity.status(409).body(new Message("Plan already exists")));
        plan.setCreateDate(LocalDate.now());
        if(!file.isEmpty()) {
            plan.setImage(
                    cloudinary.url().transformation(new Transformation()
                            .width(600)
                            .height(600)
                            .crop("fill"))
                    .generate(cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("public_id").toString())
            );
        }
        this.planRepository.save(plan);
        this.messagingTemplate.convertAndSend("/queue/plan",new Event(true));
        return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
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
    public CompletableFuture<ResponseEntity<?>> edite(long id, Plan plan, MultipartFile file) throws Exception{
        Plan existion = this.planRepository.findById(id).orElse(null);
        if(existion==null) return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("No date")));
        if(this.planRepository.findByNameAndId(plan.getName(),existion.getId()).isPresent())
            return CompletableFuture.completedFuture(ResponseEntity.status(409).body(new Message("Plan already exists")));
        existion.setName(plan.getName());
        existion.setPrice(plan.getPrice());
        existion.setDuration(plan.getDuration());
        existion.setDescription(plan.getDescription());
        existion.setMaxService(plan.getMaxService());
        existion.setMaxSkill(plan.getMaxSkill());
        existion.setStatus(plan.getStatus());
        if(file!=null && !file.isEmpty()){
            if(existion.getImage()==null || existion.getImage() !=""){
                existion.setImage(
                        cloudinary.url().transformation(new Transformation()
                                        .width(600)
                                        .height(600)
                                        .crop("fill")
                                )
                                .generate(cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("public_id").toString())
                );
                this.planRepository.save(existion);
                this.messagingTemplate.convertAndSend("/queue/plan",new Event(true));
                return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
            }
            cloudinary.uploader().destroy(getPublicIdFromUrl(existion.getImage()), ObjectUtils.emptyMap());
            existion.setImage(
                    cloudinary.url().transformation(new Transformation()
                                    .width(600)
                                    .height(600)
                                    .crop("fill")
                                   )
                            .generate(cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("public_id").toString())
            );
            this.planRepository.save(existion);
            this.messagingTemplate.convertAndSend("/queue/plan",new Event(true));
            return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
        }
        this.planRepository.save(existion);
        this.messagingTemplate.convertAndSend("/queue/plan",new Event(true));
        return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> delete(Long id){
      try{
          Plan plan = this.planRepository.findById(id).orElse(null);
          if(plan == null) return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("No data")));
          if(this.planRepository.countTotalSubscriptionPlan(id)>0) return CompletableFuture.completedFuture(ResponseEntity.status(401).body(new Message("This plan in provider use")));
          if(this.planRepository.countPlanInTransaction(plan.getId())>0) return CompletableFuture.completedFuture(ResponseEntity.status(401).body(new Message("This plan in provider use")));
          if(plan.getImage()!=null)  cloudinary.uploader().destroy(getPublicIdFromUrl(plan.getImage()), ObjectUtils.emptyMap());
          this.planRepository.delete(plan);
          this.messagingTemplate.convertAndSend("/queue/plan",new Event(true));
          return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
      } catch (Exception e) {
          return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
      }
    }

    public Plan getById(long id){
        return this.planRepository.findById(id).orElse(null);
    }


    public Plan getFreePlan(){
        return this.planRepository.findByPrice(0);
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> getAll(){
        List<PlanDto> planDtoList = new ArrayList<>();
        this.planRepository.findAll().forEach(
                plan -> {
                    PlanDto planDto = new PlanDto(
                            plan.getId(),
                            plan.getName(),
                            plan.getDuration(),
                            plan.getMaxService(),
                            plan.getMaxSkill(),
                            plan.getStatus(),
                            plan.getImage()
                    );
                    planDto.setPrice("Free");
                    if(plan.getPrice()!=0) planDto.setPrice(String.valueOf(plan.getPrice()));
                    planDtoList.add(planDto);
                }
        );
        return CompletableFuture.completedFuture(ResponseEntity.ok(planDtoList));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> getAllMonthLy(){
        List<PlanToUserDto> plans = new ArrayList<>();
        this.planRepository.findByDurationAndStatus("MonthLy","Active").forEach(
                plan -> {
                    plans.add(
                            new PlanToUserDto(
                                    plan.getId(),
                                    plan.getName(),
                                    plan.getDuration(),
                                    plan.getPrice(),
                                    plan.getMaxService(),
                                    plan.getMaxSkill(),
                                    plan.getImage()
                            )
                    );
                }
        );
        return CompletableFuture.completedFuture(ResponseEntity.ok(plans));
    }
    @Async
    public CompletableFuture<ResponseEntity<?>> getAllYearLy(){
        List<PlanToUserDto> plans = new ArrayList<>();
        this.planRepository.findByDurationAndStatus("YearLy","Active").forEach(
                plan -> {
                    plans.add(
                            new PlanToUserDto(
                                    plan.getId(),
                                    plan.getName(),
                                    plan.getDuration(),
                                    plan.getPrice(),
                                    plan.getMaxService(),
                                    plan.getMaxSkill(),
                                    plan.getImage()
                            )
                    );
                }
        );
        return CompletableFuture.completedFuture(ResponseEntity.ok(plans));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> getPlanName(){
        try{
            return CompletableFuture.completedFuture(ResponseEntity.ok(this.planRepository.findAllPlan()));
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> getPlansReport(){
        try{
            return CompletableFuture.completedFuture(ResponseEntity.ok(
                    new PlanReport(
                            new PlanPriView(
                                    this.subscriptionRepository.count(),
                                    this.subscriptionRepository.countByStatus("Active"),
                                    this.subscriptionRepository.countByStatus("Expired"),
                                    this.subscriptionRepository.findTopPlan().getName()
                            ),
                            this.planRepository.findToReport()
                    )
            ));
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

}

package com.example.Backend.Services;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.example.Backend.DTOS.AdminDashboards.ServiceReports.OverView;
import com.example.Backend.DTOS.AdminDashboards.ServiceReports.ServiceReport;
import com.example.Backend.DTOS.AdminDashboards.ServiceReports.ServiceReportList;
import com.example.Backend.DTOS.Categorys.CategoryDto;
import com.example.Backend.DTOS.Categorys.Mobiles.ServiceDto;
import com.example.Backend.Events.Event;
import com.example.Backend.Models.Services;
import com.example.Backend.Repositories.ServiceRepository;
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
public class CategoryService {
    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private Cloudinary cloudinary;

    @Async
    public CompletableFuture<ResponseEntity<?>> add(String name, MultipartFile file){
        try{
            if(serviceRepository.findByName(name).isPresent()) return CompletableFuture.completedFuture(ResponseEntity.status(409).body(new Message("Service already exists")));
            Services service = new Services();
            service.setName(name);
            service.setCreateDate(LocalDate.now());
            service.setStatus("ACTIVE");
            if(file!=null && !file.isEmpty())service.setImage( cloudinary.url().transformation(new Transformation()
                            .width(600)
                            .height(600)
                            .crop("fill")
                            .gravity("auto")
                            .fetchFormat("auto")
                            .quality("auto"))
                    .generate(cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("public_id").toString()));
            this.serviceRepository.save(service);
            this.messagingTemplate.convertAndSend(
                    "/queue/service",
                    new Event(true)
            );
            return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
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
    public CompletableFuture<ResponseEntity<?>> edite(long id, String name,MultipartFile file){
       try{
           Services service = this.serviceRepository.findById(id).orElse(null);
           if(service==null) return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("No data")));
           if(this.serviceRepository.findByNameAndId(name,service.getId()).isPresent())
               return CompletableFuture.completedFuture(ResponseEntity.status(409).body("Skill already exists"));
           service.setName(name);
           if (!file.isEmpty()) {
               if(service.getImage()==null || service.getImage() !=""){
                   service.setImage( cloudinary.url().transformation(new Transformation()
                                   .width(600)
                                   .height(600)
                                   .crop("fill")
                                   )
                           .generate(cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("public_id").toString()));
                   this.serviceRepository.save(service);
                   this.messagingTemplate.convertAndSend(
                           "/queue/service",
                           new Event(true)
                   );
                   return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
               }
               cloudinary.uploader().destroy(getPublicIdFromUrl(service.getImage()), ObjectUtils.emptyMap());
               service.setImage(cloudinary.url().transformation(new Transformation()
                               .width(600)
                               .height(600)
                               .crop("fill")
                               )
                       .generate(cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("public_id").toString()));
               this.serviceRepository.save(service);
               return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
           }
           this.serviceRepository.save(service);
           this.messagingTemplate.convertAndSend(
                   "/queue/service",
                   new Event(true)
           );
           return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
       } catch (Exception e) {
           return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
       }
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> delete(Long id){
       try{
           Services services = this.serviceRepository.findById(id).orElse(null);
           if(services==null) return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("No data")));
           if(this.serviceRepository.countServiceInSkill(services.getId())>0) return CompletableFuture.completedFuture(ResponseEntity.status(401).body(new Message("This service is in use and cannot delete.")));
           if(this.serviceRepository.countTotalJob(services.getId())>0) return CompletableFuture.completedFuture(ResponseEntity.status(401).body(new Message("This service is in use and cannot delete.")));
           if(services.getImage()!=null)  cloudinary.uploader().destroy(getPublicIdFromUrl(services.getImage()), ObjectUtils.emptyMap());
           this.serviceRepository.delete(services);
           this.messagingTemplate.convertAndSend(
                   "/queue/service",
                   new Event(true)
           );

           return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
       } catch (Exception e) {
           return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
       }
    }

    public List<Services> getAll(){
        return this.serviceRepository.findAll();
    }


    public Services getById(long id){
        return this.serviceRepository.findById(id).orElse(null);
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> getAllToAdmin(){
        List<CategoryDto> lists = new ArrayList<>();
        this.serviceRepository.findAll().forEach(
                services -> {
                    lists.add(
                            new CategoryDto(
                                    services.getId(),
                                    services.getName(),
                                    this.serviceRepository.countTotalJob(services.getId()),
                                    this.serviceRepository.countServiceInSkill(services.getId()),
                                    services.getStatus(),
                                    services.getImage()
                            )
                    );
                }
        );
        return CompletableFuture.completedFuture(ResponseEntity.ok(lists));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> getAllToMobile(){
       try{
           List<ServiceDto> services = new ArrayList<>();
           this.serviceRepository.findAll().forEach(
                   services1 -> {
                       services.add(
                               new ServiceDto(
                                       services1.getId(),
                                       services1.getName(),
                                       services1.getImage()
                               )
                       );
                   }
           );
           return CompletableFuture.completedFuture(ResponseEntity.ok(services));
       } catch (Exception e) {
           return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
       }
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> serviceReport(){
        try{
            List<ServiceReportList> services = new ArrayList<>();
            this.serviceRepository.findAll().forEach(
                    services1 -> {
                        this.serviceRepository.findSkillByServiceId(services1.getId()).forEach(
                                skill -> {
                                    services.add(
                                            new ServiceReportList(
                                                    skill.getId(),
                                                    skill.getName(),
                                                    services1.getName(),
                                                    this.serviceRepository.countProviderUseSkill(skill.getId()),
                                                    this.serviceRepository.countAllByServiceIdAndSkill(services1.getId(),skill.getId()),
                                                    this.serviceRepository.countByServiceIdAndSkill(services1.getId(),skill.getId(),"Completed"),
                                                    this.serviceRepository.countByServiceIdAndSkill(services1.getId(),skill.getId(),"Cancelled")
                                            )
                                    );
                                }
                        );
                    }
            );
            return CompletableFuture.completedFuture(
                    ResponseEntity.ok(
                            new ServiceReport(
                                    new OverView(
                                            this.serviceRepository.count(),
                                            this.serviceRepository.countSkill(),
                                            this.serviceRepository.countJob()
                                    ),
                                    services
                            )
                    )
            );
        } catch (Exception e) {
            return CompletableFuture.completedFuture(
                    ResponseEntity.status(500).body(
                            new Message(e.getMessage())
                    )
            );
        }
    }
}

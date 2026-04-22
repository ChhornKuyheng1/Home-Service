package com.example.Backend.Services;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.example.Backend.DTOS.Categorys.Mobiles.ServiceDto;
import com.example.Backend.DTOS.Skills.SkillAdminDto;
import com.example.Backend.DTOS.Skills.SkillDto;
import com.example.Backend.Events.Event;
import com.example.Backend.Models.Address;
import com.example.Backend.Models.Job;
import com.example.Backend.Models.Services;
import com.example.Backend.Models.Skill;
import com.example.Backend.Repositories.AddressRepository;
import com.example.Backend.Repositories.SkillRepository;
import com.example.Backend.Responses.Messages.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class SkillService {
    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private JobService jobService;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;


    @Async
    public CompletableFuture<ResponseEntity<?>> add(String name, long serviceId, MultipartFile file) throws IOException {
        Services  services = this.categoryService.getById(serviceId);
        if(services==null) return  CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("No data")));
        if(this.skillRepository.findByName(name).isPresent()) return CompletableFuture.completedFuture(ResponseEntity.status(409).body("Skill already exists"));
        this.skillRepository.save(
                new Skill(
                        name,
                        services,
                        LocalDate.now(),
                        cloudinary.url().transformation(new Transformation()
                                        .width(600)
                                        .height(600)
                                        .crop("fill")
                                        .gravity("auto")
                                        .fetchFormat("auto")
                                        .quality("auto"))
                                .generate(cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("public_id").toString())
                )
        );
        this.messagingTemplate.convertAndSend("/queue/skill",new Event(true));
        this.messagingTemplate.convertAndSend(
                "/queue/service/report",
                new Event(true)
        );
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
    public CompletableFuture<ResponseEntity<?>> edite(long id,String name,MultipartFile file,long serviceId) throws IOException{
        Services services = this.categoryService.getById(serviceId);
        Skill skill = this.skillRepository.findById(id).orElse(null);
        if(skill== null) return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("No data")));
        if(services==null) return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("No data")));
        if(this.skillRepository.findByNameAndId(name,id).isPresent())
            return CompletableFuture.completedFuture(ResponseEntity.status(409).body("Skill already exists"));
        skill.setName(name);
        skill.setService(services);
        if(file !=null && !file.isEmpty()){
            if(skill.getImage()==null || skill.getImage()!="") {
                skill.setImage( cloudinary.url().transformation(new Transformation()
                                .width(600)
                                .height(600)
                                .crop("fill")
                                )
                        .generate(cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("public_id").toString()));
                this.skillRepository.save(skill);
                this.messagingTemplate.convertAndSend("/queue/skill",new Event(true));
                return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
            }
            cloudinary.uploader().destroy(getPublicIdFromUrl(skill.getImage()), ObjectUtils.emptyMap());
            skill.setImage( cloudinary.url().transformation(new Transformation()
                            .width(600)
                            .height(600)
                            .crop("fill")
                            )
                    .generate(cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("public_id").toString()));
            this.skillRepository.save(skill);
            this.messagingTemplate.convertAndSend("/queue/skill",new Event(true));
            return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
        }
        this.skillRepository.save(skill);
        this.messagingTemplate.convertAndSend("/queue/skill",new Event(true));
        return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> delete(Long id) throws Exception{
        Skill skill = this.skillRepository.findById(id).orElse(null);
        if(skill==null) return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("No data")));
        if(this.skillRepository.countTotalJobFocus(skill.getId())>0) return CompletableFuture.completedFuture(ResponseEntity.status(401).body(new Message("This skill in provider use")));
        if(skill.getImage()!=null) cloudinary.uploader().destroy(getPublicIdFromUrl(skill.getImage()), ObjectUtils.emptyMap());
        this.skillRepository.delete(skill);
        this.messagingTemplate.convertAndSend("/queue/skill",new Event(true));
        return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> getAll(){
        List<SkillAdminDto> skillAdminDtos = new ArrayList<>();
        this.skillRepository.findAll().forEach(
                skill -> {
                    skillAdminDtos.add(
                            new SkillAdminDto(
                                    skill.getId(),
                                    skill.getName(),
                                    skill.getService().getName(),
                                    this.skillRepository.countTotalJobFocus(skill.getId()),
                                    this.skillRepository.countTotalBooking(skill.getId()),
                                    skill.getImage(),
                                    skill.getService().getId()
                            )
                    );
                }
        );
        return CompletableFuture.completedFuture(ResponseEntity.ok(skillAdminDtos));
    }

    public Skill getById(long id){
        return this.skillRepository.findById(id).orElse(null);
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> getAllSkillToProviderAdd(Long jobId){
        Job job = this.jobService.getById(jobId);
        List<SkillDto> skills = new ArrayList<>();
        this.skillRepository.findByService_Id(job.getService().getId(),job.getId()).forEach(
                skill -> {
                    skills.add(
                            new SkillDto(
                                    skill.getId(),
                                    skill.getName()
                            )
                    );
                }
        );
        return CompletableFuture.completedFuture(ResponseEntity.ok(skills));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> getByServiceId(Long serviceId){
        try{
            List<ServiceDto> skills = new ArrayList<>();
            this.skillRepository.findByService_Id(serviceId).forEach(
                    skill -> {
                        skills.add(
                                new ServiceDto(
                                        skill.getId(),
                                        skill.getName(),
                                        skill.getImage()
                                )
                        );
                    }
            );
            return CompletableFuture.completedFuture(ResponseEntity.ok(skills));
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> getSkillNearCustomer(Long userId){
        try{
             Address address = this.addressRepository.findByUser_IdAndStatus(userId,"Default");
             return CompletableFuture.completedFuture(
                     ResponseEntity.ok(this.skillRepository.findSkillNearCustomer(address.getLat(),address.getLon()))
             );
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }
}

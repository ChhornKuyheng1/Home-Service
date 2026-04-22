package com.example.Backend.Controllers;

import com.example.Backend.Responses.Messages.Message;
import com.example.Backend.Services.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@CrossOrigin
@RequestMapping("auth/skill")
public class SkillController {
    @Autowired
    private SkillService skillService;

    @GetMapping("/all/by/job/id")
    public CompletableFuture<ResponseEntity<?>> getAllSkillByJob(@RequestParam Long jobId){
        try{
            return this.skillService.getAllSkillToProviderAdd(jobId);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/by/service")
    public CompletableFuture<ResponseEntity<?>> getByService(@RequestParam Long serviceId){
        return this.skillService.getByServiceId(serviceId);
    }

    @GetMapping("/near")
    public CompletableFuture<ResponseEntity<?>> getSkillNearCustomer(@RequestParam Long userId){
        return this.skillService.getSkillNearCustomer(userId);
    }
}

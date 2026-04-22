package com.example.Backend.Controllers;

import com.example.Backend.Responses.Messages.Message;
import com.example.Backend.Services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@CrossOrigin
@RequestMapping("user/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping("/add")
    public CompletableFuture<ResponseEntity<?>>  add(@RequestParam long providerId,@RequestParam long serviceId){
        try{
            return this.jobService.add(providerId,serviceId);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @PutMapping("/active")
    public CompletableFuture<ResponseEntity<?>> activeJob(@RequestParam long jobId){
        try{
            return this.jobService.setStatusActive(jobId);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @PutMapping("/inactive")
    public CompletableFuture<ResponseEntity<?>>  desActive(@RequestParam long jobId){
        try{
            return this.jobService.setStatusInactive(jobId);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/get/job/by/provider/id")
    public CompletableFuture<ResponseEntity<?>> getAllJobByProviderId(@RequestParam Long providerId){
        try{
            return this.jobService.getAllJobByProviderId(providerId);
        }catch(Exception e){
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

}

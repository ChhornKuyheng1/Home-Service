package com.example.Backend.Controllers;

import com.example.Backend.Requests.JobFocusRequest;
import com.example.Backend.Requests.UpdateJobFocusRequest;
import com.example.Backend.Responses.Messages.Message;
import com.example.Backend.Services.JobFocusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;

@RestController
@CrossOrigin
@RequestMapping("user/job/focus")
public class JobFocusController {

    @Autowired
    private JobFocusService jobFocusService;

    @PostMapping("/add")
    public CompletableFuture<ResponseEntity<?>>  add(@ModelAttribute JobFocusRequest job){
        try{
            return this.jobFocusService.add(job);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @PutMapping("/update")
    public CompletableFuture<ResponseEntity<?>> update(@ModelAttribute UpdateJobFocusRequest job){
        try{
            return this.jobFocusService.edite(job);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/get/by/provider/id")
    public CompletableFuture<ResponseEntity<?>>  getJobFocusByProviderId(@RequestParam Long providerId){
        try{
            return this.jobFocusService.getByProviderId(providerId);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @PutMapping("/active")
    public CompletableFuture<ResponseEntity<?>>  setActive(@RequestParam Long jobFocusId){
        try{
            return this.jobFocusService.setStatusActive(jobFocusId);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @PutMapping("/inactive")
    public CompletableFuture<ResponseEntity<?>>  setInActive(@RequestParam Long jobFocusId){
        try{
            return this.jobFocusService.setStatusInActive(jobFocusId);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/get/by/id")
    public CompletableFuture<ResponseEntity<?>> getToview(@RequestParam Long jobFocusId){
        return this.jobFocusService.getToView(jobFocusId);
    }
}

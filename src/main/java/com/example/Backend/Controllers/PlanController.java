package com.example.Backend.Controllers;

import com.example.Backend.Responses.Messages.Message;
import com.example.Backend.Services.PlanService;
import org.hibernate.boot.internal.Abstract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@CrossOrigin
@RequestMapping("/auth/plan")
public class PlanController {

    @Autowired
    private PlanService planService;

    @GetMapping("/monthly")
    public CompletableFuture<ResponseEntity<?>> getAllMonthLy(){
        try{
            return this.planService.getAllMonthLy();
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/yearly")
    public CompletableFuture<ResponseEntity<?>> getAllYearLy(){
        try{
            return this.planService.getAllYearLy();
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }
}

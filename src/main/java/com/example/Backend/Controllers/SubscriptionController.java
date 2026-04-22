package com.example.Backend.Controllers;

import com.example.Backend.DTOS.SubscriptionExpired;
import com.example.Backend.Responses.Messages.Message;
import com.example.Backend.Services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@CrossOrigin
@RequestMapping("user/subscription")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping("/add")
    public CompletableFuture<ResponseEntity<?>> subscription(@RequestParam Long providerId,@RequestParam Long planId){
        try{
            return this.subscriptionService.transaction(providerId,planId);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @PostMapping("/first")
    public CompletableFuture<ResponseEntity<?>> firstSubscription(@RequestParam Long providerId){
        try{
            return this.subscriptionService.firstSubscription(providerId);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @PostMapping("/pay")
    public CompletableFuture<ResponseEntity<?>> pay(@RequestParam Long transactionId){
        try{
            return this.subscriptionService.payment(transactionId);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/check")
    public ResponseEntity<?> checkSubscription(@RequestParam Long providerId){

        return this.subscriptionService.checkSubscriptionToProvider(providerId);

    }
}

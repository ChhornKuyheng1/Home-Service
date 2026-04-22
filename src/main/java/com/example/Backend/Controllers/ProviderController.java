package com.example.Backend.Controllers;

import com.example.Backend.DTOS.MobileProviders.Profiles.Personal;
import com.example.Backend.Requests.PersonalRequest;
import com.example.Backend.Requests.ProviderProfile;
import com.example.Backend.Responses.Messages.Message;
import com.example.Backend.Services.BookingService;
import com.example.Backend.Services.ProviderService;
import com.example.Backend.Services.UserService;
import org.hibernate.dialect.function.array.ArrayAndElementArgumentTypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@CrossOrigin
@RequestMapping("user/provider")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    @GetMapping("/revenue/analytics/1day")
    public CompletableFuture<ResponseEntity<?>> revenueAnalytics1Day(@RequestParam Long id){
        try {
            return this.providerService.revenueAnalytics1Day(id);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/revenue/analytics/7day")
    public CompletableFuture<ResponseEntity<?>> revenueAnalytics7Day(@RequestParam Long id){
        try {
            return this.providerService.revenueAnalytics7Day(id);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/revenue/analytics/1month")
    public CompletableFuture<ResponseEntity<?>> revenueAnalytics1Month(@RequestParam Long id){
        try {
            return this.providerService.revenueAnalytics1Month(id);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    // Insights

    @GetMapping("/insights")
    public CompletableFuture<ResponseEntity<?>> insights(@RequestParam Long id){
        try{
            return this.providerService.insights(id);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    // Plan

    @GetMapping("/plan")
    public CompletableFuture<ResponseEntity<?>> getPlan(@RequestParam Long id){
        try{
            return this.providerService.getSubscription(id);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }



    @GetMapping("/profile")
    public CompletableFuture<ResponseEntity<?>> getProfile(@RequestParam Long id){
        try{
           return this.providerService.getToSettings(id);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/personal")
    public CompletableFuture<ResponseEntity<?>> getPersonal(@RequestParam Long id){
        try {
            return this.providerService.getPersonal(id);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/profile/view")
    public CompletableFuture<ResponseEntity<?>> getProviderProfile(@RequestParam Long providerId){
        try{
            return this.providerService.getProviderViewProfile(providerId);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @PutMapping("/edite/personal")
    public CompletableFuture<ResponseEntity<?>> editePersonal(@ModelAttribute PersonalRequest personal){
        try{
            return this.providerService.editePersonal(personal.getProviderId(),personal);
        }catch (Exception e){
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/service")
    public CompletableFuture<ResponseEntity<?>> getService(@RequestParam Long id){
        try{
            return this.providerService.getToService(id);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/services")
    public CompletableFuture<ResponseEntity<?>> getServiceToCustomer(@RequestParam Long id){
        try{
            return this.providerService.getToServiceToCustomer(id);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @PutMapping("/edite/tel")
    public CompletableFuture<ResponseEntity<?>> editeTel(@RequestParam Long id,@RequestParam String tel){
        try{
            return this.providerService.editeTel(id,tel);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @PutMapping("/edite/email")
    public CompletableFuture<ResponseEntity<?>> editeEmail(@RequestParam Long id,@RequestParam String email){
        try{
            return this.providerService.editeEmail(id,email);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }



    @GetMapping("/near/customer")
    public CompletableFuture<ResponseEntity<?>> getProviderNearCustomer(@RequestParam Long userId){
        try{
            return this.providerService.getProviderNearCustomer(userId);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/near/customer/by/service")
    public CompletableFuture<ResponseEntity<?>> getProviderNearCustomerByServiceId(@RequestParam Long userId,@RequestParam Long serviceId){
        try{
            return this.providerService.getProviderNearViewByServiceId(userId,serviceId);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/near/customer/by/skill")
    public CompletableFuture<ResponseEntity<?>> getProviderNearCustomerBySkillId(@RequestParam Long userId,@RequestParam Long skillId){
        return this.providerService.getProviderNearBySkill(userId,skillId);
    }

    @GetMapping("/near/customer/view")
    public CompletableFuture<ResponseEntity<?>> getProviderNearCustomerView(@RequestParam Long userId){
        try{
            return this.providerService.getProviderNearView(userId);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/about")
    public CompletableFuture<ResponseEntity<?>> getAboutProviderById(@RequestParam Long providerId){
        try{
            return this.providerService.getAboutProviderById(providerId);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("service/near")
    public CompletableFuture<ResponseEntity<?>> getServiceNearCustomer(@RequestParam Long userId){
        return this.providerService.getServiceNearCustomer(userId);
    }

    @PutMapping("/set/profile")
    public CompletableFuture<ResponseEntity<?>>setProfile(@ModelAttribute ProviderProfile profile){
        return this.userService.setProfile(profile.getProviderId(),profile.getImage());
    }

    @PutMapping("/online")
    public CompletableFuture<ResponseEntity<?>> online(@RequestParam Long providerId){
        return this.providerService.setStatus(providerId,"Active");
    }

    @PutMapping("/offline")
    public CompletableFuture<ResponseEntity<?>> offline(@RequestParam Long providerId){
        return this.providerService.setStatus(providerId,"Inactive");
    }

    @GetMapping("/home/profile")
    public CompletableFuture<ResponseEntity<?>> getToHome(@RequestParam Long providerId){
        return this.providerService.getProviderProfile(providerId);
    }

    @GetMapping("/new/complete")
    public CompletableFuture<ResponseEntity<?>> getNewComplete(@RequestParam Long providerId){
        return this.providerService.getNewCompleted(providerId);
    }
}

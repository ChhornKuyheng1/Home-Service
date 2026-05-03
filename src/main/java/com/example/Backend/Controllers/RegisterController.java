package com.example.Backend.Controllers;

import com.example.Backend.Models.Provider;
import com.example.Backend.Models.User;
import com.example.Backend.Requests.*;
import com.example.Backend.Responses.Messages.Message;
import com.example.Backend.Services.AddressService;
import com.example.Backend.Services.AdminService;
import com.example.Backend.Services.ProviderService;
import com.example.Backend.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@CrossOrigin
@RequestMapping("auth/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProviderService providerService;

    @Autowired
    private AdminService adminService;

    //Admin Login
    @GetMapping("/admin/login")
    public ResponseEntity<?> adminLogin(@RequestParam String email,@RequestParam String password){
        try{
            return this.adminService.getByEmailAndPassword(email,password);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Message(e.getMessage()));
        }
    }

    @PutMapping("/admin/cheng/password")
    public CompletableFuture<ResponseEntity<?>>AdminChengPassword(@RequestParam String email ,@RequestParam String password){
       return this.adminService.chengPassword(email,password);
    }

    //User Login
    @GetMapping("/user/login")
    public  CompletableFuture<ResponseEntity<?>> userLogin(@RequestParam String tel){
        try{
            return this.userService.getUserByTel(tel);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @PostMapping("/set/pending")
    public ResponseEntity<?> gg(@RequestParam Long providerId){
        return this.providerService.setp(providerId);
    }


    @GetMapping("/provider/login")
    public CompletableFuture<ResponseEntity<?>> providerLogin(@RequestParam Long userId){
        try{
            return this.providerService.getByUserId(userId);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    //User
    @PostMapping("/user")
    public CompletableFuture<ResponseEntity<?>> createUser(@ModelAttribute UserRequest user){
        try{
            return this.userService.add(
                    new User(user.getName(),user.getGender(),user.getTel(),user.getDob()),
                    user.getLat(),
                    user.getLon(),
                    user.getAddressName()
            );
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

//    //Provider
//    @PostMapping("/provider")
//    public CompletableFuture<ResponseEntity<?>> createProvider(@ModelAttribute ProviderRequest provider){
//        try{
//            return this.providerService.add(
//                    new Provider(
//                          provider.getEmail(),
//                          provider.getStartDate(),
//                          provider.getEndDate(),
//                          provider.getStartTime(),
//                          provider.getEndTime(),
//                            provider.getBusinessName()
//                    ),
//                    provider.getUserId());
//        } catch (Exception e) {
//            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
//        }
//    }

    @PostMapping("/provider/add")
    public CompletableFuture<ResponseEntity<?>> createProvider(@ModelAttribute ProviderRequest provider){

            return this.providerService.addProvider(
                    new Provider(
                            provider.getEmail(),
                            provider.getStartDate(),
                            provider.getEndDate(),
                            provider.getStartTime(),
                            provider.getEndTime(),
                            provider.getBusinessName(),
                            provider.getLat(),
                            provider.getLon(),
                            provider.getServiceRadius()
                    ),
                    provider.getUserId(),
                    provider.getIdCard(),
                    provider.getImage()
            );

    }

    @PostMapping("/check/email")
    public CompletableFuture<ResponseEntity<?>> checkProviderEmail(@RequestParam String email , @RequestParam Long userId){
        return this.providerService.checkEmail(email,userId);
    }

    @PostMapping("/provider/address")
    public CompletableFuture<ResponseEntity<?>> setProviderAddress(@ModelAttribute AddressProviderRequest address){
        try{
            return this.providerService.setAddressWork(address.getProviderId(),address.getLat(),address.getLon(),address.getServiceRadius());
        }catch (Exception e){
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @PostMapping("/provider/id/card")
    public CompletableFuture<ResponseEntity<?>> setIdCardProvider(@ModelAttribute ProviderImageRequest image){
        try{
            return this.providerService.setDocument(image.getProviderId(), image.getIdCard(),image.getImage());
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

}

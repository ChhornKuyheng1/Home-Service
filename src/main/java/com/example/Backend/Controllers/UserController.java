package com.example.Backend.Controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.Backend.Requests.UserProfile;
import com.example.Backend.Requests.UserUpdateRequest;
import com.example.Backend.Responses.Messages.Message;
import com.example.Backend.Services.NotificationService;
import com.example.Backend.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.CompletableFuture;

@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/profile")
    public CompletableFuture<ResponseEntity<?>> setProfile(@ModelAttribute UserProfile profile){
        try{
            return this.userService.setProfile(profile.getId(), profile.getImage());
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/profile")
    public CompletableFuture<ResponseEntity<?>> getProfile(@RequestParam Long userId){
        return this.userService.getUserProfile(userId);
    }

    @PutMapping("/edite/profile")
    public CompletableFuture<ResponseEntity<?>> editeProfile(@ModelAttribute UserUpdateRequest user){
        return this.userService.editeProfile(user);
    }

    @PostMapping("/set/fmc/token")
    public CompletableFuture<ResponseEntity<?>> setFmcToken(@RequestParam Long userId,@RequestParam String fmcToken){
        if(fmcToken.isEmpty()|| fmcToken == null) return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("FCM Token is Null")));
        return this.userService.setFmcToken(userId,fmcToken);
    }

    @GetMapping("/notification")
    public CompletableFuture<ResponseEntity<?>> getNotification(@RequestParam Long userId){
        return this.notificationService.getToUserView(userId);
    }

    @PutMapping("/notification/read")
    public CompletableFuture<ResponseEntity<?>> notificationRead(@RequestParam Long notificationId){
        return this.notificationService.setIsRead(notificationId);
    }

    @PutMapping("/notification/read/all")
    public CompletableFuture<ResponseEntity<?>> readNotificationAll(@RequestParam Long userId){
        return this.notificationService.readAll(userId);
    }
}

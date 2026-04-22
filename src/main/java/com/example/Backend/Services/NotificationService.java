package com.example.Backend.Services;

import com.example.Backend.Components.WebSocketUserRegistry;
import com.example.Backend.DTOS.MessageDto;
import com.example.Backend.DTOS.Providers.ProviderRejectedDto;
import com.example.Backend.Models.Notification;
import com.example.Backend.Models.Provider;
import com.example.Backend.Models.Subscription;
import com.example.Backend.Models.User;
import com.example.Backend.Repositories.NotificationRepository;
import com.example.Backend.Repositories.ProviderRepository;
import com.example.Backend.Repositories.SubscriptionRepository;
import com.example.Backend.Repositories.UserRepository;
import com.example.Backend.Responses.Messages.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class NotificationService {

    @Autowired
    private  NotificationRepository notificationRepository;

    @Autowired
    public WebSocketUserRegistry userRegistry;

    @Autowired
    private  SimpMessagingTemplate messagingTemplate;

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FCMService fcmService;

    @Async
    public  void sendMessage(User user,String title,String message,String type,Long referenceId){
        if(this.userRegistry.isOnline(user.getId().toString())){
            this.notificationRepository.save(
                    new Notification(
                            user,
                            title,
                            message,
                            type,
                            referenceId,
                            false,
                            LocalDateTime.now()
                    )
            );
            this.messagingTemplate.convertAndSendToUser(user.getId().toString(),
                    "/queue/notification",
                    new MessageDto(
                            title,
                            message,
                            type,
                            referenceId,
                            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a"))
                    )
            );
            this.fcmService.sendNotification(user.getFcmToken(),title,message,referenceId,type);
            return;
        }
        this.notificationRepository.save(
                new Notification(
                        user,
                        title,
                        message,
                        type,
                        referenceId,
                        false,
                        LocalDateTime.now()
                )
        );
        this.fcmService.sendNotificationOffline(user.getFcmToken(),title,message,referenceId,type);

    }

    @Async
    public void senMessageToUserIsOnline(Long id){
        this.notificationRepository.findByUserIdAndIsRead(id).forEach(
               notification -> {
                   this.messagingTemplate.convertAndSendToUser(
                           notification.getUser().getId().toString(),
                           "/queue/notification",
                           new MessageDto(
                                   notification.getId(),
                                   notification.getTitle(),
                                   notification.getMessage(),
                                   notification.getType(),
                                   notification.getReferenceId(),
                                   notification.getCreateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a")),
                                   notification.isRead()
                           )
                   );
                   this.fcmService.sendNotification(notification.getUser().getFcmToken(),notification.getTitle(),notification.getMessage(),notification.getReferenceId(),notification.getType());
               }
        );
    }

    @Async
    public void delete(Notification notification){
        this.notificationRepository.delete(notification);
    }

    public Notification getNotificationByUserIdAndType(Long userId,String type){
        return this.notificationRepository.findByTypes(type,userId);
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> getAllProviderRejected(){
        try{
            List<ProviderRejectedDto> providers = new ArrayList<>();
            this.notificationRepository.findByType("REJECTED").forEach(
                    notification -> {
                        Provider provider = this.providerRepository.findById(notification.getUser().getId()).orElse(new Provider());
                        providers.add(
                                new ProviderRejectedDto(
                                        provider.getId(),
                                        provider.getBusinessName(),
                                        provider.getEmail(),
                                        provider.getUser().getTel(),
                                        provider.getCreateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a")),
                                        notification.getCreateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a")),
                                        notification.getMessage(),
                                        provider.getJobStatus(),
                                        provider.getProfile()
                                )
                        );
                    }
            );
            return CompletableFuture.completedFuture(ResponseEntity.ok(providers));
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> getToUserView(Long userId){
        try{
            List<MessageDto> message = new ArrayList<>();
            this.notificationRepository.findByUserIdAndIsRead(userId).forEach(
                    notification -> {
                        message.add(
                                new MessageDto(
                                        notification.getId(),
                                        notification.getTitle(),
                                        notification.getMessage(),
                                        notification.getType(),
                                        notification.getReferenceId(),
                                        notification.getCreateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a")),
                                        notification.isRead()
                                )
                        );
                    }
            );
            return CompletableFuture.completedFuture(
                    ResponseEntity.ok(message)
            );
        } catch (Exception e) {
            return CompletableFuture.completedFuture(
                    ResponseEntity.status(500).body(new Message(e.getMessage()))
            );
        }
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> setIsRead(Long id){
        try{
            Notification notification = this.notificationRepository.findById(id).orElse(null);
            if(notification == null) return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("No data")));
            notification.setRead(true);
            this.notificationRepository.save(notification);
            return CompletableFuture.completedFuture(
                    ResponseEntity.ok(new Message("Successfully"))
            );
        } catch (Exception e) {
            return CompletableFuture.completedFuture(
                    ResponseEntity.status(500).body(new Message(e.getMessage()))
            );
        }
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> readAll(Long userId){
        try {
            this.notificationRepository.updateReadAllByUserId(userId);
            return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
        } catch (Exception e) {
            return CompletableFuture.completedFuture(
                    ResponseEntity.status(500).body(new Message(e.getMessage()))
            );
        }
    }


    public  void checkSubscriptionExpireDate(Long userId){
        this.checkSubscription(userId);
    }

    public boolean  checkSubscriptionToProvider(Long providerId){
        try{
            Subscription subscription = this.subscriptionRepository.findByProvider_Id(providerId);

            Provider provider = this.providerRepository.findById(providerId).orElse(null);

            if(subscription!=null){

                    if(subscription.getEndDate().isBefore(LocalDate.now())){
                        provider.setStatus("Inactive");
                        this.providerRepository.save(provider);
                        return true;
                    }
                    return false;
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Async
    private void checkSubscription(Long providerId){
        try{

            Subscription subscription = this.subscriptionRepository.findByProvider_Id(providerId);

            Provider provider = this.providerRepository.findById(providerId).orElse(null);

            if(subscription!=null){
                if(subscription.getStatus().equals("Active")){
                    if(subscription.getEndDate().isBefore(LocalDate.now())){

                        subscription.setStatus("Expired");

                        provider.setStatus("Inactive");

                        this.subscriptionRepository.save(subscription);

                        this.providerRepository.save(provider);

                        this.sendMessage(provider.getUser(),"SUBSCRIPTION","Your subscription is expired","SUBSCRIPTION",provider.getId());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

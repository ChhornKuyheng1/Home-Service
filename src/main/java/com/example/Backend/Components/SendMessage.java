package com.example.Backend.Components;

import com.example.Backend.Services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import java.security.Principal;

@Component
public class SendMessage {

    @Autowired
    private NotificationService NotificationService;

    @EventListener
    public void onConnect(SessionSubscribeEvent event) {
        try{
            Principal principal = event.getUser();
            if(principal !=null){

                NotificationService.checkSubscriptionExpireDate(Long.valueOf(principal.getName()));

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

package com.example.Backend.Services;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class FCMService {

    @Async
    public void sendNotification(String token, String title, String body,Long id,String type) {
        try {
            Message message = Message.builder()
                    .setToken(token)
                    .setNotification(Notification.builder()
                            .setTitle(title)
                            .setBody(body)
                            .build())
                    .putData("id",id.toString())
                    .putData("type",type)
                    .build();
            String i =  FirebaseMessaging.getInstance().send(message);
            System.out.println(i);
        } catch (Exception e) {
           System.out.println(e.getMessage());
        }
    }

    @Async
    public void sendNotificationOffline(String token, String title, String body,Long id,String type) {
        try {
            Message message = Message.builder()
                    .setToken(token)
                    .setNotification(Notification.builder()
                            .setTitle(title)
                            .setBody(body)
                            .build())
                    .putData("title",title)
                    .putData("body",body)
                    .putData("id",id.toString())
                    .putData("type",type)
                    .build();
         String i =  FirebaseMessaging.getInstance().send(message);
         System.out.println(i);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

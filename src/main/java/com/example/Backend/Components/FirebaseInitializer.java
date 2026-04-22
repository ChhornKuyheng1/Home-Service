package com.example.Backend.Components;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class FirebaseInitializer {

//    @PostConstruct
//    public void initialize() throws IOException {
//        InputStream serviceAccount = getClass().getClassLoader()
//                .getResourceAsStream("");
//
//        if (FirebaseApp.getApps().isEmpty()) {
//            FirebaseOptions options = FirebaseOptions.builder()
//                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                    .build();
//            FirebaseApp.initializeApp(options);
//        }
//
//        System.out.println("Firebase Initialized Successfully!");
//    }
}


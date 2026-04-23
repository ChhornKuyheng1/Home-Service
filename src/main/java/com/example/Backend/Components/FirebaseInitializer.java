package com.example.Backend.Components;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import java.io.IOException;
import java.io.InputStream;


@Component
public class FirebaseInitializer {

    @PostConstruct
    public void initialize() {

        try {

            String firebaseConfig = System.getenv("FIREBASE_CONFIG");

            if (firebaseConfig == null || firebaseConfig.isEmpty()) {
                throw new RuntimeException("FIREBASE_CONFIG is missing in environment");
            }

            InputStream serviceAccount = new ByteArrayInputStream(
                    firebaseConfig.getBytes(StandardCharsets.UTF_8)
            );

            if (FirebaseApp.getApps().isEmpty()) {

                FirebaseOptions options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .build();

                FirebaseApp.initializeApp(options);
            }

            System.out.println(" Firebase Initialized Successfully!");

        } catch (Exception e) {
            System.err.println("Firebase init failed: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }


}


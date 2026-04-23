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

@Component
public class FirebaseInitializer {

    @PostConstruct
    public void initialize() throws IOException {

        String firebaseConfig = System.getenv("FIREBASE_CONFIG");

        InputStream serviceAccount = new ByteArrayInputStream(
                firebaseConfig.getBytes(StandardCharsets.UTF_8)
        );

        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);
        }
    }
}


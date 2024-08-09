package com.timmy.TimmyRoom.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;

@Service
public class FirebaseInitialization {

    @PostConstruct
    public void initialize(){
        try{
            FileInputStream serviceAccount = new FileInputStream("./src/main/resources/firebase/timmyroom-8d5dd-firebase-adminsdk-yaj9u-6fb31e4259.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}

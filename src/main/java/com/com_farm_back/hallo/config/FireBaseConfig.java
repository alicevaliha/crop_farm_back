package com.com_farm_back.hallo.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FireBaseConfig {

    // @Bean
    // public FirebaseApp firebaseApp() throws IOException {
    //     GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream("ionic-2742c.json"));
    //     FirebaseOptions options = new FirebaseOptions.Builder()
    //             .setCredentials(credentials)
    //             .setDatabaseUrl("https://ionic-2742c.firebaseio.com")
    //             .build();

    //     return FirebaseApp.initializeApp(options);
    // }
}


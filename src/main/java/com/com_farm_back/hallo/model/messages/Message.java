package com.com_farm_back.hallo.model.messages;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;

@Document(collection = "messages")
public class Message {
    @Id
    private String id_message;
    private String id_envoyeur;
    private String id_receveur;
    private String message;
    private Timestamp time_envoi;
    private int corbeille;

    public Message(){} 

    

    public Message(String id_message, String id_envoyeur, String id_receveur, String message, Timestamp time_envoi,
            int corbeille) {
        this.setId_message(id_message);
        this.setId_envoyeur(id_envoyeur);
        this.setId_receveur(id_receveur);
        this.setMessage(message);
        this.setTime_envoi(time_envoi);
        this.setCorbeille(corbeille);
    }



    public String getId_message() {
        return id_message;
    }
    public void setId_message(String id_message) {
        this.id_message = id_message;
    }
    public String getId_envoyeur() {
        return id_envoyeur;
    }
    public void setId_envoyeur(String id_envoyeur) {
        this.id_envoyeur = id_envoyeur;
    }
    public String getId_receveur() {
        return id_receveur;
    }
    public void setId_receveur(String id_receveur) {
        this.id_receveur = id_receveur;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Timestamp getTime_envoi() {
        return time_envoi;
    }
    public void setTime_envoi(Timestamp time_envoi) {
        this.time_envoi = time_envoi;
    }
    public int getCorbeille() {
        return corbeille;
    }
    public void setCorbeille(int corbeille) {
        this.corbeille = corbeille;
    }

    // Getters and setters
}


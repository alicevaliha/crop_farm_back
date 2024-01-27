package com.com_farm_back.hallo.model.proprietaire;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "discussions")
public class Messagerie {
    @Id
    String id;
    String idEnvoyeur;
    String idReceveur;
    LocalDateTime dateHeureEnvoie = LocalDateTime.now();
    String message;
    int status = 1;

    public Messagerie() {
    }

    public Messagerie(String idEnvoyeur) {
        this.setIdEnvoyeur(idEnvoyeur);
    }

    public Messagerie(String idEnvoyeur, String idReceveur) {
        this.setIdEnvoyeur(idEnvoyeur);
        this.setIdReceveur(idReceveur);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdEnvoyeur() {
        return idEnvoyeur;
    }

    public void setIdEnvoyeur(String idEnvoyeur) {
        this.idEnvoyeur = idEnvoyeur;
    }

    public String getIdReceveur() {
        return idReceveur;
    }

    public void setIdReceveur(String idReceveur) {
        this.idReceveur = idReceveur;
    }

    public LocalDateTime getDateHeureEnvoie() {
        return dateHeureEnvoie;
    }

    public void setDateHeureEnvoie(LocalDateTime dateHeureEnvoie) {
        // LocalDateTime now = LocalDateTime.now();
        // String formattedDateTime = now.format(DateTimeFormatter.ISO_DATE_TIME);
        this.dateHeureEnvoie = dateHeureEnvoie;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}

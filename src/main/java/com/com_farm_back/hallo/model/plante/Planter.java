package com.com_farm_back.hallo.model.plante;

import com.google.cloud.Timestamp;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Planter {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   

    private int id_plantation;
    private int id_parcelle;
    private int id_plante;
    private Timestamp dateaction;
    private int etat;

    
    public Planter(){}
    
    public Planter(int id_plantation, int id_parcelle, int id_plante, Timestamp dateaction, int etat) {
        setId_plantation(id_plantation);
        setId_parcelle(id_parcelle);
        setId_plante(id_plante);
        setDateaction(dateaction);
        setEtat(etat);
    }

    public int getId_plantation() {
        return id_plantation;
    }
    public void setId_plantation(int id_plantation) {
        this.id_plantation = id_plantation;
    }
    public int getId_parcelle() {
        return id_parcelle;
    }
    public void setId_parcelle(int id_parcelle) {
        this.id_parcelle = id_parcelle;
    }
    public int getId_plante() {
        return id_plante;
    }
    public void setId_plante(int id_plante) {
        this.id_plante = id_plante;
    }
    public Timestamp getDateaction() {
        return dateaction;
    }
    public void setDateaction(Timestamp dateaction) {
        this.dateaction = dateaction;
    }
    public int getEtat() {
        return etat;
    }
    public void setEtat(int etat) {
        this.etat = etat;
    }



}

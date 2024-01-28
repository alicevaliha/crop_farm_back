package com.com_farm_back.hallo.model.categorie;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Categories_parcelle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id_categories_parcelle;
    private int id_parcelle;
    private int id_categorie_culture;
    
    public int getId_categories_parcelle() {
        return id_categories_parcelle;
    }
    public void setId_categories_parcelle(int id_categories_parcelle) {
        this.id_categories_parcelle = id_categories_parcelle;
    }
    public int getId_parcelle() {
        return id_parcelle;
    }
    public void setId_parcelle(int id_parcelle) {
        this.id_parcelle = id_parcelle;
    }
    public int getId_categorie_culture() {
        return id_categorie_culture;
    }
    public void setId_categorie_culture(int id_categorie_culture) {
        this.id_categorie_culture = id_categorie_culture;
    }



}
    
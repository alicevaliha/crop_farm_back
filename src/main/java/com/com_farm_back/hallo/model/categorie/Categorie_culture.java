package com.com_farm_back.hallo.model.categorie;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Categorie_culture {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id_categorie_culture;
    private String nomcategorie;
    private int corbeille;

    public Categorie_culture(){}

    public Categorie_culture(int id,String nomcategorie){}

    public int getId_categorie_culture() {
        return id_categorie_culture;
    }
    public void setId_categorie_culture(int id_categorie_culture) {
        this.id_categorie_culture = id_categorie_culture;
    }
    public String getNomcategorie() {
        return nomcategorie;
    }
    public void setNomcategorie(String nomcategorie) {
        this.nomcategorie = nomcategorie;
    }

    public int getCorbeille() {
        return corbeille;
    }

    public void setCorbeille(int corbeille) {
        this.corbeille = corbeille;
    }


    


}

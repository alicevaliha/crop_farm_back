package com.com_farm_back.hallo.model.parcelle;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Parcelle 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_parcelle;
    private int id_terrain;
    private int longueur;
    private int largeur;
    private double rendement;

    public Parcelle(){}

    public Parcelle(int id,int idterrain , double rend,
    double sur,int longeur,int largeur)
    {
        setId_parcelle(id);
        setId_terrain(idterrain);
        setLargeur(largeur);
        setLongueur(longeur);
        setRendement(rend);
    }

    public int getId_parcelle() {
        return id_parcelle;
    }
    public void setId_parcelle(int id_parcelle) {
        this.id_parcelle = id_parcelle;
    }
    public int getId_terrain() {
        return id_terrain;
    }
    public void setId_terrain(int id_terrain) {
        this.id_terrain = id_terrain;
    }

    public double getRendement() {
        return rendement;
    }
    public void setRendement(double rendement) {
        this.rendement = rendement;
    }

    public int getLongueur() {
        return longueur;
    }

    public void setLongueur(int longueur) {
        this.longueur = longueur;
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

}

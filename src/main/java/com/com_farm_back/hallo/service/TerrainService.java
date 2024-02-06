package com.com_farm_back.hallo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.com_farm_back.hallo.dao.TerrainDAO;
import com.com_farm_back.hallo.dao.Terrain_dao;
import com.com_farm_back.hallo.model.terrain.Terrain;
import com.com_farm_back.hallo.repository.TerrainRepository;
import java.util.*;

@Service
public class TerrainService {

    private final TerrainDAO dao;
    @Autowired
    private TerrainRepository terrainRepository;

     @Autowired 
    public TerrainService(TerrainDAO dao)
    {
        this.dao = dao;
    }   

    public List<Terrain> getAllTerrains() {
        return terrainRepository.findAll();
    }

    public Terrain getTerrainById(int id) {
        return terrainRepository.findById(id).orElse(null);
    }

    public List<Terrain> getTerrainsByProprietaire(int idProprietaire) {
        return dao.getTerrainsByIdProprietaireValid(idProprietaire);
    }

    public List<Terrain> getTerrainsByProprietaireUnvalid(int idProprietaire) {
        return dao.getTerrainsByIdProprietaireUnvalid(idProprietaire);
    }

    public List<Terrain> getTerrainsByProprietaireValid(int idProprietaire) {
        return dao.getTerrainsByIdProprietaireValid(idProprietaire);
    }

    public List<Terrain> getNonDeletedTerrains() {
        return terrainRepository.findByCorbeille(0);
    }

    public Terrain saveTerrain(Terrain terrain) {
        return terrainRepository.save(terrain);
    }

    public Terrain createTerrain(Terrain terrain) {
        return terrainRepository.save(terrain);
    }

    public Terrain updateTerrain(int id, Terrain terrainDetails) throws Exception{
        // Rechercher le terrain existant dans la base de données par son ID
        Optional<Terrain> terrainOptional = terrainRepository.findById(id);
    
        if (terrainOptional.isPresent()) {
            Terrain terrainExistante = terrainOptional.get();
    
            // Mettre à jour uniquement les attributs spécifiés dans terrainDetails
            if (terrainDetails.getDesc_terrain() != null) {
                terrainExistante.setDesc_terrain(terrainDetails.getDesc_terrain());
            }
            if (terrainDetails.getCoord_location() != null) {
                // terrainExistante.setCoordLocation(terrainDetails.getCoordLocation());
            }
            if(terrainDetails.getId_proprietaire() != 0){
                terrainExistante.setId_proprietaire(terrainDetails.getId_proprietaire());
            }
            if(terrainDetails.getLongueur() != 0 ){
                terrainExistante.setLongueur(terrainDetails.getLongueur());
            }
            if(terrainDetails.getLargeur() != 0){
                terrainExistante.setLargeur(terrainDetails.getLargeur());
            }
            if(terrainDetails.getCorbeille() != 0 ){
                terrainExistante.setCorbeille(terrainDetails.getCorbeille());
            }
            // Mettre à jour d'autres attributs si nécessaire
    
            // Enregistrer les modifications dans la base de données
            return terrainRepository.save(terrainExistante);
        } else {
            // Gérer le cas où aucun terrain avec cet ID n'est trouvé
            throw new Exception("Terrain non trouvé avec l'ID : " + id);
        }
    }
    

    public void deleteTerrain(int id) {
        terrainRepository.deleteById(id);
    }

    public List<Map<String, Object>> getstatTerrain(int idProprietaire) {
        return dao.getstatTerrain(idProprietaire);
    }

    public List<Map<String, Object>> getstatTerrainNb(int idProprietaire) {
        return dao.getstatTerrainNb(idProprietaire);
    } 

    public void insertPhotos(String photo,int idterrain){
        dao.insertPhotos(photo, idterrain);
    } 
}

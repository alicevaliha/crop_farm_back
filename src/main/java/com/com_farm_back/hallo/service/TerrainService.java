package com.com_farm_back.hallo.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.com_farm_back.hallo.model.terrain.Terrain;
import com.com_farm_back.hallo.repository.TerrainRepository;

@Service
public class TerrainService {

    @Autowired
    private TerrainRepository terrainRepository;

    public List<Terrain> getAllTerrains() {
        return terrainRepository.findAll();
    }

    public Terrain getTerrainById(int id) {
        return terrainRepository.findById(id).orElse(null);
    }

    public List<Terrain> getNonDeletedTerrains() {
        return terrainRepository.findByCorbeille(0);
    }

    public Terrain saveTerrain(Terrain terrain) {
        return terrainRepository.save(terrain);
    }

    public Terrain updateTerrain(Terrain terrain) {
        if (terrainRepository.existsById(terrain.getId_terrain())) {
            return terrainRepository.save(terrain);
        }
        return null; // Handle this case according to your application logic
    }

    public void deleteTerrain(int id) {
        terrainRepository.deleteById(id);
    }

}

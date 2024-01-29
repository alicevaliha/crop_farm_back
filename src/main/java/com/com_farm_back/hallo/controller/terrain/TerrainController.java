package com.com_farm_back.hallo.controller.terrain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.com_farm_back.hallo.model.terrain.Terrain;
import com.com_farm_back.hallo.service.TerrainService;

@RestController
@RequestMapping("/terrains")
public class TerrainController {

    @Autowired
    private TerrainService terrainService;

    @CrossOrigin(origins = "*")
    @GetMapping
    public ResponseEntity<List<Terrain>> getAllTerrains() {
        List<Terrain> terrains = terrainService.getAllTerrains();
        return new ResponseEntity<>(terrains, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/proprietaire/{id}")
    public ResponseEntity<List<Terrain>> getTerrainsByProprietaire(@PathVariable("id") int id) {
        List<Terrain> terrains = terrainService.getTerrainsByProprietaire(id);
        return terrains.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(terrains);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/proprietaire/invalid/{id}")
    public ResponseEntity<List<Terrain>> getTerrainsByProprietaireUnvalid(@PathVariable("id") int id) {
        List<Terrain> terrains = terrainService.getTerrainsByProprietaireUnvalid(id);
        return terrains.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(terrains);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/proprietaire/valid/{id}")
    public ResponseEntity<List<Terrain>> getTerrainsByProprietaireValid(@PathVariable("id") int id) {
        List<Terrain> terrains = terrainService.getTerrainsByProprietaireValid(id);
        return terrains.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(terrains);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public ResponseEntity<Terrain> getTerrainById(@PathVariable("id") int id) {
        Terrain terrain = terrainService.getTerrainById(id);
        return terrain != null ? ResponseEntity.ok(terrain) : ResponseEntity.notFound().build();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/stats/{id}")
    public ResponseEntity< List<Map<String, Object>>> getStats(@PathVariable("id") int id) {
        List<Map<String, Object>> simus = terrainService.getstatTerrain(id);
        return new ResponseEntity<>(simus, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/nombreterrains/{id}")
    public ResponseEntity< List<Map<String, Object>>> getStatsNb(@PathVariable("id") int id) {
        List<Map<String, Object>> simus = terrainService.getstatTerrainNb(id);
        return new ResponseEntity<>(simus, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/active")
    public ResponseEntity<List<Terrain>> getNonDeletedTerrains() {
        List<Terrain> terrains = terrainService.getNonDeletedTerrains();
        return terrains.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(terrains);
    }

    @CrossOrigin(origins = "*")
    @PostMapping
    public ResponseEntity<Terrain> saveOrCreateTerrain(@RequestBody Terrain terrain) {
        if (terrain.getId_terrain() != 0) {
            Terrain savedTerrain = terrainService.saveTerrain(terrain);
            return new ResponseEntity<>(savedTerrain, HttpStatus.OK);
        } else {
            Terrain createdTerrain = terrainService.createTerrain(terrain);
            return new ResponseEntity<>(createdTerrain, HttpStatus.CREATED);
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/{id}")
    public ResponseEntity<Terrain> updateTerrain(@PathVariable(value = "id") int id, @RequestBody Terrain terrainDetails) throws Exception {
        Terrain updatedTerrain = terrainService.updateTerrain(id, terrainDetails);
        return ResponseEntity.ok(updatedTerrain);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTerrain(@PathVariable("id") int id) {
        terrainService.deleteTerrain(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

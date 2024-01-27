package com.com_farm_back.hallo.controller.terrain;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public ResponseEntity<List<Terrain>> getAllTerrains() {
        List<Terrain> terrains = terrainService.getAllTerrains();
        return new ResponseEntity<>(terrains, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Terrain> getTerrainById(@PathVariable("id") int id) {
        Terrain terrain = terrainService.getTerrainById(id);
        if (terrain != null) {
            return new ResponseEntity<>(terrain, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/active")
    public ResponseEntity<List<Terrain>> getNonDeletedTerrains() {
        List<Terrain> terrains = terrainService.getNonDeletedTerrains();
        return new ResponseEntity<>(terrains, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Terrain> saveTerrain(@RequestBody Terrain terrain) {
        Terrain savedTerrain = terrainService.saveTerrain(terrain);
        return new ResponseEntity<>(savedTerrain, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Terrain> updateTerrain(@PathVariable("id") int id, @RequestBody Terrain terrain) {
        terrain.setId_terrain(id);
        Terrain updatedTerrain = terrainService.updateTerrain(terrain);
        if (updatedTerrain != null) {
            return new ResponseEntity<>(updatedTerrain, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTerrain(@PathVariable("id") int id) {
        terrainService.deleteTerrain(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

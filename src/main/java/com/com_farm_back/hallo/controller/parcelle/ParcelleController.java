package com.com_farm_back.hallo.controller.parcelle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.com_farm_back.hallo.model.parcelle.Parcelle;
import com.com_farm_back.hallo.service.ParcelleService;
import com.google.gson.JsonArray;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/parcelles")
public class ParcelleController {

    private final ParcelleService parcelleService;

    @Autowired
    public ParcelleController(ParcelleService parcelleService) {
        this.parcelleService = parcelleService;
    }

    @GetMapping
    public ResponseEntity<List<Parcelle>> getAllParcelles() {
        List<Parcelle> parcelles = parcelleService.getAllParcelles();
        return new ResponseEntity<>(parcelles, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Map<String, Object>> > getDataParcelle() {
        return new ResponseEntity<>(parcelleService.getDataParcelle(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parcelle> getParcelleById(@PathVariable("id") int id) {
        Optional<Parcelle> parcelle = parcelleService.getParcelleById(id);
        return parcelle.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Parcelle> createParcelle(@RequestBody Parcelle parcelle) {
        Parcelle createdParcelle = parcelleService.saveParcelle(parcelle);
        return new ResponseEntity<>(createdParcelle, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Parcelle> updateParcelle(@PathVariable("id") int id, @RequestBody Parcelle parcelleDetails) throws Exception{
        Parcelle updatedParcelle = parcelleService.updateParcelle(id, parcelleDetails);
        return new ResponseEntity<>(updatedParcelle, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParcelle(@PathVariable("id") int id) {
        parcelleService.deleteParcelle(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

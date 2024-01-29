package com.com_farm_back.hallo.controller.categorie;

import com.com_farm_back.hallo.model.categorie.Categorie_culture;
import com.com_farm_back.hallo.service.Categorie_cultureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories-culture")
public class Categorie_cultureController {

    private final Categorie_cultureService categorie_cultureService;

    @Autowired
    public Categorie_cultureController(Categorie_cultureService categorie_cultureService) {
        this.categorie_cultureService = categorie_cultureService;
    }

    
    @GetMapping
    public ResponseEntity<List<Categorie_culture>> getAllCategoriesCulture() {
        List<Categorie_culture> categoriesCultureList = categorie_cultureService.getAllCategoriesCulture();
        return new ResponseEntity<>(categoriesCultureList, HttpStatus.OK);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Categorie_culture> getCategoryCultureById(@PathVariable int id) {
        Optional<Categorie_culture> categoryCultureOptional = categorie_cultureService.getCategoryCultureById(id);
        return categoryCultureOptional.map(categoryCulture -> new ResponseEntity<>(categoryCulture, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    
    @PostMapping
    public ResponseEntity<Categorie_culture> createCategoryCulture(@RequestBody Categorie_culture categorie) {
        Categorie_culture createdCategoryCulture = categorie_cultureService.createCategoryCulture(categorie);
        return new ResponseEntity<>(createdCategoryCulture, HttpStatus.CREATED);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Categorie_culture> updateCategoryCulture(@PathVariable int id, @RequestBody Categorie_culture categorieDetails) {
        Categorie_culture updatedCategoryCulture = categorie_cultureService.updateCategoryCulture(id, categorieDetails);
        return updatedCategoryCulture != null ?
                new ResponseEntity<>(updatedCategoryCulture, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryCulture(@PathVariable int id) {
        categorie_cultureService.deleteCategoryCulture(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

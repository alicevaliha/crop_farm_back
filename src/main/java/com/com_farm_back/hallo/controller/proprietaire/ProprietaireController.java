package com.com_farm_back.hallo.controller.proprietaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.com_farm_back.hallo.model.proprietaire.Proprietaire;
import com.com_farm_back.hallo.service.ProprietaireService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/proprietaires")
public class ProprietaireController {

    private final ProprietaireService proprietaireService;

    @Autowired
    public ProprietaireController(ProprietaireService proprietaireService) {
        this.proprietaireService = proprietaireService;
    }

    
    @GetMapping
    public ResponseEntity<List<Proprietaire>> getAllProprietaires() {
        List<Proprietaire> proprietaires = proprietaireService.getAllProprietaires();
        return new ResponseEntity<>(proprietaires, HttpStatus.OK);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Proprietaire> getProprietaireById(@PathVariable("id") int id) {
        Optional<Proprietaire> proprietaire = proprietaireService.getProprietaireById(id);
        return proprietaire.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/amis/{id}")
    public ResponseEntity<List<Proprietaire>> getAmis(@PathVariable("id") int id) {
        List<Proprietaire> proprietaires = proprietaireService.getAmis(id);
        return new ResponseEntity<>(proprietaires, HttpStatus.OK);
    }

    
    @GetMapping("/login")
    public ResponseEntity<Proprietaire> Login(@RequestParam("mail") String mail,@RequestParam("mdp") String mdp) throws Exception{
        Proprietaire proprietaire = proprietaireService.Login(mail, mdp);
        return new ResponseEntity<>(proprietaire, HttpStatus.OK);
    }


    
    @PostMapping
    public ResponseEntity<Proprietaire> createProprietaire(@RequestBody Proprietaire proprietaire) {
        Proprietaire createdProprietaire = proprietaireService.saveProprietaire(proprietaire);
        return new ResponseEntity<>(createdProprietaire, HttpStatus.CREATED);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Proprietaire> updateProprietaire(@PathVariable("id") int id, @RequestBody Proprietaire proprietaireDetails)throws Exception {
        Proprietaire updatedProprietaire = proprietaireService.updateProprietaire(id, proprietaireDetails);
        return new ResponseEntity<>(updatedProprietaire, HttpStatus.OK);
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProprietaire(@PathVariable("id") int id) {
        proprietaireService.deleteProprietaire(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


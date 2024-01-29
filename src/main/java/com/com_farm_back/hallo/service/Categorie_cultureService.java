package com.com_farm_back.hallo.service;

import com.com_farm_back.hallo.model.categorie.Categorie_culture;
import com.com_farm_back.hallo.repository.Categorie_cultureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Categorie_cultureService {

    private final Categorie_cultureRepository categorie_cultureRepository;

    @Autowired
    public Categorie_cultureService(Categorie_cultureRepository categorie_cultureRepository) {
        this.categorie_cultureRepository = categorie_cultureRepository;
    }

    public List<Categorie_culture> getAllCategoriesCulture() {
        return categorie_cultureRepository.findAll();
    }

    public Optional<Categorie_culture> getCategoryCultureById(int id) {
        return categorie_cultureRepository.findById(id);
    }

    public Categorie_culture createCategoryCulture(Categorie_culture categorie) {
        return categorie_cultureRepository.save(categorie);
    }

    public Categorie_culture updateCategoryCulture(int id, Categorie_culture categorieDetails) {
        Optional<Categorie_culture> optionalCategoryCulture = categorie_cultureRepository.findById(id);
        if (optionalCategoryCulture.isPresent()) {
            Categorie_culture existingCategoryCulture = optionalCategoryCulture.get();
            existingCategoryCulture.setNomcategorie(categorieDetails.getNomcategorie());
            existingCategoryCulture.setCorbeille(categorieDetails.getCorbeille());
            return categorie_cultureRepository.save(existingCategoryCulture);
        } else {
            return null;
        }
    }

    public void deleteCategoryCulture(int id) {
        categorie_cultureRepository.deleteById(id);
    }
}

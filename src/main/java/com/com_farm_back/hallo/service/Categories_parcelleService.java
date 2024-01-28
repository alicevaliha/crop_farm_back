package com.com_farm_back.hallo.service;

import com.com_farm_back.hallo.model.categorie.Categories_parcelle;
import com.com_farm_back.hallo.repository.Categories_parcelleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class Categories_parcelleService {

    private final Categories_parcelleRepository categories_parcelleRepository;

    @Autowired
    public Categories_parcelleService(Categories_parcelleRepository categories_parcelleRepository) {
        this.categories_parcelleRepository = categories_parcelleRepository;
    }

    public List<Categories_parcelle> getAllCategories_parcelle() {
        return categories_parcelleRepository.findAll();
    }

    public Optional<Categories_parcelle> getCategories_parcelleById(int id) {
        return categories_parcelleRepository.findById(id);
    }

    public Categories_parcelle createCategories_parcelle(Categories_parcelle categories_parcelle) {
        return categories_parcelleRepository.save(categories_parcelle);
    }

    public Categories_parcelle updateCategories_parcelle(int id, Categories_parcelle categories_parcelleDetails) {
        Optional<Categories_parcelle> optionalCategories_parcelle = categories_parcelleRepository.findById(id);
        if (optionalCategories_parcelle.isPresent()) {
            Categories_parcelle existingCategories_parcelle = optionalCategories_parcelle.get();
            
            existingCategories_parcelle.setId_parcelle(categories_parcelleDetails.getId_parcelle());
            
            if(existingCategories_parcelle.getId_categorie_culture()!=0)
            existingCategories_parcelle.setId_categorie_culture(categories_parcelleDetails.getId_categorie_culture());
            return categories_parcelleRepository.save(existingCategories_parcelle);
        } else {
            return null;
        }
    }

    public void deleteCategories_parcelle(int id) {
        categories_parcelleRepository.deleteById(id);
    }
}

package com.com_farm_back.hallo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.com_farm_back.hallo.dao.ParcelleDAO;
import com.com_farm_back.hallo.dao.TerrainDAO;
import com.com_farm_back.hallo.model.parcelle.Parcelle;
import com.com_farm_back.hallo.repository.ParcelleRepository;
import com.google.gson.JsonArray;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ParcelleService {

    private final ParcelleDAO dao;
    private final ParcelleRepository parcelleRepository;

    @Autowired
    public ParcelleService(ParcelleRepository parcelleRepository,ParcelleDAO dao) {
        this.parcelleRepository = parcelleRepository;
        this.dao = dao;
    }

    public List<Parcelle> getAllParcelles() {
        return parcelleRepository.findAll();
    }

    public Optional<Parcelle> getParcelleById(int id) {
        return parcelleRepository.findById(id);
    }

    public Parcelle saveParcelle(Parcelle parcelle) {
        return parcelleRepository.save(parcelle);
    }

    public Parcelle updateParcelle(int id, Parcelle parcelleDetails) throws Exception {
        Optional<Parcelle> existingParcelle = parcelleRepository.findById(id);
        if (existingParcelle.isPresent()) {
            Parcelle updatedParcelle = existingParcelle.get();
    
            if (parcelleDetails.getLongueur() != 0) {
                updatedParcelle.setLongueur(parcelleDetails.getLongueur());
            }
    
            if (parcelleDetails.getLargeur() != 0) {
                updatedParcelle.setLargeur(parcelleDetails.getLargeur());
            }
    
            if (parcelleDetails.getRendement() != 0) {
                updatedParcelle.setRendement(parcelleDetails.getRendement());
            }
    
            if (parcelleDetails.getCorbeille() != 0) {
                updatedParcelle.setCorbeille(parcelleDetails.getCorbeille());
            }
    
            return parcelleRepository.save(updatedParcelle);
        } else {
            throw new Exception("Parcelle non trouvée avec l'ID : " + id);
        }
    }
    

    public void deleteParcelle(int id) {
        parcelleRepository.deleteById(id);
    }

    public List<Map<String, Object>>  getDataParcelle() {
        return dao.getDataParcelle();
    } 

    public List<Map<String, Object>>  getParcelleByTerrain(int idterrain) {
        return dao.getParcelleByTerrain(idterrain);
    }

    public List<Map<String, Object>>  getParcelleByProprietaire(int idproprio) {
        return dao.getParcelleByProprietaire(idproprio);
    }

}

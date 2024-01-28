
package com.com_farm_back.hallo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import com.com_farm_back.hallo.model.plante.Plante;
import com.com_farm_back.hallo.repository.PlanteRepository;


@Service
public class PlanteService {

    @Autowired
    private PlanteRepository planteRepository;

    public List<Plante> getAllPlantes() {
        return planteRepository.findAll();
    }

    public Optional<Plante> getPlanteById(Integer id) {
        return planteRepository.findById(id);
    }

    public List<Plante> getAllPlantesWithCorbeilleEqualsToZero() {
        return planteRepository.findByCorbeille(0);
    }

    public Plante savePlante(Plante plante) {
        return planteRepository.save(plante);
    }

    public void deletePlante(Integer id) {
        planteRepository.deleteById(id);
    }

    public Plante updatePlante(int id, Plante updatedPlante) {
        Optional<Plante> optionalPlante = planteRepository.findById(id);
    
        if (optionalPlante.isPresent()) {
            Plante existingPlante = optionalPlante.get();
    
            // Mise à jour des champs de la plante existante avec les valeurs fournies
            if (updatedPlante.getId_categorie_culture() != 0) {
                existingPlante.setId_categorie_culture(updatedPlante.getId_categorie_culture());
            }
    
            if (updatedPlante.getNom_plante() != null) {
                existingPlante.setNom_plante(updatedPlante.getNom_plante());
            }
    
            if (updatedPlante.getPrixachat() != 0) {
                existingPlante.setPrixachat(updatedPlante.getPrixachat());
            }
    
            if (updatedPlante.getPrixvente() != 0) {
                existingPlante.setPrixvente(updatedPlante.getPrixvente());
            }
    
            if (updatedPlante.getSprite_plante() != null) {
                existingPlante.setSprite_plante(updatedPlante.getSprite_plante());
            }
    
            if (updatedPlante.getPlaceingamemaker() != 0) {
                existingPlante.setPlaceingamemaker(updatedPlante.getPlaceingamemaker());
            }
    
            if (updatedPlante.getCorbeille() != 0) {
                existingPlante.setCorbeille(updatedPlante.getCorbeille());
            }
    
            // Mise à jour du rendement
            if (updatedPlante.getRendement() != 0) {
                existingPlante.setRendement(updatedPlante.getRendement());
            }
    
            // Enregistrer et retourner la plante mise à jour
            return planteRepository.save(existingPlante);
        } else {
            // Gérer le cas où la plante n'est pas trouvée
            throw new IllegalArgumentException("Plante not found with id: " + id);
        }
    }
    
    
}


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

    public Plante updatePlante(Plante plante) {
        // Vérifie si la plante existe dans la base de données
        Optional<Plante> existingPlante = planteRepository.findById(plante.getId_plante());
        if (existingPlante.isPresent()) {
            // Met à jour les détails de la plante existante
            return planteRepository.save(plante);
        } else {
            // Si la plante n'existe pas, vous pouvez choisir de lever une exception ou de retourner null
            return null;
        }
    }
}

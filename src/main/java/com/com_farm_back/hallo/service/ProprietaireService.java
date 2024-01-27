package com.com_farm_back.hallo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import com.com_farm_back.hallo.model.proprietaire.Proprietaire;
import com.com_farm_back.hallo.repository.*;

@Service
public class ProprietaireService {

    private final ProprietaireRepository proprietaireRepository;

    @Autowired
    public ProprietaireService(ProprietaireRepository proprietaireRepository) {
        this.proprietaireRepository = proprietaireRepository;
    }

    public List<Proprietaire> getAllProprietaires() {
        return proprietaireRepository.findAll();
    }

    public Optional<Proprietaire> getProprietaireById(int id) {
        return proprietaireRepository.findById(id);
    }

    public Proprietaire saveProprietaire(Proprietaire proprietaire) {
        return proprietaireRepository.save(proprietaire);
    }

    public Proprietaire updateProprietaire(int id, Proprietaire proprietaireDetails) throws Exception{
        Optional<Proprietaire> existingProprietaire = proprietaireRepository.findById(id);
        if (existingProprietaire.isPresent()) {
            Proprietaire updatedProprietaire = existingProprietaire.get();
            if(updatedProprietaire.getNom() != null)
            updatedProprietaire.setNom(proprietaireDetails.getNom());

            if(updatedProprietaire.getMail()!=null)
            updatedProprietaire.setMail(proprietaireDetails.getMail());

            if(updatedProprietaire.getMdp()!=null)
            updatedProprietaire.setMdp(proprietaireDetails.getMdp());

            if(String.valueOf(updatedProprietaire.getDtn())!=null)
            updatedProprietaire.setDtn(proprietaireDetails.getDtn());

            if(updatedProprietaire.getCorbeille()!=0)
            updatedProprietaire.setCorbeille(proprietaireDetails.getCorbeille());

            return proprietaireRepository.save(updatedProprietaire);

        } else {
            throw new Exception("Proprietaire non trouvé avec l'ID : " + id);
        }
    }

    public void deleteProprietaire(int id) {
        proprietaireRepository.deleteById(id);
    }
}

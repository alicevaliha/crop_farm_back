package com.com_farm_back.hallo.service;

import com.com_farm_back.hallo.dao.PlanterDAO;
import com.com_farm_back.hallo.model.plante.Planter;
import com.com_farm_back.hallo.repository.PlanterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PlanterService {

    private final PlanterDAO dao;
    @Autowired
    private PlanterRepository planterRepository;

    @Autowired
    public PlanterService(PlanterDAO dao){
        this.dao=dao;
    } 

    public List<Planter> getAllPlanters() {
        return planterRepository.findAll();
    }

    public Planter getPlanterById(int id) {
        return planterRepository.findById(id).orElse(null);
    }

    public Planter savePlanter(Planter planter) {
        return planterRepository.save(planter);
    }

    public void deletePlanter(int id) {
        planterRepository.deleteById(id);
    }
    
    public List<Map<String, Object>> getSimulation() {
        return dao.getSimulation();
    }

    public List<Map<String, Object>>  getSimulationByproprietaire(int idproprietaire) {
        return dao.getSimulationByproprietaire(idproprietaire);
    }

    public void recolter(int idplantation,int rendement,int idparcelle){
        dao.recolter(idplantation, rendement, idparcelle);
    }
    public List<Map<String, Object>> getRecoltes(int idProprietaire) {
        return dao.getRecoltes(idProprietaire);
    }

}

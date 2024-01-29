package com.com_farm_back.hallo.controller.plante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.com_farm_back.hallo.model.plante.Plante;
import com.com_farm_back.hallo.repository.PlanteRepository;
import com.com_farm_back.hallo.service.PlanteService;

@RestController
@RequestMapping("/plantes")
public class PlanteController {

    @Autowired
    private PlanteService planteService;

    @GetMapping
    public ResponseEntity<List<Plante>> getAllPlantes() {
        List<Plante> plantes = planteService.getAllPlantes();
        return new ResponseEntity<>(plantes, HttpStatus.OK);
    }


    @GetMapping("/active")
    public ResponseEntity<List<Plante>> getNonDeletedPlantes() {
        List<Plante> plantes = planteService.getAllPlantesWithCorbeilleEqualsToZero();
        return new ResponseEntity<>(plantes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plante> getPlanteById(@PathVariable("id") Integer id) {
        Optional<Plante> plante = planteService.getPlanteById(id);
        return plante.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/graphdara/{id}")
    public ResponseEntity< List<Map<String, Object>>> graphPlante(@PathVariable("id") int id) {
        List<Map<String, Object>> simus = planteService.graphPlante(id);
        return new ResponseEntity<>(simus, HttpStatus.OK);
    }

    @GetMapping("/statrecoltes/{id}")
    public ResponseEntity< List<Map<String, Object>>> getStatRecoltes(@PathVariable("id") int id) {
        List<Map<String, Object>> simus = planteService.statRecolte(id);
        return new ResponseEntity<>(simus, HttpStatus.OK);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<List<Plante>> getPlanteByCategorie(@PathVariable("id") Integer id) {
        List<Plante> plantes = planteService.getPlantesbycategorie(id);
        return new ResponseEntity<>(plantes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Plante> createPlante(@RequestBody Plante plante) {
        Plante savedPlante = planteService.savePlante(plante);
        return new ResponseEntity<>(savedPlante, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Plante> updatePlante(@PathVariable("id") Integer id, @RequestBody Plante plante) {
        Plante updatedPlante = planteService.updatePlante(id,plante);
        return updatedPlante != null ? new ResponseEntity<>(updatedPlante, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlante(@PathVariable("id") Integer id) {
        planteService.deletePlante(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

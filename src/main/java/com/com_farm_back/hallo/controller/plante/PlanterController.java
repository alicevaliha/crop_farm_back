package com.com_farm_back.hallo.controller.plante;
import com.com_farm_back.hallo.model.plante.Planter;
import com.com_farm_back.hallo.service.PlanterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/planters")
public class PlanterController {

    @Autowired
    private PlanterService planterService;

    @GetMapping
    public ResponseEntity<List<Planter>> getAllPlanters() {
        List<Planter> planters = planterService.getAllPlanters();
        return new ResponseEntity<>(planters, HttpStatus.OK);
    }


    @GetMapping("/simulations")
    public ResponseEntity< List<Map<String, Object>>> getAllSimulations() {
        List<Map<String, Object>> simus = planterService.getSimulation();
        return new ResponseEntity<>(simus, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Planter> getPlanterById(@PathVariable("id") int id) {
        Planter planter = planterService.getPlanterById(id);
        return new ResponseEntity<>(planter, planter != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Planter> savePlanter(@RequestBody Planter planter) {
        Planter savedPlanter = planterService.savePlanter(planter);
        return new ResponseEntity<>(savedPlanter, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlanter(@PathVariable("id") int id) {
        planterService.deletePlanter(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

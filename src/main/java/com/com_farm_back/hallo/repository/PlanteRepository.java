package com.com_farm_back.hallo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.com_farm_back.hallo.model.plante.Plante;
import java.util.List;


@Repository
public interface PlanteRepository extends JpaRepository<Plante, Integer> {

    List<Plante> findByCorbeille(int corbeille);
    
}


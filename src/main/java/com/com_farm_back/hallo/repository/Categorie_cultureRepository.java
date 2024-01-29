package com.com_farm_back.hallo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.com_farm_back.hallo.model.categorie.Categorie_culture;
import java.util.List;


@Repository
public interface Categorie_cultureRepository extends JpaRepository<Categorie_culture, Integer> {

    // List<Categorie_culture> findByCorbeille(int corbeille);
    
}

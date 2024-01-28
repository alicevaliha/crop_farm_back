package com.com_farm_back.hallo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.com_farm_back.hallo.model.categorie.Categories_parcelle;

import java.util.List;


@Repository
public interface Categories_parcelleRepository extends JpaRepository<Categories_parcelle, Integer> {

    
}

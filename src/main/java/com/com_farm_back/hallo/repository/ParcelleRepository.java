package com.com_farm_back.hallo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.com_farm_back.hallo.model.parcelle.Parcelle;
import java.util.List;


@Repository
public interface ParcelleRepository extends JpaRepository<Parcelle, Integer> {

    List<Parcelle> findByCorbeille(int corbeille);
    
}


package com.com_farm_back.hallo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.com_farm_back.hallo.model.proprietaire.Proprietaire;
import java.util.List;


@Repository
public interface ProprietaireRepository extends JpaRepository<Proprietaire, Integer> {

    List<Proprietaire> findByCorbeille(int corbeille);
    
}

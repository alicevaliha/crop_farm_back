package com.com_farm_back.hallo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.com_farm_back.hallo.model.terrain.Terrain;
import java.util.List;


@Repository
public interface TerrainRepository extends JpaRepository<Terrain, Integer> {

    List<Terrain> findByCorbeille(int corbeille);
    
}

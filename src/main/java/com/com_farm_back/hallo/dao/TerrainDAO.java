
package com.com_farm_back.hallo.dao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.com_farm_back.hallo.dao.Parcelle_dao;
import com.com_farm_back.hallo.model.categorie.Categorie_culture;
import com.com_farm_back.hallo.model.parcelle.Parcelle;
import com.com_farm_back.hallo.model.proprietaire.Proprietaire;
import com.com_farm_back.hallo.model.terrain.Terrain;

@Repository
public class TerrainDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Terrain> getTerrainsByIdProprietaire(int idProprietaire) {
        String sql = "select * from terrain where id_proprietaire = ? and corbeille = 0";
        List<Terrain> result = jdbcTemplate.query(sql, new Object[]{idProprietaire}, new BeanPropertyRowMapper<>(Terrain.class));
        return result;
    }
    

}
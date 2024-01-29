package com.com_farm_back.hallo.dao;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.com_farm_back.hallo.model.plante.Plante;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.List;
import java.util.Map;

@Repository
public class PlanteDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Plante> getPlantesbycategorie(int categ) {
        String sql = "SELECT * FROM plante where id_categorie_culture ="+categ;
        System.out.println("Executing SQL query: " + sql);

        // Utiliser BeanPropertyRowMapper pour mapper les résultats à des objets Plante
        List<Plante> plantes = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Plante.class));

        return plantes;
    }


    public List<Map<String, Object>> graphPlante(int idProprietaire) {

        String sql = "SELECT nom_plante, COUNT(*) AS nombre_utilisations,ROUND(COUNT(*) * 100.0 / (SELECT COUNT(*) FROM vue_planter_plante_parcelle_terrain WHERE id_proprietaire = ";
        sql += idProprietaire +"), 2) AS pourcentage_utilisation FROM vue_planter_plante_parcelle_terrain WHERE id_proprietaire =";
        sql+=idProprietaire+" GROUP BY nom_plante";
        System.out.println(sql);
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        
        return rows;
    
    }

    
}

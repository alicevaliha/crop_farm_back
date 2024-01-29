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

        String sql = "SELECT nom_plante,ROUND(COUNT(*) * 100.0 / (SELECT COUNT(*) FROM vue_planter_plante_parcelle_terrain WHERE id_proprietaire = ";
        sql += idProprietaire +"), 2) AS pourcentage_utilisation FROM vue_planter_plante_parcelle_terrain WHERE id_proprietaire =";
        sql+=idProprietaire+" GROUP BY nom_plante";
        System.out.println(sql);
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        
        return rows;
    
    }

    public List<Map<String, Object>> statRecolte(int idProprietaire) {

        String sql = "WITH all_month AS (SELECT generate_series(DATE_TRUNC('year', CURRENT_DATE), DATE_TRUNC('year', CURRENT_DATE) + INTERVAL '1 year' - INTERVAL '1 day', INTERVAL '1 month' ) AS month ) SELECT TO_CHAR(am.month, 'Month') AS mois, COALESCE(SUM(v.rendement), 0) AS total_recolte FROM  all_month am LEFT JOIN ";
        sql+=" v_combine_recolte_proprietaire v ON DATE_TRUNC('month', v.dateaction) = am.month AND v.idproprietaire =";
        sql+=idProprietaire;
        sql+=" GROUP BY  am.month ORDER BY am.month";

        
        System.out.println(sql);
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        
        return rows;
    }

    
}

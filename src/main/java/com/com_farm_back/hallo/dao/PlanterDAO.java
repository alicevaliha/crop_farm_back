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

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.List;
import java.util.Map;

@Repository
public class PlanterDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>>  getSimulation() {
        String sql = "SELECT * FROM simulations";
        System.out.println("Executing SQL query: " + sql);

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        return rows;
    }

    public List<Map<String, Object>>  getSimulationByproprietaire(int idproprietaire) {
        String sql = "SELECT * FROM simulations where id_proprietaire="+idproprietaire;
        System.out.println("Executing SQL query: " + sql);

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        return rows;
    }

    public void recolter(int idplantation,int rendement,int idparcelle){

        String sql = "insert into recolte(id_parcelle,id_plantation,dateaction,rendement) values(";
        sql+= idparcelle+","+idplantation+",NOW(),"+rendement+")";
        jdbcTemplate.update(sql);

        String sql2 = "UPDATE PLANTER SET etat=1 where id_plantation="+idplantation;
        // System.out.println("Executing SQL query: " + sql);

        jdbcTemplate.update(sql2);

        // return rows;

    }

    public List<Map<String, Object>> getRecoltes(int idProprietaire) {
        String sql = "select * from v_total_recolte where idproprietaire="+ idProprietaire;
        System.out.println(sql);
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        
        return rows;
    }


}

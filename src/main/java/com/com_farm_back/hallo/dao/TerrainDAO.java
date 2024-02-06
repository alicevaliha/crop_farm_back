
package com.com_farm_back.hallo.dao;
import java.util.List;
import java.util.Map;

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

    public List<Terrain> getTerrainsByIdProprietaireUnvalid(int idProprietaire) {
        String sql = "select * from terrain where id_proprietaire ="+ idProprietaire+" and corbeille = 0";
        System.out.println(sql);
        List<Terrain> result = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Terrain.class));
        
        return result;
    }

    public List<Terrain> getTerrainsByIdProprietaireValid(int idProprietaire) {
        String sql = "select * from terrain where id_proprietaire ="+ idProprietaire+" and corbeille = 1";
        System.out.println(sql);
        List<Terrain> result = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Terrain.class));
        
        return result;
    }

    public List<Map<String, Object>> getstatTerrain(int idProprietaire) {
        String sql = "select sum(surface) , idproprietaire from v_all_surface where idproprietaire="+ idProprietaire+" group by idproprietaire";
        System.out.println(sql);
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        
        return rows;
    }

    public List<Map<String, Object>> getstatTerrainNb(int idProprietaire) {
        String sql = "select * from v_count_terrain where id_proprietaire="+ idProprietaire;
        System.out.println(sql);
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        
        return rows;
    }

    public void insertPhotos(String photo,int idterrain){
        String sql = "insert into photos_terrain (id_terrain,photo) values ("+ idterrain+",'"+photo+"')";
        System.out.println(sql);
        jdbcTemplate.update(sql);
    }

    public void deletephoto(int idphoto){
        String sql = "delete from photos_terrain where id_photos_terrain="+idphoto;
        System.out.println(sql);
        jdbcTemplate.update(sql);
    }

    

}
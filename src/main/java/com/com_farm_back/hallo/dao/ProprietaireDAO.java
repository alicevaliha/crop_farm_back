package com.com_farm_back.hallo.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.com_farm_back.hallo.model.plante.Plante;
import com.com_farm_back.hallo.model.proprietaire.Proprietaire;

@Repository
public class ProprietaireDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    

    public Proprietaire Login(String mail, String mdp) throws Exception {
        if (mail == null || mail.isEmpty() || !mail.contains("@")) {
            throw new Exception("Invalid email");
        }
        String sql = "SELECT * FROM proprietaire WHERE mail = ?";
        System.out.println(sql);

        try {
            System.out.println("trying to connect whith"+mail);
            System.out.println("Parameter - mail: " + mail);

            Proprietaire proprietaire = jdbcTemplate.queryForObject(sql, new Object[]{mail}, new BeanPropertyRowMapper<>(Proprietaire.class));
    
            if (proprietaire==null || mail.compareTo(proprietaire.getMail())!=0) throw new Exception("Compte inexist sur mail: "+mail);
            if (proprietaire != null && mdp.equals(proprietaire.getMdp())) 
            return proprietaire; 
            else throw new Exception("Invalid password");
            
        } catch (EmptyResultDataAccessException e) {
            throw new Exception("error during login", e);
        }
    }

    public List<Proprietaire> getAmis(int id) {

        String sql = "SELECT * FROM proprietaire where id !="+id;
        System.out.println("Executing SQL query: " + sql);

        // Utiliser BeanPropertyRowMapper pour mapper les résultats à des objets Plante
        List<Proprietaire> amis = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Proprietaire.class));

        return amis;

    }

    
}

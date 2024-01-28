package com.com_farm_back.hallo.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
            Proprietaire proprietaire = jdbcTemplate.queryForObject(sql, new Object[]{mail}, new BeanPropertyRowMapper<>(Proprietaire.class));

            if (proprietaire==null || mail.compareTo(proprietaire.getMail())!=0) throw new Exception("Compte inexist sur mail: "+mail);
            if (proprietaire != null && mdp.equals(proprietaire.getMdp())) 
                return proprietaire; 
            else throw new Exception("Invalid password");
            
        } catch (EmptyResultDataAccessException e) {
            throw new Exception("error during login", e);
        }
    }

    
}

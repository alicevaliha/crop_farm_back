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
public class ParcelleDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

   public List<Map<String, Object>>  getDataParcelle() {
        String sql = "SELECT * FROM v_all_concat";
        System.out.println("Executing SQL query: " + sql);

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        // Construire le JSONArray
        // JsonArray jsonArray = new JsonArray();
        // for (Map<String, Object> row : rows) {
        //     JsonObject jsonObject = new JsonObject();
        //     for (Map.Entry<String, Object> entry : row.entrySet()) {
        //         jsonObject.addProperty(entry.getKey(), entry.getValue().toString());
        //     }
        //     jsonArray.add(jsonObject);
        // }

        // // Sérialiser le JsonArray en format JSON
        // Gson gson = new Gson();
        // String jsonOutput = gson.toJson(jsonArray);
        // System.out.println("Serialized JSON Array: " + jsonOutput);

        return rows;
    }
    
}
package com.com_farm_back.hallo.dao;
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
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.List;
import java.util.Map;

@Repository
public class ParcelleDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JsonArray getDataParcelle() {
        String sql = "SELECT * FROM v_all_concat";
        System.out.println(sql);

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        // Construire le JSONArray
        JsonArray jsonArray = new JsonArray();
        for (Map<String, Object> row : rows) {
            JsonObject jsonRow = new JsonObject();
            for (Map.Entry<String, Object> entry : row.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                // Ajouter chaque paire clé-valeur à l'objet JSON de la ligne
                jsonRow.addProperty(key, value.toString());
            }
            // Ajouter l'objet JSON de la ligne au tableau JSON
            jsonArray.add(jsonRow);
        }

        return jsonArray;
    }
}

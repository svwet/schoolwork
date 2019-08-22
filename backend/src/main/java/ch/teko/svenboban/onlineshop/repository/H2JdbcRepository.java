package ch.teko.svenboban.onlineshop.repository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import org.springframework.web.client.RestTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class H2JdbcRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final String URL = "https://jsonplaceholder.typicode.com/posts";


    public String getProducts() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONArray jsonArray = new JSONArray();
        String sqlquery = "select * from products";
        jsonArray = getDataH2(sqlquery);

        return createJson(jsonArray, headers);
    }

    public String getProductsById(String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String sqlquery = "select * from products where id = " + id + "";
        JSONArray jsonArray = new JSONArray();
        jsonArray = getDataH2(sqlquery);

        return createJson(jsonArray, headers);
    }

    public void writeCartToDb() {

    }

    private JSONArray getDataH2(String sqlquery) {
        JSONArray jsonArray = new JSONArray();
        jdbcTemplate.query(sqlquery, new ResultSetExtractor<ResultSet>() {
            @Override
            public ResultSet extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    try {
                        String id_json = rs.getString("ID");
                        String name_json = rs.getString("NAME");
                        String desc_json = rs.getString("DESCRIPTION");
                        String price_json = rs.getString("PRICE");
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("ID", id_json);
                        jsonObject.put("NAME", name_json);
                        jsonObject.put("DESCRIPTION", desc_json);
                        jsonObject.put("PRICE", price_json);
                        jsonArray.put(jsonObject);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        });
        return jsonArray;
    }

    private String createJson(JSONArray jsonArray, HttpHeaders headers) {
        try {
            HttpEntity<String> entity = new HttpEntity<String>(jsonArray.toString(), headers);
            ResponseEntity<String> response = restTemplate().postForEntity(URL, entity, String.class);
            return response.getBody();
        } catch (JSONException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

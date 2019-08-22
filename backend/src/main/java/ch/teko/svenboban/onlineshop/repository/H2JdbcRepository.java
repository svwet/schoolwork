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

/**
 * @author sven.wetter@edu.teko.ch
 */

@Repository
public class H2JdbcRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final String URL = "https://jsonplaceholder.typicode.com/posts";

    /**
     * Collects all data from products table
     * @return returns the value as json
     */
    public String getProducts() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String sqlquery = "select * from products";

        return createJson(getDataH2(sqlquery), headers);
    }

    /**
     * Collect the data of a single products
     * @param id the id of the products
     * @return returns the values as json
     */
    public String getProductsById(String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String sqlquery = "select * from products where id = " + id + "";

        return createJson(getDataH2(sqlquery), headers);
    }

    public void writeCartToDb() {

    }

    /**
     * Collecting data from db and convert it into jsonarray
     * @param sqlquery the sql statment in string format
     * @return returns the data as jsonarray
     */
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

    /**
     * Mix up the array and http header to create valid http json
     * @param jsonArray the data as jsonarray format
     * @param headers the http header
     * @return return the mixed response body
     */
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

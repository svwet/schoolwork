package ch.teko.svenboban.onlineshop.controller;

import ch.teko.svenboban.onlineshop.model.OnetimePassword;
import ch.teko.svenboban.onlineshop.model.User;
import ch.teko.svenboban.onlineshop.repository.OnetimePasswordRepository;
import ch.teko.svenboban.onlineshop.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.*;

/**
 * @author: Petrovic Boban
 **/
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
public class UserRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRestController.class);
    private static final String URL = "https://jsonplaceholder.typicode.com/posts";

    private final UserRepository userRepository;
    private final OnetimePasswordRepository onetimePasswordRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public UserRestController(UserRepository userRepository, OnetimePasswordRepository onetimePasswordRepository, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.onetimePasswordRepository = onetimePasswordRepository;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/postRequestTest")
    public String send() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("title", "foo");
        requestBody.put("body", "bar");
        requestBody.put("userId", "1");

        String json;
        try {
            json = new ObjectMapper().writeValueAsString(requestBody);
            HttpEntity<String> entity = new HttpEntity<>(json, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(URL, entity, String.class);
            return response.getBody();
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{pid}")
    public User getUserByPid(@PathVariable String pid) {
        return userRepository.findUserByPid(pid);
    }

    @Scheduled(fixedDelay = 1000)
    public void checkAndRemoveOtps() {
        LOGGER.info("checking otp's={}", new Date());
        List<OnetimePassword> all = onetimePasswordRepository.findAll();
        all.forEach(entry -> {
            int i = entry.getCreatedAt()
                         .compareTo(getNowMinus5Seconds());
            if (i < 0) {
                onetimePasswordRepository.delete(entry);
                LOGGER.info("removed entry={}", entry.getId());
                OnetimePassword otp = new OnetimePassword();
                otp.setCreatedAt(new Timestamp(new Date().getTime()));
                otp.setPassword("test");
                onetimePasswordRepository.save(otp);
                LOGGER.info("added entry={}", otp.getId());
            }
        });
    }

    private Date getNowMinus5Seconds() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.SECOND, -5);
        return cal.getTime();
    }

}

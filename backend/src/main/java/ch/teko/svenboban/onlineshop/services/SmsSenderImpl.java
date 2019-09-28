package ch.teko.svenboban.onlineshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.net.InetSocketAddress;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sven.wetter@edu.teko.ch
 */


@Service
public class SmsSenderImpl implements SmsSender {
    private static final String URL = "https://connector.example.com:4300/rest/1.0.0/submit_sm/1234";
    private static final String PASSWORD = "Br94vera";
    private static final String DESTINATION_ADDR = "destination_addr";
    private static final String SOURCE_ADDR = "source_addr";
    private static final String SOURCE_ADDR_TON = "source_addr_ton";
    private static final Integer SOURCE_ADDR_TON_VALUE = 5;
    private static final String SHORT_MESSAGE = "short_message";
    private static final String USERNAME = "1234"; // Enter Short ID
    private static final String ALPHANUMERIC = "TEKO";
    private static final InetSocketAddress HOST = new InetSocketAddress("123.45.67.89", 4300); //Enter IP Adress

    private final RestTemplate restTemplate;
    private final UserService userService;

    @Autowired
    public SmsSenderImpl(RestTemplate restTemplate, UserService userService) {
        this.restTemplate = restTemplate;
        this.userService = userService;
    }

    public void send(String message) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(USERNAME, PASSWORD);
        headers.setHost(HOST);

        Map<String, Object> data = new HashMap<>();
        data.put(DESTINATION_ADDR, userService.getMobileFromCurrentUser());
        data.put(SOURCE_ADDR, ALPHANUMERIC);
        data.put(SOURCE_ADDR_TON, SOURCE_ADDR_TON_VALUE);
        data.put(SHORT_MESSAGE, message);

        HttpEntity<Map> request = new HttpEntity<>(data, headers);
        restTemplate.postForObject(URL, request, String.class);
    }
}

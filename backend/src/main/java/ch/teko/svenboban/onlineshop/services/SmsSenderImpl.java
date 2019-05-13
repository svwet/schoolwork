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
 * @author svenwetter
 */
@Service
public class SmsSenderImpl implements SmsSender {
    private static final String URL = "https://messagingproxy.swisscom.ch:4300/rest/1.0.0/submit_sm/87749";
    private static final String PASSWORD = "Br94vera";
    private static final String DESTINATION_ADDR = "destination_addr";
    private static final String SOURCE_ADDR = "source_addr";
    private static final String SOURCE_ADDR_TON = "source_addr_ton";
    private static final Integer SOURCE_ADDR_TON_VALUE = 5;
    private static final String SHORT_MESSAGE = "short_message";
    private static final String DESTINATION_ADDR_VALUE = "0798464541";
    private static final String USERNAME = "87749";
    private static final String ALPHANUMERIC = "TEKO";
    private static final String TEST_MESSAGE = "Test message SPRING REST";
    private static final InetSocketAddress HOST = new InetSocketAddress("217.192.8.32", 4300);

    private final RestTemplate restTemplate;

    @Autowired
    public SmsSenderImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String send() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(USERNAME, PASSWORD);
        headers.setHost(HOST);

        Map<String, Object> data = new HashMap<>();
        data.put(DESTINATION_ADDR, DESTINATION_ADDR_VALUE);
        data.put(SOURCE_ADDR, ALPHANUMERIC);
        data.put(SOURCE_ADDR_TON, SOURCE_ADDR_TON_VALUE);
        data.put(SHORT_MESSAGE, TEST_MESSAGE);

        HttpEntity<Map> request = new HttpEntity<>(data, headers);
        return restTemplate.postForObject(URL, request, String.class);
    }
}
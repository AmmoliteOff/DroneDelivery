package com.hackathon.dronedelivery.util;

import com.hackathon.dronedelivery.model.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RequestToMarketplace {
    public static void updateStatus(Order order) {
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://marketplace.com/api/order";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        HttpEntity<String> requestEntity = new HttpEntity<>(order.toString(), headers);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);

        String response = responseEntity.getBody();
        System.out.println("Ответ от сервера: " + response);
    }
}

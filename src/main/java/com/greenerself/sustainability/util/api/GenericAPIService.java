package com.greenerself.sustainability.util.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GenericAPIService {

    private final RestTemplate restTemplate;

    @Autowired
    public GenericAPIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <T> T getForObject(String url, Class<T> responseType) {
        return restTemplate.getForObject(url, responseType);
    }

    public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType) {
        return restTemplate.getForEntity(url, responseType);
    }
}

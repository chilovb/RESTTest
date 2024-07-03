package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalApiServiceImpl implements ExternalApiService {

    @Value("${external.api.url}") // Inject external API URL from properties
    private String externalApiUrl;

    @Override
    public Product getCountryData(String countryCode) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format(externalApiUrl, countryCode);
        //TODO hier JSON vertalen in product
        ResponseEntity<Product> response = restTemplate.getForEntity(url, Product.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new Exception("Failed to retrieve data from external API. Status code: " + response.getStatusCodeValue());
        }
    }
}

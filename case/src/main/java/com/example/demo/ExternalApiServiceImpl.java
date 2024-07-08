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
        //ResponseEntity<Country> response = restTemplate.getForEntity(url, Country.class);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String body = skipBrackets(response.getBody());
        // translate JSON to Country
        Country country = Country.parseJson(body);
        if (response.getStatusCode() == HttpStatus.OK) {
            return makeProductFromCountry(country);
        } else {
            throw new Exception("Failed to retrieve data from external API. Status code: " + response.getStatusCodeValue());
        }
    }

    public static String skipBrackets(String str) {
        if (str.isEmpty() || !str.startsWith("[") || !str.endsWith("]")) {
            return str; // No brackets or invalid format, return original string
        }
        // Remove leading [ and trailing ]
        return str.substring(1, str.length() - 1);
    }

    private Product makeProductFromCountry(Country body) {
        return new Product(body.getCca2(), body.getPostalCode().getFormat(), body.getPostalCode().getRegex());
    }
}

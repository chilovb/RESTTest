package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExternalApiServiceImplTest {

    @InjectMocks
    private ExternalApiServiceImpl externalApiService;

    @Mock
    private RestTemplate restTemplate;

    @Value("${external.api.url}") // Inject for test (optional)
    private String externalApiUrl;

    @Test
    public void testGetCountryData_ValidCountryCode_ReturnsProduct() throws Exception {
        String countryCode = "US";
        String expectedJson = "{\"cca2\":\"US\",\"postal_code\":{\"format\":\"#####\",\"regex\":\"^[0-9]{5}$\"}}";
        Country expectedCountry = Country.parseJson(expectedJson);
        Product expectedProduct = new Product(expectedCountry.getCca2(), expectedCountry.getPostalCode().getFormat(), expectedCountry.getPostalCode().getRegex());

        // Mock RestTemplate behavior
        String url = String.format(externalApiUrl, countryCode);
        ResponseEntity<String> response = new ResponseEntity<>(expectedJson, HttpStatus.OK);
        when(restTemplate.getForEntity(url, String.class)).thenReturn(response);

        // Execute the method
        Product actualProduct = externalApiService.getCountryData(countryCode);

        // Verify returned product
        assertThat(actualProduct).isEqualTo(expectedProduct);
    }

    @Test
    public void testGetCountryData_ExternalApiError_ThrowsException() throws Exception {
        String countryCode = "US";

        // Mock RestTemplate to return error status code
        String url = String.format(externalApiUrl, countryCode);
        when(restTemplate.getForEntity(url, String.class)).thenReturn(new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR));

        // Execute the method (exception expected)
        externalApiService.getCountryData(countryCode);
    }

    @Test
    public void testGetCountryData_InvalidJsonResponse_ThrowsException() throws Exception {
        String countryCode = "US";
        String invalidJson = "Invalid JSON";

        // Mock RestTemplate to return invalid JSON
        String url = String.format(externalApiUrl, countryCode);
        when(restTemplate.getForEntity(url, String.class)).thenReturn(new ResponseEntity<>(invalidJson, HttpStatus.OK));

        // Execute the method (exception expected)
        externalApiService.getCountryData(countryCode);
    }
}

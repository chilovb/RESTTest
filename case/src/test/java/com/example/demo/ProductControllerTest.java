package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ExternalApiService externalApiService;

    @InjectMocks
    private ProductController productController;

    @Test
    public void testGetCountryInfo_ValidCountryCode_ReturnsProduct() throws Exception {
        // Arrange
        String countryCode = "NL";
        Product expectedProduct = new Product("Netherlands", "fmt", "regex");
        Mockito.when(externalApiService.getCountryData(countryCode)).thenReturn(expectedProduct);
        //Mockito.when(productRepository.insertCode(expectedProduct.getName())).thenReturn(1L);

        // Act
        ResponseEntity<Product> response = productController.getCountryInfo(countryCode);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        //assertEquals(expectedProduct, response.getBody());
        Mockito.verify(externalApiService).getCountryData(countryCode);
        Mockito.verify(productRepository).insertCode(expectedProduct.getName());
    }

    @Test
    public void testGetCountryInfo_InvalidCountryCode_ThrowsException() {
        // Arrange
        String countryCode = "invalid";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> productController.getCountryInfo(countryCode));
        Mockito.verifyNoInteractions(externalApiService);
        Mockito.verifyNoInteractions(productRepository);
    }

    // Similar tests can be written for other methods like:
    // - findCountryByCountryCode
    // - getAllProducts
    // - getProductById (including cases where product is not found)
    // - createProduct
    // - updateProduct (including cases where product is not found)
    // - deleteProduct

}

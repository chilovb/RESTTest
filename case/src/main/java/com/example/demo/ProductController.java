package com.example.demo;

import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final com.example.demo.ProductRepository productRepository;
    private final ExternalApiService externalApiService; // Inject an ExternalApiService dependency

    public ProductController(ProductRepository productRepository, ExternalApiService externalApiService) {
        this.productRepository = productRepository;
        this.externalApiService = externalApiService;
    }

    @GetMapping("/load/{name}")
    @Transactional
    public ResponseEntity<Product> getCountryInfo(@PathVariable String name) throws Exception {
        // Optional validation (if not using CountryCode model)
        if (name.length() != 2) {
            throw new IllegalArgumentException("Invalid country code format.");
        }
        // translate JSON to product  in Country.getJson()
        Product countryData  = (Product) externalApiService.getCountryData(name); // Call external API service
        productRepository.insertCode(countryData.getName(), countryData.getPostalCodeFmt(), countryData.getRegex());
        return ResponseEntity.ok(countryData);
    }


    @GetMapping("/code/{name}")
    public List<Product> findCountryByCountryCode(@PathVariable String name) {
        return productRepository.findCountryByCountryCode(name);
    }

    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @GetMapping("/HI")
    public Product getProductById() {
        return null;
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product existingProduct = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        existingProduct.setName(product.getName());
        existingProduct.setPostalCodeFmt(product.getPostalCodeFmt());
        existingProduct.setRegex(product.getRegex());
        return productRepository.save(existingProduct);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
    }
}

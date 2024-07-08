package com.example.demo;

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

    @GetMapping("/load/{countryCode}")
    public ResponseEntity<Product> getCountryInfo(@PathVariable String countryCode) throws Exception {
        // Optional validation (if not using CountryCode model)
        if (countryCode.length() != 2) {
            throw new IllegalArgumentException("Invalid country code format.");
        }
        // translate JSON to product  in Country.getJson()
        Product countryData  = (Product) externalApiService.getCountryData(countryCode); // Call external API service
        //Product countryData = new Product("xx", "fmt", "rg");
        productRepository.insertCode(countryData.getName());
        return ResponseEntity.ok(countryData);
    }


    @GetMapping("/code/{code}")
    public List<Product> findCountryByCountryCode(@PathVariable String code) {
        return productRepository.findCountryByCountryCode(code);
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

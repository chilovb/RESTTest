package com.example.demo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM countries WHERE code = :countryCode", nativeQuery = true)
    List<Product> findCountryByCountryCode(@Param("countryCode") String countryCode);


}

package com.example.demo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM product WHERE name = :name", nativeQuery = true)
    List<Product> findCountryByCountryCode(@Param("name") String name);

    @Modifying
    @Query(value = "INSERT INTO  product (name, fmt, regex) VALUES (:name, :fmt, :regex)", nativeQuery = true)
    void insertCode(@Param("name") String name, @Param("fmt") String fmt, @Param("regex") String regex);

}

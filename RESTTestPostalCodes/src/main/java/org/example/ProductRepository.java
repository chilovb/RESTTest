package org.example;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM countries WHERE code = :countryCode", nativeQuery = true)
    List<Product> findCountryByCountryCode(@Param("countryCode") String countryCode);

}

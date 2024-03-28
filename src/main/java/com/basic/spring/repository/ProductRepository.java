package com.basic.spring.repository;

import com.basic.spring.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

// Repository = DAO(data access object)
public interface ProductRepository extends JpaRepository<Product, Long> { // <entity class, type of pk>

    // สร้าง func สำหรับการ where ด้วย name
    // select * form product where name = x
    Optional<Product> findByName(String name);

    // select * form product where name = x limit = 1
    List<Product> findTopByName(String name);

    // select * form product link '%name%'
//    List<Product> findByNameContaining(String name);

    // select * form product link '%name%' and stock > x
//    List<Product> findByNameContainingStockAndGreaterThan(String name, int stock);
//
//    // select * form product link '%name%' and stock > x order by stock desc
//    List<Product> findByNameContainingStockAndGreaterThanOrderByStockDesc(String name, int stock);

    // Query แบบเขียน sql เอง
    @Query(value = "SELECT * FORM PRODUCT P WHERE P.STOCK > 0", nativeQuery = true)
    List<Product> checkOutOfStock();
}

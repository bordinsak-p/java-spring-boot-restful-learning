package com.basic.spring.repository;

import com.basic.spring.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Repository = DAO(data access object)
public interface ProductRepository extends JpaRepository<Product, Long> { // <entity class, type of pk>

    // สร้าง func สำหรับการ where ด้วย name
    // select * form product where name = ?
    Optional<Product> findByName(String name);

    // select * form product where name = ? limit = 1
    Optional<Product> findTopByName(String name);
}

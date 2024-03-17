package com.basic.spring.services;

import com.basic.spring.controllers.request.ProductRequest;
import com.basic.spring.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProduct();
    Product getProductById(Long id);
    Product getProductByName(String name);
    Product addProduct(ProductRequest productRequest);
    Product editProduct(ProductRequest productRequest, Long id);
    void deleteProduct(Long id);
}

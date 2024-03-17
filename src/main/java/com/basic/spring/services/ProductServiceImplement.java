package com.basic.spring.services;

import com.basic.spring.controllers.request.ProductRequest;
import com.basic.spring.exception.ProductNotFoundException;
import com.basic.spring.models.Product;
import com.basic.spring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImplement implements ProductService {

    private final StorageService storageService;
    private final ProductRepository productRepository;


    @Autowired // inject
    public ProductServiceImplement(StorageService storageService, ProductRepository productRepository) {
        this.storageService = storageService;
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll(Sort.by(Sort.Direction.DESC, "updatedDate")); // Sort.by(Sort.Direction.DESC, "updatedDate") = ORDER BY updatedDate DESC
    }

    @Override
    public Product getProductById(Long id) {
        // Optional มีโอกาศที่ค่าจะออกมา null
        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            return product.get(); // Optional ต้อง ใช้ method get เพื่อนำค่าออกมา
        } else {
            throw new ProductNotFoundException(id);
        }

    }

    @Override
    public Product getProductByName(String name) {
        Optional<Product> product = productRepository.findByName(name);

        if (product.isPresent()) {
            return product.get(); // Optional ต้อง ใช้ method get เพื่อนำค่าออกมา
        } else {
            throw new ProductNotFoundException(name);
        }
    }

    @Override
    public Product addProduct(ProductRequest productRequest) {
        String fileName = storageService.store(productRequest.getImageFile());

        Product addProduct = new Product();
        addProduct
                .setName(productRequest.getName())
                .setImage(fileName)
                .setPrice(productRequest.getPrice())
                .setStock(productRequest.getStock());

        return productRepository.save(addProduct);
    }

    @Override
    public Product editProduct(ProductRequest productRequest, Long id) {
        String fileName = storageService.store(productRequest.getImageFile());

        // Optional มีโอกาศที่ค่าจะออกมา null
        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            Product existingProduct = product.get();

            // ถ้า fileName ไม่เท่ากับค่า null ให้ทำการ set image ลงไปที่ obj
            if (fileName != null) {
                existingProduct.setImage(fileName);
            }
            existingProduct
                    .setName(productRequest.getName())
                    .setPrice(productRequest.getPrice())
                    .setStock(productRequest.getStock());

            return productRepository.save(existingProduct);
        } else {
            throw new ProductNotFoundException(id);
        }
    }

    @Override
    public void deleteProduct(Long id) {
        try {
            productRepository.deleteById(id);
        } catch (Exception e) {
            throw new ProductNotFoundException(id);
        }
    }
}

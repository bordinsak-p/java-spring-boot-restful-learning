package com.basic.spring.controllers.api;

import com.basic.spring.controllers.request.ProductRequest;
import com.basic.spring.models.Product;
import com.basic.spring.services.ProductService;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProduct() {
        return productService.getAllProduct();
    }

    @GetMapping("/search")
    public Product searchProductByName(@RequestParam String productName) {
        return productService.getProductByName(productName);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable long id) {
        return productService.getProductById(id);
    }

    @GetMapping("/out-of-stock")
    public List<Product> getProductCheckOutOfStock() {
        return productService.getProductOutOfStock();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    // 200  // BindingResult จะต้องเป็น parameter ที่ต่อจาก @Valid เท่านั้น ถ้าไม่ต่อจะเกิด exception ได้
    public Product addProduct(@Valid ProductRequest productRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(filedError -> {
                throw new ValidationException(filedError.getField() + ": " + filedError.getDefaultMessage());
            });
        }
        return productService.addProduct(productRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED) // 200
    public Product editProduct(@Valid ProductRequest product, BindingResult bindingResult, @PathVariable long id) {
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(filedError -> {
                throw new ValidationException(filedError.getField() + ": " + filedError.getDefaultMessage());
            });
        }
        return productService.editProduct(product, id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT) // 201
    public void deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
    }

}

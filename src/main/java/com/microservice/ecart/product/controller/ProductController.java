package com.microservice.ecart.product.controller;

import com.microservice.ecart.product.exception.ResourceNotFoundException;
import com.microservice.ecart.product.model.Category;
import com.microservice.ecart.product.model.Product;
import com.microservice.ecart.product.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    
    
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        // If empty, throws exception caught cleanly by GlobalExceptionHandler
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
    }

    @GetMapping("/category/{categoryName}")
    public List<Product> getProductsByCategory(@PathVariable String categoryName) {
        // If valueOf fails, it throws IllegalArgumentException caught cleanly by GlobalExceptionHandler
        Category category = Category.valueOf(categoryName.toUpperCase());
        return productRepository.findByCategory(category);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Explicitly return HTTP 201 Created status
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }
}

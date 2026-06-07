package com.microservice.ecart.product.repository;



import com.microservice.ecart.product.model.Category;
import com.microservice.ecart.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(Category category);
}

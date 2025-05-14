package com.project.shopease.repositories;

import java.util.*;
import com.project.shopease.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameStartingWithIgnoreCase(String name);
}

package com.project.shopease.services;

import com.project.shopease.models.Product;
import com.project.shopease.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepo;

    public ProductService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> getAll() {
        return productRepo.findAll();
    }

    public Product getById(Long id) {
        return productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product save(Product product) {
        return productRepo.save(product);
    }

    public void delete(Long id) {
        productRepo.deleteById(id);
    }

    public List<Product> searchByName(String name) {
        return productRepo.findByNameStartingWithIgnoreCase(name);
    }
}

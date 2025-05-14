package com.project.shopease.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;  // Product being added to cart

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;  // Cart that the product belongs to

    private int quantity;

    // Calculate total price for this CartItem (product price * quantity)
    public double getTotalPrice() {
        return this.product.getPrice() * this.quantity;
    }
}

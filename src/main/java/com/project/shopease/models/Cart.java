package com.project.shopease.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CartItem> cartItems;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    private double totalAmount;

    // Calculate total amount for the cart (sum of all cart items)
    public void updateTotalAmount() {
        this.totalAmount = 0.0;

        for (CartItem item : cartItems) {
            this.totalAmount += item.getQuantity() * item.getProduct().getPrice();
        }
        System.out.println(totalAmount+" "+cartItems);
    }
}


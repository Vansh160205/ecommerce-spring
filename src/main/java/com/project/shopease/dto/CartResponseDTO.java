package com.project.shopease.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CartResponseDTO {
    private Long id;
    private List<CartItemDTO> cartItems;
    private double totalAmount;

    public CartResponseDTO(Long id, List<CartItemDTO> cartItems, double totalAmount) {
        this.id = id;
        this.cartItems = cartItems;
        this.totalAmount = totalAmount;
    }

    // getters and setters
}

package com.project.shopease.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartItemDTO {
    private Long productId;
    private String productName;
    private int quantity;
    private double totalPrice;

    public CartItemDTO(Long productId, String productName, int quantity, double totalPrice) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    // getters and setters
}

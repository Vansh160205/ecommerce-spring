package com.project.shopease.controllers;

import com.project.shopease.dto.CartResponseDTO;
import com.project.shopease.models.Cart;
import com.project.shopease.models.Users;
import com.project.shopease.repositories.UserRepo;
import com.project.shopease.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserRepo userRepository;

    // Get cart for user
    @GetMapping(value="/user/{userId}", produces = "application/json")
    public ResponseEntity<CartResponseDTO> getCart(@PathVariable Long userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        CartResponseDTO cart = cartService.getCartForUser(user);
        return ResponseEntity.ok(cart);
    }

    // Add product to cart
    @PostMapping("/add")
    public ResponseEntity<Void> addProductToCart(@RequestParam Long userId,
                                                 @RequestParam Long productId,
                                                 @RequestParam int quantity) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        cartService.addProductToCart(user, productId, quantity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // Update cart item quantity
    @PutMapping("/update")
    public ResponseEntity<Void> updateCartItemQuantity(@RequestParam Long userId,
                                                       @RequestParam Long productId,
                                                       @RequestParam int quantity) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        cartService.updateCartItemQuantity(user, productId, quantity);
        return ResponseEntity.ok().build();
    }

    // Remove product from cart
    @DeleteMapping("/remove")
    public ResponseEntity<Void> removeProductFromCart(@RequestParam Long userId,
                                                      @RequestParam Long productId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        cartService.removeProductFromCart(user, productId);
        return ResponseEntity.noContent().build();
    }
}

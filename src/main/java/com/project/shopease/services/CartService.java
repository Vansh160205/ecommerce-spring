package com.project.shopease.services;

import com.project.shopease.dto.CartItemDTO;
import com.project.shopease.dto.CartResponseDTO;
import com.project.shopease.models.Cart;
import com.project.shopease.models.CartItem;
import com.project.shopease.models.Product;
import com.project.shopease.models.Users;
import com.project.shopease.repositories.CartItemRepository;
import com.project.shopease.repositories.CartRepository;
import com.project.shopease.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    // Get cart for user
    public CartResponseDTO getCartForUser(Users user) {
        Cart cart = cartRepository.findByUser(user).orElseThrow();

        List<CartItemDTO> itemDTOs = cart.getCartItems().stream().map(item ->
                new CartItemDTO(
                        item.getProduct().getId(),
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.getTotalPrice()
                )
        ).toList();

        return new CartResponseDTO(cart.getId(), itemDTOs, cart.getTotalAmount());
    }

    // Create a new cart for user if none exists
    @Transactional
    private Cart createCart(Users user) {
        Cart newCart = new Cart();
        newCart.setUser(user);
        return cartRepository.save(newCart);
    }

    // Add product to cart
    @Transactional
    public void addProductToCart(Users user, Long productId, int quantity) {
        Cart cart = cartRepository.findByUser(user).orElseThrow();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));


        // Check if product already in cart
        CartItem cartItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().equals(product))
                .findFirst()
                .orElse(new CartItem());

        cartItem.setProduct(product);
        cartItem.setQuantity(cartItem.getQuantity() + quantity);
        cartItem.setCart(cart);

        cartItemRepository.save(cartItem);
        cart.updateTotalAmount();

        cartRepository.save(cart);
    }

    // Update quantity of a product in cart
    @Transactional
    public void updateCartItemQuantity(Users user, Long productId, int newQuantity) {
        Cart cart = cartRepository.findByUser(user).orElseThrow();
        CartItem cartItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Product not found in cart"));

        cartItem.setQuantity(newQuantity);
        cart.updateTotalAmount();
        cartRepository.save(cart);
    }

    // Remove product from cart
    @Transactional
    public void removeProductFromCart(Users user, Long productId) {
        Cart cart = cartRepository.findByUser(user).orElseThrow();
        CartItem cartItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Product not found in cart"));

        cart.getCartItems().remove(cartItem);
        cart.updateTotalAmount();
        cartRepository.save(cart);
    }
}

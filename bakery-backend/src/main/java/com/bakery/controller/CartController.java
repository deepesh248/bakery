package com.bakery.controller;

import com.bakery.model.CartItem;
import com.bakery.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(cartService.getCartItems(userId));
    }


    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestBody CartItem item) {
        cartService.addToCart(item);
        return ResponseEntity.ok("Added to cart");
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> clearCart(@PathVariable("userId") Long userId) {
        try {
            cartService.clearCart(userId);
            return ResponseEntity.ok("Cart cleared successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to clear cart: " + e.getMessage());
        }
    }
    
    @DeleteMapping("/item/{id}")
    public ResponseEntity<String> removeItem(@PathVariable("id") Long id) {
        cartService.removeCartItem(id);
        return ResponseEntity.ok("Item removed");
    }



}

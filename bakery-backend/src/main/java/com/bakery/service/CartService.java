package com.bakery.service;

import com.bakery.model.CartItem;
import com.bakery.model.Product;
import com.bakery.model.User;
import com.bakery.repository.CartItemRepository;
import com.bakery.repository.ProductRepository;
import com.bakery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    private static final Logger logger = LoggerFactory.getLogger(CartService.class);

    public List<CartItem> getCartItems(Long userId) {
        try {
            return cartItemRepository.findByUserId(userId);
        } catch (Exception e) {
            logger.error("Error while fetching cart items for userId: " + userId, e);
            throw new RuntimeException("Error fetching cart items", e);
        }
    }

    public void addToCart(CartItem item) {
        try {
            Long userId = item.getUser().getId();
            Long productId = item.getProduct().getId();

            User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
            Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

            Optional<CartItem> existingItem = cartItemRepository.findByUserAndProduct(user, product);

            if (existingItem.isPresent()) {
                CartItem cartItem = existingItem.get();
                cartItem.setQuantity(item.getQuantity()); // ✅ update quantity directly
                cartItemRepository.save(cartItem);
            } else {
                CartItem newItem = new CartItem();
                newItem.setUser(user);
                newItem.setProduct(product);
                newItem.setQuantity(item.getQuantity());
                cartItemRepository.save(newItem);
            }

        } catch (Exception e) {
            logger.error("Error while adding item to cart: " + item, e);
            throw new RuntimeException("Error adding item to cart", e);
        }
    }

    public void clearCart(Long userId) {
        try {
            logger.info("Clearing cart for userId: " + userId);
            cartItemRepository.deleteByUserId(userId);
        } catch (Exception e) {
            logger.error("Error while clearing cart for userId: " + userId, e);
            throw new RuntimeException("Error clearing cart", e);
        }
    }

    public void removeCartItem(Long id) {
        try {
            cartItemRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Error while removing cart item with id: " + id, e);
            throw new RuntimeException("Error removing cart item", e);
        }
    }
}

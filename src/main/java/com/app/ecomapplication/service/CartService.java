package com.app.ecomapplication.service;

import com.app.ecomapplication.entity.CartItem;
import com.app.ecomapplication.entity.Product;
import com.app.ecomapplication.entity.User;
import com.app.ecomapplication.entity.dto.CartItemRequest;
import com.app.ecomapplication.repository.CartItemRepository;
import com.app.ecomapplication.repository.ProductRepository;
import com.app.ecomapplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    public final UserRepository userRepository;

    public Boolean addToCart(String userId, CartItemRequest request) {
        // Look for product
        Optional<Product> productOpt = productRepository.findById(request.getProductId());
        if (productOpt.isEmpty()) {
            return false;
        }
        Product product = productOpt.get();
        if(product.getStockQuantity() < request.getQuantity()) return false;

        Optional<User> userOpt = userRepository.findById(Long.valueOf(userId));
        if(userOpt.isEmpty()) return false;
        User user = userOpt.get();

        CartItem existingCartItem = cartItemRepository.findByUserAndProduct(user, product);
        if(existingCartItem != null) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + request.getQuantity());
            existingCartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(request.getQuantity())));
            cartItemRepository.save(existingCartItem);
        }
        else {
            CartItem cartItem = new CartItem();
            cartItem.setUser(user);
            cartItem.setProduct(product);
            cartItem.setQuantity(request.getQuantity());
            cartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(request.getQuantity())));
            cartItemRepository.save(cartItem);
        }
        return true;
    }
}

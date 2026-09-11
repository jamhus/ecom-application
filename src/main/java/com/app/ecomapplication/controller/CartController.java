package com.app.ecomapplication.controller;

import com.app.ecomapplication.entity.dto.CartItemRequest;
import com.app.ecomapplication.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<String> addToCart(
            @RequestHeader("X-User-ID") String userId,
            @RequestBody CartItemRequest cartItemRequest
    ) {
        if(cartService.addToCart(userId, cartItemRequest))
            return ResponseEntity.status(HttpStatus.CREATED).build();
        return ResponseEntity.badRequest().body("Product is out of stock or not found");
    }
}

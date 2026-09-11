package com.app.ecomapplication.repository;

import com.app.ecomapplication.entity.CartItem;
import com.app.ecomapplication.entity.Product;
import com.app.ecomapplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByUserAndProduct(User user, Product productEntity);
}

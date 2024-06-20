package com.James.order.eats.repository;

import com.James.order.eats.model.Cart;
import com.James.order.eats.model.CartItem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    public Cart findByCustomerId(Long userId);

}


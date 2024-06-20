package com.James.order.eats.service;

import com.James.order.eats.model.Cart;
import com.James.order.eats.model.CartItem;
import com.James.order.eats.requests.addCartItemRequest;

public interface CartService {

    public CartItem addCartItem(addCartItemRequest req, String jwt) throws Exception;

    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception;

    public Cart removeCartItem(Long cartItemId, String jwt) throws Exception;

    public Long calculateCartTotal(Cart cart) throws Exception;

    public Cart findCartById(Long id) throws Exception;

    public Cart findCartByUserId(Long userId)throws Exception;

    public Cart clearCart(Long userId)throws Exception;
}

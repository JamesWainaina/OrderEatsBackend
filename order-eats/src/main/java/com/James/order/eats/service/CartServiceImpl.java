package com.James.order.eats.service;

import com.James.order.eats.model.Cart;
import com.James.order.eats.model.CartItem;
import com.James.order.eats.model.Food;
import com.James.order.eats.model.User;
import com.James.order.eats.repository.CartItemRepository;
import com.James.order.eats.repository.CartRepository;
import com.James.order.eats.requests.addCartItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private FoodService foodService;

    @Override
    public CartItem addCartItem(addCartItemRequest req, String jwt) throws Exception {
        User user = userService.findByJwtToken(jwt);
        Food food = foodService.findFoodById(req.getFoodId());

        Cart cart = cartRepository.findByCustomerId(user.getId());

        for(CartItem cartItem : cart.getItems()){
            if(cartItem.getFood().equals(food)){
                int newQuantity = (int) (cartItem.getQuantity() + req.getQuantity());
                return updateCartItemQuantity(cartItem.getId(), newQuantity);
            }
        }

        CartItem cartItem = new CartItem();

        cartItem.setFood(food);
        cartItem.setCart(cart);
        cartItem.setQuantity(Math.toIntExact(req.getQuantity()));
        cartItem.setIngredients(req.getIngredients());
        cartItem.setTotalPrice(food.getPrice() * req.getQuantity());

        CartItem savedCartItem = cartItemRepository.save(cartItem);

        cart.getItems().add(savedCartItem);

        return savedCartItem;
    }

    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {
        Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);
        if (cartItem.isEmpty()){
            throw  new Exception("Cart Item not found");
        }
        CartItem item = cartItem.get();
        item.setQuantity(quantity);
        item.setTotalPrice(item.getFood().getPrice() * quantity);

        return cartItemRepository.save(item);
    }

    @Override
    public Cart removeCartItem(Long cartItemId, String jwt) throws Exception {
        User user = userService.findByJwtToken(jwt);
        Cart cart = cartRepository.findByCustomerId(user.getId());

        Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);
        if (cartItem.isEmpty()){
            throw  new Exception("Cart Item not found");
        }

        CartItem item = cartItem.get();

        cart.getItems().remove(item);

        return cartRepository.save(cart);
    }

    @Override
    public Long calculateCartTotal(Cart cart) throws Exception {
        Long total = 0L;

        for(CartItem cartItem: cart.getItems()){
            total += cartItem.getFood().getPrice() * cartItem.getQuantity();
        }
        return total;
    }


    @Override
    public Cart findCartById(Long id) throws Exception {
        Optional<Cart> optionalCart = cartRepository.findById(id);
        if (optionalCart.isEmpty()){
            throw  new Exception("Cart is not found with that Id  "+ id);
        }
        return optionalCart.get();
    }

    @Override
    public Cart findCartByUserId(Long userId) throws Exception {
//        User user = userService.findByJwtToken(jwt);
       Cart cart = cartRepository.findByCustomerId(userId);

       cart.setTotal(calculateCartTotal(cart));
       return  cart;
    }

    @Override
    public Cart clearCart(Long userId) throws Exception {
//        User user = userService.findByJwtToken();
        Cart cart = findCartByUserId(userId);
        cart.getItems().clear();
        return cartRepository.save(cart);
    }
}

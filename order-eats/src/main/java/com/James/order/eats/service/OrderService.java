package com.James.order.eats.service;

import com.James.order.eats.model.Order;
import com.James.order.eats.model.User;
import com.James.order.eats.requests.OrderRequest;

import java.util.List;

public interface OrderService {

    public Order createOrder(OrderRequest order, User user) throws Exception;

    public Order updateOrder(Long orderId, String orderStatus) throws Exception;

    public void cancelOrder(Long orderId) throws Exception;

    public List<Order> getUsersOrder(Long userId) throws  Exception;

    public List<Order> getRestaurantsOrder(Long RestaurantId, String orderStatus) throws Exception;

    public  Order findOrderById(Long orderId) throws Exception;
}

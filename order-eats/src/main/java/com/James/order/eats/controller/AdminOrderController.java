package com.James.order.eats.controller;

import com.James.order.eats.model.Order;
import com.James.order.eats.model.User;
import com.James.order.eats.service.OrderService;
import com.James.order.eats.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/admin")
@RestController
public class AdminOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;


    @PostMapping("/order/restaurant/{id}")
    public ResponseEntity<List<Order>> getOrderHistory(
            @PathVariable Long id,
            @RequestParam(required = false) String order_status,
            @RequestHeader("Authorization") String jwt)
            throws Exception {
        User user = userService.findByJwtToken(jwt);
        List<Order> order = orderService.getRestaurantsOrder(id, order_status);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("/order/{orderId}/orderStatus")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable Long id,
            @PathVariable String order_status,
            @RequestHeader("Authorization") String jwt)
            throws Exception {
        User user = userService.findByJwtToken(jwt);
        Order order = orderService.updateOrder(id, order_status);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}

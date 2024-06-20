package com.James.order.eats.controller;

import com.James.order.eats.model.Food;
import com.James.order.eats.model.Category;
import com.James.order.eats.model.Restaurant;
import com.James.order.eats.model.User;
import com.James.order.eats.service.FoodService;
import com.James.order.eats.service.RestaurantService;
import com.James.order.eats.service.UserService;
import com.James.order.eats.requests.CreateFoodRequest;
import com.James.order.eats.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest req,
                                           @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findByJwtToken(jwt);
        Restaurant restaurant = restaurantService.findRestaurantById(req.getRestaurantId());
        Food food = foodService.createFood(req, (Category) req.getCategory(), restaurant);
        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<MessageResponse> DeleteFood(@PathVariable Long id,
                                           @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findByJwtToken(jwt);
        foodService.deleteFood(id);
        MessageResponse res = new MessageResponse();

        res.setMessage("Food deleted successfully");

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Food> UpdateFoodAvailability(@PathVariable Long id,
                                           @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findByJwtToken(jwt);
        Food food = foodService.updateFoodAvailability(id);
        return new ResponseEntity<>(food, HttpStatus.OK);
    }
}

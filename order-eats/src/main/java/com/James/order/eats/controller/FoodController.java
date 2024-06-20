package com.James.order.eats.controller;

import com.James.order.eats.model.Food;
import com.James.order.eats.model.User;
import com.James.order.eats.service.FoodService;
import com.James.order.eats.service.RestaurantService;
import com.James.order.eats.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/food")
@RestController
public class FoodController {
    @Autowired
    private FoodService foodService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @GetMapping("{/search}")
    public ResponseEntity<List<Food>> searchFood(@RequestParam String keyword,
                                           @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findByJwtToken(jwt);
        List<Food> foods = foodService.searchFood(keyword);
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

    @GetMapping("{/restaurant/{restaurantId}}")
    public ResponseEntity<List<Food>> getRestaurantFood(
            @RequestParam Boolean vegetarian,
            @RequestParam Boolean seasonal,
            @RequestParam Boolean nonVeg,
            @RequestParam Long restaurantId,
            @RequestParam(required = false) String foodCategory,
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findByJwtToken(jwt);
        List<Food> foods = foodService.getFoodByRestaurant(restaurantId, vegetarian, nonVeg, seasonal, foodCategory);
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }
}

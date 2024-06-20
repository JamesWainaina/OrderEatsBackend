package com.James.order.eats.controller;

import com.James.order.eats.Dto.RestaurantDto;
import com.James.order.eats.model.Restaurant;
import com.James.order.eats.model.User;
import com.James.order.eats.service.RestaurantService;
import com.James.order.eats.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController()
@RequestMapping("/api/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> SearchRestaurant(
            @RequestHeader("Authorization") String jwt,
            @RequestParam String keyword) throws Exception {

        User user = userService.findByJwtToken(jwt);
        List<Restaurant> restaurant= restaurantService.searchRestaurants(keyword);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);

    }

    @GetMapping()
    public ResponseEntity<List<Restaurant>> getAllRestaurant(
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findByJwtToken(jwt);
        List<Restaurant> restaurant= restaurantService.getAllRestaurants();
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findRestaurantById(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id) throws Exception {

        User user = userService.findByJwtToken(jwt);
        Restaurant restaurant= restaurantService.findRestaurantById(id);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);

    }

    @PutMapping("/{id}/add_favourites")
    public ResponseEntity<RestaurantDto> addToFavourites(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id) throws Exception {

        User user = userService.findByJwtToken(jwt);
        RestaurantDto restaurantDto = restaurantService.addTOFavourites(id,user);
        return new ResponseEntity<>(restaurantDto, HttpStatus.OK);

    }



}

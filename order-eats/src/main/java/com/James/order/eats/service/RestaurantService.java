package com.James.order.eats.service;
import com.James.order.eats.Dto.RestaurantDto;
import com.James.order.eats.model.Restaurant;
import com.James.order.eats.model.User;
import com.James.order.eats.requests.CreateRestaurantRequest;

import java.util.List;

public interface RestaurantService {

  public Restaurant createRestaurant(CreateRestaurantRequest req , User user)throws Exception;

  public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updateRestaurant) throws Exception;

  public void deleteRestaurant(Long restaurantId) throws Exception;


  public List<Restaurant>  getAllRestaurants();


  public List<Restaurant> searchRestaurants(String keyword);

  public Restaurant findRestaurantById(Long restaurantId) throws Exception;

  public Restaurant getRestaurantByUserId(Long userId) throws Exception;

  public RestaurantDto addTOFavourites(Long restaurantId, User user) throws Exception;

  public Restaurant updateRestaurantStatus(Long id) throws Exception;

}

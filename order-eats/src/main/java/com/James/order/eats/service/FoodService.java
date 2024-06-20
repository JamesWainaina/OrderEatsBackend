package com.James.order.eats.service;

import com.James.order.eats.model.Food;
import com.James.order.eats.model.Category;
import com.James.order.eats.model.Restaurant;
import com.James.order.eats.requests.CreateFoodRequest;


import java.util.List;


public interface FoodService {
    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant) throws Exception;

    void deleteFood(Long foodId) throws Exception;

    public List<Food> getFoodByRestaurant(Long restaurantId,
                                          boolean isVegeterian,
                                          boolean isNonVeg,
                                          boolean isSeasonal,
                                          String foodCategory) throws Exception;

    public List<Food> searchFood(String keyword) throws Exception;

    public Food findFoodById(Long foodId) throws Exception;

    public Food updateFoodAvailability(Long foodId) throws Exception;
}

package com.James.order.eats.service;

import com.James.order.eats.model.Food;
import com.James.order.eats.model.Category;
import com.James.order.eats.model.Restaurant;
import com.James.order.eats.repository.FoodRepository;
import com.James.order.eats.requests.CreateFoodRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepository foodRepository;

    @Override
    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant) throws Exception {
        Food food = new Food();
        food.setFoodCategory(category);
        food.setRestaurant(restaurant);
        food.setImages(req.getImages());
        food.setDescription(req.getDescription());
        food.setName(req.getName());
        food.setSeasonal(req.isSeasonal());
        food.setPrice(req.getPrice());
        food.setSeasonal(req.isSeasonal());
        food.setVegeterian(req.isVegetarian());

        Food savedFood= foodRepository.save(food);
        restaurant.getFood().add(savedFood);

        return savedFood;
    }

    @Override
    public void deleteFood(Long foodId) throws Exception {

        Food food = findFoodById(foodId);
        food.setRestaurant(null);
        foodRepository.save(food);


    }

    @Override
    public List<Food> getFoodByRestaurant(Long restaurantId,
                                          boolean isVegeterian,
                                          boolean isNonVeg,
                                          boolean isSeasonal,
                                          String foodCategory) throws Exception {

        List<Food> foods = foodRepository.findByRestaurantId(restaurantId);

        if(isVegeterian) {
            foods = filterByVegeterian(foods, isVegeterian);
        }

        if(isNonVeg){
            foods = filterByNonVeg(foods,isNonVeg);
        }

        if(isSeasonal){
            foods = filterBySeasonal(foods,isSeasonal);
        }

        if(foodCategory != null && !foodCategory.isEmpty()){
            foods = filterByCategory(foods, foodCategory);
        }
        return foods;

    }

    private List<Food> filterByCategory(List<Food> foods, String foodCategory) {
        return foods.stream().filter(food -> {
            if (food.getFoodCategory() != null) {
                return food.getFoodCategory().getName().equals(foodCategory);
            }
            return false;
        }).collect(Collectors.toList());

    }

    private List<Food> filterBySeasonal(List<Food> foods, boolean isSeasonal) {
        return foods.stream().filter(food -> food.isSeasonal() == isSeasonal).collect(Collectors.toList());
    }

    private List<Food> filterByNonVeg(List<Food> foods, boolean isNonVeg) {
        return foods.stream().filter(food -> !food.isVegeterian()).collect(Collectors.toList());

    }

    private List<Food> filterByVegeterian(List<Food> foods, boolean isVegeterian) {
        return foods.stream().filter(food -> food.isVegeterian() == isVegeterian).collect(Collectors.toList());
    }

    @Override
    public List<Food> searchFood(String keyword) throws Exception {
        return foodRepository.searchFood(keyword);
    }

    @Override
    public Food findFoodById(Long foodId) throws Exception {
        Optional<Food> optionalFood = foodRepository.findById(foodId);
        if(optionalFood.isEmpty()){
            throw new Exception("Food not found");
        }
        return optionalFood.get();
    }

    @Override
    public Food updateFoodAvailability(Long foodId) throws Exception {
        Food food = findFoodById(foodId);
        food.setAvailable(food.isAvailable());
        return foodRepository.save(food);
    }
}

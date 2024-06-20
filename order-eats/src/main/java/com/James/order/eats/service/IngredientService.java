package com.James.order.eats.service;

import com.James.order.eats.model.IngredientCategory;
import com.James.order.eats.model.IngredientItems;

import java.util.List;

public interface IngredientService {
    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception;

    public IngredientCategory findIngredientCategoryById(Long id) throws Exception;

    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception;

    public List<IngredientItems> findRestaurantIngredients(Long id) throws Exception;

    public IngredientItems createIngredientItem(String ingredientName, Long categoryId, Long restaurantId) throws Exception;

    public IngredientItems updateStock(Long id) throws Exception;

}

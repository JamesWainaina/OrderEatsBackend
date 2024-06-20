package com.James.order.eats.service;

import com.James.order.eats.model.IngredientCategory;
import com.James.order.eats.model.IngredientItems;
import com.James.order.eats.model.Restaurant;
import com.James.order.eats.repository.IngredientCategoryRepository;
import com.James.order.eats.repository.IngredientItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {

    @Autowired
    private IngredientCategoryRepository ingredientCategoryRepository;

    @Autowired
    private IngredientItemRepository ingredientItemRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Override
    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);

        IngredientCategory ingredientCategory = new IngredientCategory();
        ingredientCategory.setRestaurant(restaurant);
        ingredientCategory.setName(name);

        return ingredientCategoryRepository.save(ingredientCategory);
    }

    @Override
    public IngredientCategory findIngredientCategoryById(Long id) throws Exception {
        Optional<IngredientCategory> opt = ingredientCategoryRepository.findById(id);

        if (opt.isEmpty()){
            throw new Exception("Ingredient Category not found");
        }
        return opt.get();
    }

    @Override
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception {

        restaurantService.findRestaurantById(id);
        return ingredientCategoryRepository.findByRestaurantId(id);
    }

    @Override
    public List<IngredientItems> findRestaurantIngredients(Long id) throws Exception {
        return ingredientItemRepository.findByRestaurantId(id);
    }

    @Override
    public IngredientItems createIngredientItem(String ingredientName, Long categoryId, Long restaurantId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientCategory ingredientCategory = findIngredientCategoryById(categoryId);

        IngredientItems ingredientItems = new IngredientItems();
        ingredientItems.setName(ingredientName);
        ingredientItems.setRestaurant(restaurant);
        ingredientItems.setCategory(ingredientCategory);
        IngredientItems savedIngredient= ingredientItemRepository.save(ingredientItems);
        ingredientCategory.getIngredientItems().add(savedIngredient);
        return savedIngredient;
    }

    @Override
    public IngredientItems updateStock(Long id) throws Exception {
        Optional<IngredientItems> opt = ingredientItemRepository.findById(id);
        if(opt.isEmpty()){
            throw new Exception("Ingredient Item not found");
        }
        IngredientItems ingredientItems = opt.get();

        ingredientItems.setInStock(!ingredientItems.isInStock());

        return ingredientItemRepository.save(ingredientItems);
    }
}

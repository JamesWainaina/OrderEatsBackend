package com.James.order.eats.service;

import com.James.order.eats.model.Category;

import java.util.List;


public interface CategoryService {
    public Category createCategory(String name, Long userId) throws Exception;


    List<Category> findCategoryByRestaurantId(Long restaurantId) throws Exception;

    public Category findCategoryById(Long id) throws Exception;
}

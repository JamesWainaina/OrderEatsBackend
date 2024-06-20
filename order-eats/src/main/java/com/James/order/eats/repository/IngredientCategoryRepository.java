package com.James.order.eats.repository;

import com.James.order.eats.model.IngredientCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientCategoryRepository extends JpaRepository<IngredientCategory, Long>{

    List<IngredientCategory> findByRestaurantId(Long id);
}

package com.James.order.eats.repository;

import com.James.order.eats.model.IngredientItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientItemRepository extends JpaRepository<IngredientItems, Long> {

    List<IngredientItems> findByRestaurantId(Long id);
}
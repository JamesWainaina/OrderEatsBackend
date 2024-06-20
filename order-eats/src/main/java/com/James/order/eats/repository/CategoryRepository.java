package com.James.order.eats.repository;

import com.James.order.eats.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long>{
    public List<Category> findByRestaurantId(Long id);
}

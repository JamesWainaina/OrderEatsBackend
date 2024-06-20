package com.James.order.eats.repository;

import com.James.order.eats.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantRespository extends JpaRepository<Restaurant, Long>{

    @Query("SELECT r FROM Restaurant r WHERE lower(r.name) LIKE lower(concat('$',:query ,'%')) OR lower(r.cuisineType) LIKE lower(concat('%', :query,'$'))")
    List<Restaurant>findbyRestaurantName(String query);

    Restaurant findByOwnerId(Long userId);

}

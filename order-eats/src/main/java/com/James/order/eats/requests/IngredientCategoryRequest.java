package com.James.order.eats.requests;

import lombok.Data;

@Data
public class IngredientCategoryRequest {

    private Long categoryId;
    private String name;
    private Long restaurantId;
}

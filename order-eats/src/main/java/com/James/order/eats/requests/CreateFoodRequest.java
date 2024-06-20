package com.James.order.eats.requests;

import com.James.order.eats.model.IngredientItems;
import jdk.jfr.Category;
import lombok.Data;

import java.util.List;

@Data
public class CreateFoodRequest {
    private String name;
    private String description;
    private Long price;
    private Category category;
    private List<String> images;

    private  Long restaurantId;

    private boolean vegetarian;
    private boolean seasonal;
    private  List <IngredientItems> ingredients;
}

package com.James.order.eats.controller;

import com.James.order.eats.model.IngredientCategory;
import com.James.order.eats.model.IngredientItems;
import com.James.order.eats.requests.IngredientCategoryRequest;
import com.James.order.eats.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RequestMapping("/api/admin/ingredients")
@RestController
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @PostMapping("category")
    public ResponseEntity<IngredientCategory> createIngredientCategory(
            @RequestBody IngredientCategoryRequest req) throws Exception {

        IngredientCategory ingredientCategory = ingredientService.createIngredientCategory(req.getName(), req.getRestaurantId());

        return new ResponseEntity<>(ingredientCategory, HttpStatus.CREATED);
    }


    @PostMapping
    public ResponseEntity<IngredientItems> createIngredientItem(
            @RequestBody IngredientCategoryRequest req)
            throws Exception {

        IngredientItems items = ingredientService.createIngredientItem(req.getName(), req.getCategoryId(), req.getRestaurantId());

        return new ResponseEntity<>(items, HttpStatus.CREATED);
    }

    @PutMapping("{id}/stock")
    public ResponseEntity<IngredientItems> UpdateIngredientItem(
            @PathVariable Long id)
            throws Exception {

        IngredientItems items = ingredientService.updateStock(id);

        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("Restaurant/{id}")
    public ResponseEntity<List<IngredientItems>> getRestaurantIngredients(
            @PathVariable Long id)
            throws Exception {

        List<IngredientItems> items = ingredientService.findRestaurantIngredients(id);

        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("Restaurant/{id}/category")
    public ResponseEntity<List<IngredientCategory>> getRestaurantIngredientsCategory(
            @PathVariable Long id)
            throws Exception {

        List<IngredientCategory> items = ingredientService.findIngredientCategoryByRestaurantId(id);

        return new ResponseEntity<>(items, HttpStatus.OK);
    }

}

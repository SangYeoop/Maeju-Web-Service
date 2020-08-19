package com.foodwebservice.food.dto;

import com.foodwebservice.Ingredient.IngredientType;
import lombok.Data;

@Data
public class IngredientDto {
    private IngredientType ingredientType;
    private String ingredientName;
    private String amount;
}

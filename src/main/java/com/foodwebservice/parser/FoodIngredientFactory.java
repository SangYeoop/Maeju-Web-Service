package com.foodwebservice.parser;

import com.foodwebservice.Ingredient.Ingredient;
import com.foodwebservice.food.Food;
import com.foodwebservice.food_ingredient.FoodIngredient;

import java.util.List;
import java.util.stream.Collectors;

public class FoodIngredientFactory {

    public List<FoodIngredient> getFoodIngredient(Food food, List<Tuple<Ingredient, String>> ingredients) {
        return ingredients.stream().map((tuple) -> {
            FoodIngredient foodIngredient = new FoodIngredient();
            foodIngredient.setFood(food);
            foodIngredient.setIngredient(tuple.getFirst());
            foodIngredient.setAmount(tuple.getSecond());

            return foodIngredient;
        }).collect(Collectors.toList());
    }
}

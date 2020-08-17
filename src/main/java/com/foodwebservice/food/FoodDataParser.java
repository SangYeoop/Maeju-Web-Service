package com.foodwebservice.food;

import com.foodwebservice.food.condition.Difficulty;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class FoodDataParser {

    public Food getFoodAsString(String foodData){
        Food food = new Food();

        String[] datas = foodData.split(",");

        food.setName(datas[0]);
        food.setAmount(datas[1]);
        food.setTime(datas[2]);
        food.setDifficulty(Difficulty.getInstanceAsString(datas[3]));
        food.setIngredient(datas[4]);
        return food;
    }


    public List<String> getIngredientListAsString(String ingredients){
        return List.of(ingredients.split("/"));
    }
}

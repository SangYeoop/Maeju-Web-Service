package com.foodwebservice.food_ingredient;

import com.foodwebservice.Ingredient.Ingredient;
import com.foodwebservice.food.Food;
import com.foodwebservice.food.dto.IngredientDto;
import com.foodwebservice.parser.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FoodIngredientService {

    private final FoodIngredientRepository foodIngredientRepository;

    public List<FoodIngredient> findFoodIngredientByFood(Food food){
        return foodIngredientRepository.findAllWithIngredientByFood(food);
    }

    public List<IngredientDto> findAllIngredientsAndAmountByFood(Food food){
        return findFoodIngredientByFood(food).stream().map(value -> {
            IngredientDto ingredientDto = new IngredientDto();
            ingredientDto.setAmount(value.getAmount());
            ingredientDto.setIngredientName(value.getIngredient().getName());
            ingredientDto.setIngredientType(value.getIngredient().getIngredientType());
            return ingredientDto;
        }).collect(Collectors.toList());
    }

}

package com.foodwebservice.food;

import com.foodwebservice.Ingredient.IngredientType;
import com.foodwebservice.diet.DietType;
import com.foodwebservice.food.condition.Kind;
import com.foodwebservice.food.condition.Situation;
import com.foodwebservice.food.condition.Way;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FoodRepositoryExtension {
    Page<Food> findByKeyword(String keyword, Pageable pageable);

    List<Food> findByDietTypeAndKinds(List<DietType> dietType, List<Kind> kinds, List<IngredientType> ingredients);

    List<Food> findByDietTypeAndWays(List<DietType> dietTypes, List<Way> ways, List<IngredientType> ingredients);

    List<Food> findByDietTypeAndSituations(List<DietType> dietTypes, List<Situation> situations, List<IngredientType> ingredients);
}

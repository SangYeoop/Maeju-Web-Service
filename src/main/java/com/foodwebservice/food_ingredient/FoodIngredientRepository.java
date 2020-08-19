package com.foodwebservice.food_ingredient;

import com.foodwebservice.food.Food;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodIngredientRepository extends JpaRepository<FoodIngredient, Long> {

    @EntityGraph(attributePaths = {"ingredient"})
    List<FoodIngredient> findAllWithIngredientByFood(Food food);
}

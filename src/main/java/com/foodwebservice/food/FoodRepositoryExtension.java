package com.foodwebservice.food;

import com.foodwebservice.diet.DietType;
import com.foodwebservice.food.condition.Kind;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FoodRepositoryExtension {
    Page<Food> findByKeyword(String keyword, Pageable pageable);

    List<Food> findByDietTypeAndKinds(DietType dietType, List<Kind> kinds);
}

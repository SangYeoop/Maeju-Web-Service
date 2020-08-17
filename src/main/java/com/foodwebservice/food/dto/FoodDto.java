package com.foodwebservice.food.dto;

import com.foodwebservice.food.condition.Difficulty;
import com.foodwebservice.food.condition.Kind;
import com.foodwebservice.food.condition.Situation;
import com.foodwebservice.food.condition.Way;
import lombok.Data;

@Data
public class FoodDto {
    private Long id;

    private String name;

    private String ingredient;

    private String recipe;

    private String imagePath;

    private String amount;

    private String time;

    private Kind kind;

    private Situation situation;

    private Way way;

    private Difficulty difficulty;

}

package com.foodwebservice.food.type;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FoodTypeForm {
    List<String> foodsName = new ArrayList<>();
    List<String> selected = new ArrayList<>();

    int index;
}

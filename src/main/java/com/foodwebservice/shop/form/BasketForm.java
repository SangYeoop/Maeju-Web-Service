package com.foodwebservice.shop.form;

import com.foodwebservice.food.Food;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor
@Data
public class BasketForm {
    List<Food> selectedFoods = new ArrayList<>();
}

package com.foodwebservice.food.type;

import com.foodwebservice.food.condition.Kind;
import com.foodwebservice.food.condition.Situation;
import com.foodwebservice.food.condition.Way;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class FoodTypeQuestion {
    String foodName;
    List<Kind> kinds = new ArrayList<>();
    List<Way> ways = new ArrayList<>();
    List<Situation> situations = new ArrayList<>();

}

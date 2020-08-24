package com.foodwebservice.parser;

import com.foodwebservice.diet.DietType;
import com.foodwebservice.food.Food;
import com.foodwebservice.food.condition.Difficulty;
import com.foodwebservice.food.condition.Kind;
import com.foodwebservice.food.condition.Situation;
import com.foodwebservice.food.condition.Way;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class FoodDataParser {

    public Food getFoodAsString(String foodData){
        Food food = new Food();

        String[] datas = foodData.split(",");

        food.setName(datas[0]);
        food.setAmount(datas[1]);
        food.setTime(datas[2]);
        food.setDifficulty(Difficulty.getInstanceAsString(datas[3]));
        food.setImagePath(datas[4]);
        food.setKind(Kind.getInstanceAsString(datas[5]));
        food.setWay(Way.getInstanceAsString(datas[6]));
        food.setSituation(Situation.getInstanceAsString(datas[7]));
        food.setIngredient(datas[8]);
        food.setRecipe(datas[9]);
        for(String value : datas[10].split("%")){
            food.getDietTypeList().add(DietType.getInstanceAsString(value));
        }
        return food;
    }

}

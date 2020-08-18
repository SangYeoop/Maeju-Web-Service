package com.foodwebservice.parser;

import com.foodwebservice.Ingredient.Ingredient;
import com.foodwebservice.Ingredient.IngredientType;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Component
public class IngredientDataParser {

    public List<Tuple<Ingredient, String>> getIngredientsAsString(String value){
        return List.of(value.split("&")).stream().map((ingredientValue) -> {
            Ingredient ingredient = new Ingredient();
            String[] values = ingredientValue.split("%");
            ingredient.setName(values[0]);
            ingredient.setIngredientType(IngredientType.getInstanceAsString(values[1]));
            return Tuple.of(ingredient, values[2]);
        }).collect(Collectors.toList());
    }
}

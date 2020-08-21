package com.foodwebservice.infra.runner;

import com.foodwebservice.Ingredient.Ingredient;
import com.foodwebservice.Ingredient.IngredientRepository;
import com.foodwebservice.food.Food;
import com.foodwebservice.food.FoodRepository;
import com.foodwebservice.food_ingredient.FoodIngredient;
import com.foodwebservice.food_ingredient.FoodIngredientRepository;
import com.foodwebservice.parser.FoodDataParser;
import com.foodwebservice.parser.FoodIngredientFactory;
import com.foodwebservice.parser.IngredientDataParser;
import com.foodwebservice.parser.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Profile("!test")
@Component
public class DataCommandLineRunner implements CommandLineRunner {
    private final FoodDataParser foodDataParser;
    private final IngredientDataParser ingredientDataParser;

    private final FoodRepository foodRepository;
    private final IngredientRepository ingredientRepository;
    private final FoodIngredientRepository foodIngredientRepository;

    @Override
    public void run(String... args) throws Exception {
        initFoodData();
    }

    private void initFoodData()  throws Exception {
        ClassPathResource resource = new ClassPathResource("csv/food-data.csv");
        String data = new String(resource.getInputStream().readAllBytes());
        List<String> datas = List.of(data.split("\n"));


        datas.forEach((d) -> {
            Food food = foodDataParser.getFoodAsString(d);

            String ingredients = d.split(",")[8];
            List<Tuple<Ingredient,String>> ingredientsList = ingredientDataParser.getIngredientsAsString(ingredients);

            food = foodRepository.save(food);

            ingredientsList = ingredientsList.stream().map((tuple) -> {
                Ingredient ingredient = ingredientRepository.findByName(tuple.getFirst().getName()).orElse(null);

                if(ingredient == null)
                    ingredient = ingredientRepository.save(tuple.getFirst());

                return Tuple.of(ingredient, tuple.getSecond());
            }).collect(Collectors.toList());

            FoodIngredientFactory foodIngredientFactory = new FoodIngredientFactory();
            List<FoodIngredient> ingredients1 = foodIngredientFactory.getFoodIngredient(food, ingredientsList);
            foodIngredientRepository.saveAll(ingredients1);
        });
    }
}

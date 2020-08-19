package com.foodwebservice.food;

import com.foodwebservice.Ingredient.Ingredient;
import com.foodwebservice.Ingredient.IngredientRepository;
import com.foodwebservice.food_ingredient.FoodIngredient;
import com.foodwebservice.food_ingredient.FoodIngredientRepository;
import com.foodwebservice.parser.FoodDataParser;
import com.foodwebservice.parser.FoodIngredientFactory;
import com.foodwebservice.parser.IngredientDataParser;
import com.foodwebservice.parser.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodDataParser foodDataParser;
    private final IngredientDataParser ingredientDataParser;

    private final FoodRepository foodRepository;
    private final IngredientRepository ingredientRepository;
    private final FoodIngredientRepository foodIngredientRepository;


    public List<Food> findByKeyword(String foodName, Pageable pageable) {
        return foodRepository.findByKeyword(foodName, pageable).getContent();
    }

    public Food findById(Long id){
        return foodRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(id + "에 해당하는 음식이 없습니다."));
    }


    @PostConstruct
    public void initFoodData() throws IOException{
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

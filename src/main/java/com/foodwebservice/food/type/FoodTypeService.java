package com.foodwebservice.food.type;

import com.foodwebservice.food.condition.Kind;
import com.foodwebservice.food.condition.Situation;
import com.foodwebservice.food.condition.Way;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodTypeService {

    private List<FoodTypeQuestion> questions = new ArrayList<>();

    public List<String> getFoodNames() {
        return questions.stream().map(FoodTypeQuestion::getFoodName)
                .collect(Collectors.toList());
    }

    @PostConstruct
    public void initFoodTypeData() throws IOException {
        questions = getFoodString().stream().map((str) -> {
            FoodTypeQuestion foodTypeQuestion = new FoodTypeQuestion();
            String[] values = str.split(",");
            foodTypeQuestion.setFoodName(values[1]);
            for(String kinds : values[2].split("%")){
                if(!kinds.equals("NONE"))
                    foodTypeQuestion.getKinds().add(Kind.getInstanceAsString(kinds));
            }

            for(String ways : values[3].split("%")){
                if(!ways.equals("NONE"))
                    foodTypeQuestion.getWays().add(Way.getInstanceAsString(ways));
            }

            for(String situations : values[4].split("%")) {
                if(!situations.equals("NONE"))
                    foodTypeQuestion.getSituations().add(Situation.getInstanceAsString(situations));
            }

            return foodTypeQuestion;
        }).collect(Collectors.toList());
    }

    private List<String> getFoodString() throws IOException {
        ClassPathResource resource = new ClassPathResource("text/food-type.txt");
        String data = new String(resource.getInputStream().readAllBytes());
        return List.of(data.split("\n"));
    }
}

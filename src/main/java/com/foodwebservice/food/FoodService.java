package com.foodwebservice.food;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;

    @PostConstruct
    public void initFoodData() throws IOException{
        FoodDataParser foodDataParser = new FoodDataParser();

        if(foodRepository.count() == 0){
            Resource resource = new ClassPathResource("csv/food_data.csv");

            List<String> list = List.of(Arrays.toString(resource.getInputStream().readAllBytes()).split("\n"));
            List<Food> foods = list.stream().map(foodDataParser::getFoodAsString).collect(Collectors.toList());
            foodRepository.saveAll(foods);
//            List<Food> foodList = Files.readAllLines(resource.getFile().toPath(), StandardCharsets.UTF_8).stream()
//                    .map(foodDataParser::getFoodAsString).collect(Collectors.toList());
//
//            foodRepository.saveAll(foodList);
        }
    }

    public List<Food> findByKeyword(String foodName, Pageable pageable) {
        return foodRepository.findByKeyword(foodName, pageable).getContent();
    }
}

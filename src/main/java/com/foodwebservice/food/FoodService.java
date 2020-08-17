package com.foodwebservice.food;

import com.foodwebservice.food.dto.FoodDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodDataParser foodDataParser;
    private final FoodRepository foodRepository;

    public List<Food> findByKeyword(String foodName, Pageable pageable) {
        return foodRepository.findByKeyword(foodName, pageable).getContent();
    }

    public Food findById(Long id){
        return foodRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(id + "에 해당하는 음식이 없습니다."));
    }


    @PostConstruct
    public void initFoodData() throws IOException{
        if(foodRepository.count() == 0){
            ClassPathResource resource = new ClassPathResource("csv/food_data.csv");
            String data = new String(resource.getInputStream().readAllBytes());
            List<String> datas = List.of(data.split("\n"));
            List<Food> foodList = datas.stream().map(foodDataParser::getFoodAsString).collect(Collectors.toList());
            foodRepository.saveAll(foodList);
        }
    }
}

package com.foodwebservice.food;

import com.foodwebservice.Ingredient.Ingredient;
import com.foodwebservice.Ingredient.IngredientRepository;
import com.foodwebservice.account.Account;
import com.foodwebservice.diet.Diet;
import com.foodwebservice.diet.DietRepository;
import com.foodwebservice.food.condition.Kind;
import com.foodwebservice.food_ingredient.FoodIngredient;
import com.foodwebservice.food_ingredient.FoodIngredientRepository;
import com.foodwebservice.parser.FoodDataParser;
import com.foodwebservice.parser.FoodIngredientFactory;
import com.foodwebservice.parser.IngredientDataParser;
import com.foodwebservice.parser.Tuple;
import com.foodwebservice.preference.KindPreference;
import com.foodwebservice.preference.KindPreferenceRepository;
import com.foodwebservice.preference.QKindPreference;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
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
    private final FoodRepository foodRepository;
    private final KindPreferenceRepository kindPreferenceRepository;
    private final DietRepository dietRepository;

    public Page<Food> findByKeyword(String foodName, Pageable pageable) {
        return foodRepository.findByKeyword(foodName, pageable);
    }

    public Food findById(Long id){
        return foodRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(id + "에 해당하는 음식이 없습니다."));
    }

    public List<Food> getFoodsByKindAndAccount(Account account){
        KindPreference kindPreference = kindPreferenceRepository.findByAccount(account).orElseGet(KindPreference::new);
        Diet diet = dietRepository.findByAccount(account).orElse(null);

        List<Kind> preferenceKinds = kindPreference.getPreferenceKinds();

        return null;
    }

}

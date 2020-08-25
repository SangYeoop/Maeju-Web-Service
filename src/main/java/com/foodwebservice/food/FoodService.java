package com.foodwebservice.food;

import com.foodwebservice.Ingredient.Ingredient;
import com.foodwebservice.Ingredient.IngredientRepository;
import com.foodwebservice.Ingredient.IngredientType;
import com.foodwebservice.account.Account;
import com.foodwebservice.diet.Diet;
import com.foodwebservice.diet.DietRepository;
import com.foodwebservice.diet.DietType;
import com.foodwebservice.food.condition.Kind;
import com.foodwebservice.food.condition.Situation;
import com.foodwebservice.food.condition.Way;
import com.foodwebservice.food_ingredient.FoodIngredient;
import com.foodwebservice.food_ingredient.FoodIngredientRepository;
import com.foodwebservice.parser.FoodDataParser;
import com.foodwebservice.parser.FoodIngredientFactory;
import com.foodwebservice.parser.IngredientDataParser;
import com.foodwebservice.parser.Tuple;
import com.foodwebservice.preference.*;
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
    private final IngredientPreferenceRepository ingredientPreferenceRepository;
    private final SituationPreferenceRepository situationPreferenceRepository;
    private final WayPreferenceRepository wayPreferenceRepository;
    private final DietRepository dietRepository;

    public Page<Food> findByKeyword(String foodName, Pageable pageable) {
        return foodRepository.findByKeyword(foodName, pageable);
    }

    public Food findById(Long id){
        return foodRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(id + "에 해당하는 음식이 없습니다."));
    }

    public List<Food> getFoodsByKindAndAccount(Account account){
        KindPreference kindPreference = kindPreferenceRepository.findByAccount(account).orElseGet(() -> {
            KindPreference newPreference = new KindPreference();
            newPreference.setAccount(account);
            return newPreference;
        });

        IngredientPreference ingredientPreference = getIngredientPreference(account);

        Diet diet = dietRepository.findByAccount(account).orElse(null);

        List<DietType> dietTypes = getDietTypes(diet);
        List<IngredientType> ingredients = ingredientPreference.getPreferenceIngredientType();
        List<Kind> preferenceKinds = kindPreference.getPreferenceKinds();

        return foodRepository.findByDietTypeAndKinds(dietTypes, preferenceKinds, ingredients);
    }

    public List<Food> getFoodsByWayAndAccount(Account account){
        WayPreference wayPreference = wayPreferenceRepository.findByAccount(account).orElseGet(() -> {
            WayPreference newPreference = new WayPreference();
            newPreference.setAccount(account);
            return newPreference;
        });

        IngredientPreference ingredientPreference = getIngredientPreference(account);
        Diet diet = dietRepository.findByAccount(account).orElse(null);

        List<DietType> dietTypes = getDietTypes(diet);
        List<Way> preferenceWays = wayPreference.getPreferenceWays();
        List<IngredientType> ingredients = ingredientPreference.getPreferenceIngredientType();

        return foodRepository.findByDietTypeAndWays(dietTypes, preferenceWays, ingredients);
    }

    public List<Food> getFoodsBySituationAndAccount(Account account){
        SituationPreference situationPreference = situationPreferenceRepository.findByAccount(account).orElseGet(() -> {
            SituationPreference newPreference = new SituationPreference();
            newPreference.setAccount(account);
            return newPreference;
        });

        IngredientPreference ingredientPreference = getIngredientPreference(account);
        Diet diet = dietRepository.findByAccount(account).orElse(null);

        List<DietType> dietTypes = getDietTypes(diet);
        List<Situation> preferenceSituations = situationPreference.getPreferenceSituations();
        List<IngredientType> ingredients = ingredientPreference.getPreferenceIngredientType();

        return foodRepository.findByDietTypeAndSituations(dietTypes, preferenceSituations, ingredients);
    }



    private List<DietType> getDietTypes(Diet diet) {
        List<DietType> dietTypes;
        if(diet != null)
            dietTypes = List.of(diet.getDietType());
        else
            dietTypes = List.of(DietType.values());
        return dietTypes;
    }

    private IngredientPreference getIngredientPreference(Account account) {
        return ingredientPreferenceRepository.findByAccount(account).orElseGet(() -> {
            IngredientPreference newPreference = new IngredientPreference();
            newPreference.setAccount(account);
            return newPreference;
        });
    }


}

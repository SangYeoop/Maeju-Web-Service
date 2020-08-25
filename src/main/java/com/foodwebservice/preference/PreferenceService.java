package com.foodwebservice.preference;

import com.foodwebservice.account.Account;
import com.foodwebservice.food.Food;
import com.foodwebservice.food.type.FoodTypeQuestion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PreferenceService {

    private final KindPreferenceRepository kindPreferenceRepository;
    private final SituationPreferenceRepository situationPreferenceRepository;
    private final WayPreferenceRepository wayPreferenceRepository;
    private final IngredientPreferenceRepository ingredientPreferenceRepository;

    @Transactional
    public void setPreferenceByFood(Account account, Food food){
        KindPreference kindPreference = kindPreferenceRepository.findByAccount(account).orElseGet(() -> {
            KindPreference newPreference = new KindPreference();
            newPreference.setAccount(account);
            return newPreference;
        }).count(food.getKind());

        SituationPreference situationPreference = situationPreferenceRepository.findByAccount(account).orElseGet(() -> {
            SituationPreference newPreference = new SituationPreference();
            newPreference.setAccount(account);
            return newPreference;
        }).count(food.getSituation());
        WayPreference wayPreference = wayPreferenceRepository.findByAccount(account).orElseGet(() -> {
            WayPreference newPreference = new WayPreference();
            newPreference.setAccount(account);
            return newPreference;
        }).count(food.getWay());

        account.setExistFoodType(true);
        kindPreferenceRepository.save(kindPreference);
        situationPreferenceRepository.save(situationPreference);
        wayPreferenceRepository.save(wayPreference);
    }

    @Transactional
    public void setPreferenceCount(Account account, List<FoodTypeQuestion> foodTypeQuestions, List<String> selected) {
        KindPreference kindPreference = getKindPreference(account);
        SituationPreference situationPreference = getSituationPreference(account);
        WayPreference wayPreference = getWayPreference(account);
        IngredientPreference ingredientPreference = getIngredientPreference(account);

        selected.forEach((value) -> {
            foodTypeQuestions.forEach(foodTypeQuestion -> {
                if(foodTypeQuestion.getFoodName().equals(value)){
                    foodTypeQuestion.getKinds().forEach(kindPreference::count);
                    foodTypeQuestion.getWays().forEach(wayPreference::count);
                    foodTypeQuestion.getSituations().forEach(situationPreference::count);
                    foodTypeQuestion.getIngredients().forEach(ingredientPreference::count);
                }
            });
        });

        ingredientPreferenceRepository.save(ingredientPreference);
        kindPreferenceRepository.save(kindPreference);
        situationPreferenceRepository.save(situationPreference);
        wayPreferenceRepository.save(wayPreference);
    }

    private WayPreference getWayPreference(Account account) {
        return wayPreferenceRepository.findByAccount(account).orElseGet(() -> {
            WayPreference newPreference = new WayPreference();
            newPreference.setAccount(account);
            return newPreference;
        });
    }

    private SituationPreference getSituationPreference(Account account) {
        return situationPreferenceRepository.findByAccount(account).orElseGet(() -> {
            SituationPreference newPreference = new SituationPreference();
            newPreference.setAccount(account);
            return newPreference;
        });
    }

    private KindPreference getKindPreference(Account account) {
        return kindPreferenceRepository.findByAccount(account).orElseGet(() -> {
            KindPreference newPreference = new KindPreference();
            newPreference.setAccount(account);
            return newPreference;
        });
    }

    private IngredientPreference getIngredientPreference(Account account){
        return ingredientPreferenceRepository.findByAccount(account).orElseGet(() -> {
            IngredientPreference newPreference = new IngredientPreference();
            newPreference.setAccount(account);
            return newPreference;
        });
    }

}

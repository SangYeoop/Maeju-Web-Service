package com.foodwebservice.preference;

import com.foodwebservice.Ingredient.Ingredient;
import com.foodwebservice.Ingredient.IngredientType;
import com.foodwebservice.account.Account;
import com.foodwebservice.food.condition.Kind;
import com.foodwebservice.parser.Tuple;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity @Getter @Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor @NoArgsConstructor
public class IngredientPreference {

    @Id @GeneratedValue
    private Long id;

    @OneToOne
    private Account account;

    private int countByPork;

    private int countByBeef;

    private int countByChicken;

    private int countByMeat;

    private int countByVegetable;

    private int countBySeaFood;

    private int countByDairy;

    private int countByProcessed;

    private int countByRice;

    private int countByFlour;

    private int countByDriedFish;

    private int countByMushroom;

    private int countByFruit;

    private int countByNuts;

    private int countByGrain;

    private int countByEtc;

    public List<IngredientType> getPreferenceIngredientType() {
        List<Tuple<IngredientType, Integer>> ingredientTypes = getTuplesIngredientTypeCount();
        ingredientTypes.sort((tuple1, tuple2) -> tuple2.getSecond().compareTo(tuple1.getSecond()));

        return ingredientTypes.subList(0, 1).stream().map(Tuple::getFirst).collect(Collectors.toList());
    }

    private List<Tuple<IngredientType, Integer>> getTuplesIngredientTypeCount() {
        List<Tuple<IngredientType, Integer>> ingredientTypes = new ArrayList<>();

        ingredientTypes.add(Tuple.of(IngredientType.PORK, countByPork));
        ingredientTypes.add(Tuple.of(IngredientType.BEEF, countByBeef));
        ingredientTypes.add(Tuple.of(IngredientType.CHICKEN, countByChicken));
        ingredientTypes.add(Tuple.of(IngredientType.MEAT, countByMeat));
        ingredientTypes.add(Tuple.of(IngredientType.VEGETABLE, countByVegetable));
        ingredientTypes.add(Tuple.of(IngredientType.SEAFOOD, countBySeaFood));
        ingredientTypes.add(Tuple.of(IngredientType.DAIRY, countByDairy));
        ingredientTypes.add(Tuple.of(IngredientType.PROCESSED, countByProcessed));
        ingredientTypes.add(Tuple.of(IngredientType.RICE, countByRice));
        ingredientTypes.add(Tuple.of(IngredientType.FLOUR, countByFlour));
        ingredientTypes.add(Tuple.of(IngredientType.DRIED_FISH, countByDriedFish));
        ingredientTypes.add(Tuple.of(IngredientType.MUSHROOM, countByMushroom));
        ingredientTypes.add(Tuple.of(IngredientType.FRUIT, countByFruit));
        ingredientTypes.add(Tuple.of(IngredientType.NUTS, countByNuts));
        ingredientTypes.add(Tuple.of(IngredientType.GRAIN, countByGrain));
        return ingredientTypes;
    }

    public IngredientPreference count(IngredientType ingredientType){
        if(ingredientType.equals(IngredientType.PORK))
            countByPork++;
        else if(ingredientType.equals(IngredientType.BEEF))
            countByBeef++;
        else if(ingredientType.equals(IngredientType.CHICKEN))
            countByChicken++;
        else if(ingredientType.equals(IngredientType.VEGETABLE))
            countByVegetable++;
        else if(ingredientType.equals(IngredientType.SEAFOOD))
            countBySeaFood++;
        else if(ingredientType.equals(IngredientType.DAIRY))
            countByDairy++;
        else if(ingredientType.equals(IngredientType.PROCESSED))
            countByProcessed++;
        else if(ingredientType.equals(IngredientType.RICE))
            countByRice++;
        else if(ingredientType.equals(IngredientType.FLOUR))
            countByFlour++;
        else if(ingredientType.equals(IngredientType.DRIED_FISH))
            countByDriedFish++;
        else if(ingredientType.equals(IngredientType.MUSHROOM))
            countByMushroom++;
        else if(ingredientType.equals(IngredientType.FRUIT))
            countByFruit++;
        else if(ingredientType.equals(IngredientType.NUTS))
            countByNuts++;
        else if(ingredientType.equals(IngredientType.GRAIN))
            countByNuts++;
        else
            countByEtc++;
        return this;
    }
}

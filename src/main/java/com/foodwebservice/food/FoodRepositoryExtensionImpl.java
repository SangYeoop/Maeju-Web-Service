package com.foodwebservice.food;

import com.foodwebservice.Ingredient.IngredientType;
import com.foodwebservice.Ingredient.QIngredient;
import com.foodwebservice.diet.DietType;
import com.foodwebservice.food.condition.Kind;
import com.foodwebservice.food.condition.Situation;
import com.foodwebservice.food.condition.Way;
import com.foodwebservice.food_ingredient.QFoodIngredient;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class FoodRepositoryExtensionImpl extends QuerydslRepositorySupport implements FoodRepositoryExtension {

    public FoodRepositoryExtensionImpl() {
        super(Food.class);
    }

    @Override
    public Page<Food> findByKeyword(String keyword, Pageable pageable) {
        QFood food = QFood.food;
        JPQLQuery<Food> query = from(food)
                .where(food.name.containsIgnoreCase(keyword));

        JPQLQuery<Food> pageableQuery = getQuerydsl().applyPagination(pageable, query);
        QueryResults<Food> results = pageableQuery.fetchResults();
        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

    @Override
    public List<Food> findByDietTypeAndKinds(List<DietType> dietTypes, List<Kind> kinds, List<IngredientType> ingredients) {
        QFood food = QFood.food;
        QFoodIngredient foodIngredient = QFoodIngredient.foodIngredient;
        QIngredient ingredient = QIngredient.ingredient;

        JPQLQuery<Food> query = from(food)
                .leftJoin(food.foodIngredients, foodIngredient).fetchJoin()
                .leftJoin(foodIngredient.ingredient, ingredient).fetchJoin()
                .where(food.dietTypeList.any().in(dietTypes)
                        .and(food.kind.in(kinds))
                        .and(food.foodIngredients.any().ingredient.ingredientType.in(ingredients)))
                .distinct().limit(4);

        return query.fetch();
    }

    @Override
    public List<Food> findByDietTypeAndWays(List<DietType> dietTypes, List<Way> ways, List<IngredientType> ingredients) {
        QFood food = QFood.food;
        QFoodIngredient foodIngredient = QFoodIngredient.foodIngredient;
        QIngredient ingredient = QIngredient.ingredient;

        JPQLQuery<Food> query = from(food)
                .leftJoin(food.foodIngredients, foodIngredient).fetchJoin()
                .leftJoin(foodIngredient.ingredient, ingredient).fetchJoin()
                .where(food.dietTypeList.any().in(dietTypes)
                        .and(food.way.in(ways))
                        .and(food.foodIngredients.any().ingredient.ingredientType.in(ingredients)))
                .distinct().limit(4);

        return query.fetch();
    }

    @Override
    public List<Food> findByDietTypeAndSituations(List<DietType> dietTypes, List<Situation> situations, List<IngredientType> ingredients) {
        QFood food = QFood.food;
        QFoodIngredient foodIngredient = QFoodIngredient.foodIngredient;
        QIngredient ingredient = QIngredient.ingredient;

        JPQLQuery<Food> query = from(food)
                .leftJoin(food.foodIngredients, foodIngredient).fetchJoin()
                .leftJoin(foodIngredient.ingredient, ingredient).fetchJoin()
                .where(food.dietTypeList.any().in(dietTypes)
                        .and(food.situation.in(situations))
                        .and(food.foodIngredients.any().ingredient.ingredientType.in(ingredients)))
                .distinct().limit(4);

        return query.fetch();
    }
}

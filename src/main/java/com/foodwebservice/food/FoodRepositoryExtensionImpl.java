package com.foodwebservice.food;

import com.foodwebservice.diet.DietType;
import com.foodwebservice.food.condition.Kind;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.CollectionExpression;
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
    public List<Food> findByDietTypeAndKinds(DietType dietType, List<Kind> kinds) {
        QFood food = QFood.food;

        JPQLQuery<Food> query = from(food)
                .where(food.dietTypeList.contains(dietType).and(food.kind.in(kinds)));

        return query.fetch();
    }
}

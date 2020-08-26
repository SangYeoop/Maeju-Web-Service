package com.foodwebservice.shop;

import com.foodwebservice.account.Account;
import com.foodwebservice.food.Food;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop, Long> {

    Optional<Shop> findByAccountAndFood(Account account, Food food);

    List<Shop> findWithFoodAllByAccount(Account account);

    List<Shop> findWithFoodAndIngredientsAllByAccount(Account account);

    boolean existsByAccountAndFood(Account account, Food food);
}

package com.foodwebservice.shop;

import com.foodwebservice.account.Account;
import com.foodwebservice.food.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop, Long> {

    Optional<Shop> findByAccountAndFood(Account account, Food food);

    boolean existsByAccountAndFood(Account account, Food food);
}

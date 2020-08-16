package com.foodwebservice.food;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FoodRepositoryExtension {
    Page<Food> findByKeyword(String keyword, Pageable pageable);
}

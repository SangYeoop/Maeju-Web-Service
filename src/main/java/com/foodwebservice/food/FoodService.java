package com.foodwebservice.food;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;

}

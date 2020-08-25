package com.foodwebservice.main;

import com.foodwebservice.account.Account;
import com.foodwebservice.account.AccountService;
import com.foodwebservice.account.CurrentAccount;
import com.foodwebservice.food.Food;
import com.foodwebservice.food.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final AccountService accountService;
    private final FoodService foodService;

    @GetMapping("/")
    public String indexView(@CurrentAccount Account account, Model model) {
        if(account != null) {
            account = accountService.findById(account.getId());
            model.addAttribute(account);
            if(account.isExistDietType() && account.isExistFoodType()){
                List<Food> kindOfFood = foodService.getFoodsByKindAndAccount(account);
                List<Food> wayOfFood = foodService.getFoodsByWayAndAccount(account);
                List<Food> situationOfFood = foodService.getFoodsBySituationAndAccount(account);

                model.addAttribute("kindOfFoods", kindOfFood);
                model.addAttribute("wayOfFoods", wayOfFood);
                model.addAttribute("situationOfFoods", situationOfFood);
                return "main";
            }
        }
        return "index";
    }

    @GetMapping("/login")
    public String loginView() {
        return "login";
    }


    @GetMapping("/search")
    public String searchView(@PageableDefault(size = 6) Pageable pageable, String foodName, Model model) {
        Page<Food> foods = foodService.findByKeyword(foodName, pageable);
        model.addAttribute("foodPage", foods);
        model.addAttribute("foodName", foodName);
        return "search";
    }
}

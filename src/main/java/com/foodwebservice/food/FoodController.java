package com.foodwebservice.food;

import com.foodwebservice.account.Account;
import com.foodwebservice.account.AccountService;
import com.foodwebservice.account.CurrentAccount;
import com.foodwebservice.food.dto.FoodDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@Controller
public class FoodController {

    private final FoodDataParser foodDataParser;
    private final FoodService foodService;
    private final AccountService accountService;

    @GetMapping("/food/{foodId}")
    public String foodView(@PathVariable Long foodId, Model model) {
        Food food = foodService.findById(foodId);
        model.addAttribute("food", food);
        model.addAttribute("ingredients", foodDataParser.getIngredientListAsString(food.getIngredient()));
        return "food/food";
    }

    @PostMapping("/food/{foodId}/likefood")
    public String likeFoodRegister(@CurrentAccount Account account, @PathVariable Long foodId, RedirectAttributes redirectAttributes){
        Food food = foodService.findById(foodId);
        accountService.addLikeFood(account, food);
        redirectAttributes.addFlashAttribute("message", "선호음식이 성공적으로 등록되었습니다.");
        return "redirect:/food/" + food.getId();
    }

}

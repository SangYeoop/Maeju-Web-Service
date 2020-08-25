package com.foodwebservice.food;

import com.foodwebservice.account.Account;
import com.foodwebservice.account.AccountService;
import com.foodwebservice.account.CurrentAccount;
import com.foodwebservice.food_ingredient.FoodIngredientService;
import com.foodwebservice.preference.PreferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class FoodController {

    private final FoodService foodService;
    private final PreferenceService preferenceService;
    private final FoodIngredientService foodIngredientService;
    private final AccountService accountService;

    @GetMapping("/food/{foodId}")
    public String foodView(@CurrentAccount Account account, @PathVariable Long foodId, Model model) {
        Food food = foodService.findById(foodId);
        if(account != null) {
            account = accountService.findById(account.getId());
            model.addAttribute(account);
            preferenceService.setPreferenceByFood(account, food);
        }

        model.addAttribute("food", food);
        model.addAttribute("ingredients", foodIngredientService.findAllIngredientsAndAmountByFood(food));
        model.addAttribute("recipes", List.of(food.getRecipe().split("%")));
        return "food/food";
    }

    @PostMapping("/food/{foodId}/likefood")
    public String addLikeFood(@CurrentAccount Account account, @PathVariable Long foodId, RedirectAttributes redirectAttributes){
        Food food = foodService.findById(foodId);
        accountService.addLikeFood(account, food);
        redirectAttributes.addFlashAttribute("message", "선호음식이 성공적으로 등록되었습니다.");
        return "redirect:/food/" + food.getId();
    }

    @PostMapping("/food/{foodId}/removefood")
    public String removeLikeFood(@CurrentAccount Account account, @PathVariable Long foodId, RedirectAttributes redirectAttributes){
        Food food = foodService.findById(foodId);
        accountService.removeLikeFood(account, food);
        redirectAttributes.addFlashAttribute("message", "선호음식이 성공적으로 삭제되었습니다.");
        return "redirect:/food/" + food.getId();
    }

}

package com.foodwebservice.shop;

import com.foodwebservice.account.Account;
import com.foodwebservice.account.AccountService;
import com.foodwebservice.account.CurrentAccount;
import com.foodwebservice.food.Food;
import com.foodwebservice.food.FoodService;
import com.foodwebservice.food.dto.IngredientDto;
import com.foodwebservice.food_ingredient.FoodIngredient;
import com.foodwebservice.food_ingredient.FoodIngredientRepository;
import com.foodwebservice.food_ingredient.FoodIngredientService;
import com.foodwebservice.shop.form.BasketForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@SessionAttributes("basketForm")
@RequiredArgsConstructor
@Controller
public class SelectController {

    private final AccountService accountService;
    private final FoodService foodService;
    private final FoodIngredientService foodIngredientService;
    @ModelAttribute
    public BasketForm basketForm() {
        return new BasketForm();
    }

    @PostMapping("/shop/select/{foodId}/add")
    public String addSelectFood(@CurrentAccount Account account, @PathVariable Long foodId, BasketForm basketForm){
        Food food = foodService.findById(foodId);
        basketForm.getSelectedFoods().add(food);
        return "redirect:/shop";
    }

    @PostMapping("/shop/select/{foodId}/remove")
    public String removeSelectFood(@CurrentAccount Account account, @PathVariable Long foodId, BasketForm basketForm){
        Food food = foodService.findById(foodId);
        basketForm.getSelectedFoods().remove(food);
        return "redirect:/shop";
    }

    @GetMapping("/shop/select/buy")
    public String buySelectFood(@CurrentAccount Account account, BasketForm basketForm, Model model) {
        List<IngredientDto> ingredients = new ArrayList<>();
        basketForm.getSelectedFoods().forEach(food -> ingredients.addAll(foodIngredientService.findAllIngredientsAndAmountByFood(food)));

        model.addAttribute(account);
        model.addAttribute("ingredients", ingredients);
        return "shop/buy";
    }

    @GetMapping("/shop/select/next")
    public String nextView(@CurrentAccount Account account, Model model, BasketForm basketForm) {
        List<Food> foods = new ArrayList<>();
        basketForm.getSelectedFoods().forEach((food) -> foods.add(foodService.findById(food.getId())));
        account = accountService.findById(account.getId());
        model.addAttribute(account);
        model.addAttribute("foods", foods);
        return "shop/next";
    }

}

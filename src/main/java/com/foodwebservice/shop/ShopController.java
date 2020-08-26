package com.foodwebservice.shop;

import com.foodwebservice.account.Account;
import com.foodwebservice.account.AccountService;
import com.foodwebservice.account.CurrentAccount;
import com.foodwebservice.food.Food;
import com.foodwebservice.food.FoodRepository;
import com.foodwebservice.shop.form.BasketForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@SessionAttributes("basketForm")
@RequiredArgsConstructor
@Controller
public class ShopController {

    private final AccountService accountService;
    private final FoodRepository foodRepository;
    private final ShopService shopService;

    @GetMapping("/shop")
    public String shopListView(@CurrentAccount Account user, Model model, BasketForm basketForm){
        List<Food> foods = shopService.findWithFoodAllByAccount(user).stream().map(Shop::getFood).collect(Collectors.toList());
        model.addAttribute(basketForm);
        model.addAttribute(user);
        model.addAttribute("foods", foods);
        return "shop/shop";
    }

    @PostMapping("/shop/{foodId}/add")
    public String addShoppingList(@CurrentAccount Account user, @PathVariable Long foodId, RedirectAttributes redirectAttributes){
        Account account = accountService.findById(user.getId());
        Food food = foodRepository.findById(foodId).orElseThrow(IllegalArgumentException::new);
        shopService.addShop(account, food);
        redirectAttributes.addFlashAttribute("message", "장바구니에 등록되었습니다.");
        return "redirect:/food/" + food.getId();
    }

    @PostMapping("/shop/{foodId}/remove")
    public String removeShoppingList(@CurrentAccount Account user, @PathVariable Long foodId, RedirectAttributes redirectAttributes){
        Account account = accountService.findById(user.getId());
        Food food = foodRepository.findById(foodId).orElseThrow(IllegalArgumentException::new);
        shopService.removeShop(account, food);
        redirectAttributes.addFlashAttribute("message", "장바구니에서 삭제되었습니다.");
        return "redirect:/food/" + food.getId();
    }
}

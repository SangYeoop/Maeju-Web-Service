package com.foodwebservice.shop;

import com.foodwebservice.account.Account;
import com.foodwebservice.account.AccountService;
import com.foodwebservice.account.CurrentAccount;
import com.foodwebservice.food.Food;
import com.foodwebservice.food.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@Controller
public class ShopController {

    private final AccountService accountService;
    private final FoodRepository foodRepository;
    private final ShopService shopService;

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

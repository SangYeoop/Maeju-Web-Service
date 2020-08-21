package com.foodwebservice.food.type;

import com.foodwebservice.account.Account;
import com.foodwebservice.account.CurrentAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@SessionAttributes("foodTypeForm")
@Controller
public class FoodTypeController {
    private final FoodTypeService foodTypeService;

    @ModelAttribute("foodTypeForm")
    public FoodTypeForm foodTypeForm(@CurrentAccount Account account) {
        FoodTypeForm foodTypeForm = new FoodTypeForm();
        foodTypeForm.setFoodsName(foodTypeService.getFoodNames());
        foodTypeForm.setIndex(0);
        return foodTypeForm;
    }

    @GetMapping("/food/type")
    public String foodTypeView(@CurrentAccount Account account, FoodTypeForm foodTypeForm, Model model) {
        model.addAttribute(account);
        model.addAttribute(foodTypeForm);
        return "food/type";
    }

    @PostMapping("/food/type")
    public String foodTypeRequest(@CurrentAccount Account account, FoodTypeForm foodTypeForm, Model model,
                                  String foodName, SessionStatus status, RedirectAttributes redirectAttributes) {
        int index = foodTypeForm.getIndex();

        if(foodTypeForm.getFoodsName().get(index).equals(foodName))
            foodTypeForm.getFoodsName().remove(index + 1);
        else
            foodTypeForm.getFoodsName().remove(index);
        foodTypeForm.getSelected().add(foodName);
        foodTypeForm.setIndex(index + 1);

        if(foodTypeForm.getFoodsName().size() == foodTypeForm.getIndex()) {
            foodTypeForm.setIndex(0);
        }

        if(foodTypeForm.getFoodsName().size() == 1) {
            status.setComplete();
            redirectAttributes.addFlashAttribute("message", "음식 유형 분석을 완료했습니다.");
            return "redirect:/";
        }

        return "redirect:/food/type";
    }


}

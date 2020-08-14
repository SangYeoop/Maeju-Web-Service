package com.foodwebservice.diet;

import com.foodwebservice.account.Account;
import com.foodwebservice.account.CurrentAccount;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DietController {

    @GetMapping("/diet")
    public String dietView(@CurrentAccount Account account, Model model) {
        model.addAttribute(account);

        return "diet/diet";
    }

    @GetMapping("/diet/type")
    public String dietTypeForm(@CurrentAccount Account account) {
        return "diet/diet-type";
    }

    @GetMapping("/diet/example")
    public String dietExampleView(){
        return "diet/diet-example";
    }
}

package com.foodwebservice.main;

import com.foodwebservice.account.Account;
import com.foodwebservice.account.CurrentAccount;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String indexView(@CurrentAccount Account account) {
        if(account != null) {
            System.out.println(account.getEmail());
        }
        return "index";
    }

    @GetMapping("/login")
    public String loginView() {
        return "login";
    }


    @GetMapping("/search")
    public String searchView(String foodName, Model model) {
        //Food DB 조회후 Food Name에 해당하는 Value 반환.
        model.addAttribute("foodName", foodName);
        return "search";
    }
}

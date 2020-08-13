package com.foodwebservice.main;

import com.foodwebservice.account.Account;
import com.foodwebservice.account.CurrentAccount;
import org.springframework.stereotype.Controller;
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
}

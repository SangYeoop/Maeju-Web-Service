package com.foodwebservice.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String indexView() {
        return "index";
    }

    @GetMapping("/login")
    public String loginView() {
        return "login";
    }
}

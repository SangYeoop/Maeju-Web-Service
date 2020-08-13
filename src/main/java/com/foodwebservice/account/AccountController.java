package com.foodwebservice.account;

import com.foodwebservice.account.form.SignUpForm;
import com.foodwebservice.account.type.AccountType;
import com.foodwebservice.account.validator.SignUpFormValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class AccountController {

    private final SignUpFormValidator signUpFormValidator;
    private final AccountService accountService;

    @InitBinder("signUpForm")
    public void signUpFormInitBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(signUpFormValidator);
    }

    @GetMapping("/sign-up")
    public String signUpView(Model model){
        model.addAttribute("signUpForm", new SignUpForm());
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String signUpRequest(@Valid SignUpForm signUpForm, Errors errors, RedirectAttributes redirectAttributes) {
        if(errors.hasErrors()){
            return "sign-up";
        }
        accountService.makeAccount(signUpForm, AccountType.LOCAL);
        redirectAttributes.addFlashAttribute("message", "회원가입 되었습니다.");
        return "redirect:/";
    }

}

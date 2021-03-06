package com.foodwebservice.account;

import com.foodwebservice.account.form.PasswordForm;
import com.foodwebservice.account.form.ProfileUpdateForm;
import com.foodwebservice.account.type.AccountType;
import com.foodwebservice.account.validator.PasswordFormValidator;
import com.foodwebservice.diet.Diet;
import com.foodwebservice.diet.DietRepository;
import com.foodwebservice.diet.DietType;
import com.foodwebservice.food.Food;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class AccountSettingsController {

    private final ModelMapper modelMapper;
    private final AccountService accountService;
    private final AccountRepository accountRepository;
    private final DietRepository dietRepository;

    @InitBinder("passwordForm")
    public void passwordFormInitBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(new PasswordFormValidator());
    }

    @GetMapping("/settings/profile")
    public String settingsProfileView(@CurrentAccount Account account, Model model){
        account = accountService.findById(account.getId());

        Diet diet = dietRepository.findByAccount(account).orElse(null);
        DietType dietType = DietType.NONE;
        if(diet != null)
            dietType = diet.getDietType();

        ProfileUpdateForm profileUpdateForm = modelMapper.map(account, ProfileUpdateForm.class);
        profileUpdateForm.setDietType(dietType);

        model.addAttribute(account);
        model.addAttribute(profileUpdateForm);
        return "settings/profile";
    }

    @PostMapping("/settings/profile")
    public String updateAccountProfile(@CurrentAccount Account account, @Valid ProfileUpdateForm profileUpdateForm, RedirectAttributes redirectAttributes) {
        accountService.updateProfile(account, profileUpdateForm);
        redirectAttributes.addFlashAttribute("message", "변경사항을 저장하였습니다.");
        return "redirect:/settings/profile";
    }

    @GetMapping("/settings/likefood")
    public String settingsLikeFoodView(@CurrentAccount Account account, Model model){
        Account foodViewAccount = accountRepository.findById(account.getId()).orElseThrow(IllegalArgumentException::new);
        model.addAttribute(foodViewAccount);
        model.addAttribute("foods", new ArrayList<>(foodViewAccount.getLikeFoods()));
        return "settings/like-food";
    }

    @GetMapping("/settings/password")
    public String settingsPasswordView(@CurrentAccount Account account, Model model){
        model.addAttribute(account);
        model.addAttribute(new PasswordForm());
        return "settings/password";
    }

    @PostMapping("/settings/password")
    public String updatePassword(@CurrentAccount Account account, @Valid PasswordForm passwordForm, Errors errors, Model model, RedirectAttributes redirectAttributes){
        if (account.getAccountType() != AccountType.LOCAL)
            errors.rejectValue("password", "invalid.password", "로컬계정이 아닌 계정은 비밀번호를 변경할 수 없습니다.");
        else if(!accountService.isEqualPassword(account, passwordForm.getPassword()))
            errors.rejectValue("password", "wrong.password", "현재 비밀번호가 잘못 되었습니다.");
        if(errors.hasErrors()){
            model.addAttribute(account);
            return "settings/password";
        }
        accountService.updatePassword(account, passwordForm.getNewPassword());
        redirectAttributes.addFlashAttribute("message", "비밀번호를 성공적으로 변경했습니다.");
        return "redirect:/settings/password";
    }

    @GetMapping("/settings/account")
    public String settingsAccountView(@CurrentAccount Account account, Model model){
        model.addAttribute(account);
        return "settings/account";
    }

    @PostMapping("/settings/account")
    public String deleteAccount(@CurrentAccount Account account, RedirectAttributes redirectAttributes){
        redirectAttributes.addAttribute("message", "계정이 성공적으로 삭제되었습니다.");
        accountService.delete(account);
        return "redirect:/";
    }
}

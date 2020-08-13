package com.foodwebservice.account.validator;

import com.foodwebservice.account.AccountRepository;
import com.foodwebservice.account.form.SignUpForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@RequiredArgsConstructor
@Component
public class SignUpFormValidator implements Validator {

    private final AccountRepository accountRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(SignUpForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SignUpForm signUpForm = (SignUpForm)target;

        if(accountRepository.existsByEmail(signUpForm.getEmail())) {
            errors.rejectValue("email", "invalid.password", "이미 회원가입된 이메일 입니다.");
        }

        if(!isEqualPassword(signUpForm)){
            errors.rejectValue("password", "wrong.password", "패스워드가 일치하지 않습니다.");
        }
    }

    private boolean isEqualPassword(SignUpForm signUpForm) {
        return signUpForm.getPassword().equals(signUpForm.getConfirmPassword());
    }
}

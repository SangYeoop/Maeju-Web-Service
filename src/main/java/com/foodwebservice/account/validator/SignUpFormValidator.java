package com.foodwebservice.account.validator;

import com.foodwebservice.account.form.SignUpForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SignUpFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(SignUpForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SignUpForm signUpForm = (SignUpForm)target;

        if(!isEqualPassword(signUpForm)){
            errors.rejectValue("password", "wrong.password", "패스워드가 일치하지 않습니다.");
        }
    }

    private boolean isEqualPassword(SignUpForm signUpForm) {
        return signUpForm.getPassword().equals(signUpForm.getConfirmPassword());
    }
}

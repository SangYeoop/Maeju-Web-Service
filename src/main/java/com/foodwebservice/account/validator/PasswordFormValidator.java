package com.foodwebservice.account.validator;


import com.foodwebservice.account.form.PasswordForm;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PasswordFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(PasswordForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PasswordForm passwordForm = (PasswordForm)target;

        if(!passwordForm.getNewPassword().equals(passwordForm.getConfirmPassword())){
            errors.rejectValue("newPassword", "wrong.newPassword", "두 패스워드가 일치하지 않습니다.");
        }
    }
}

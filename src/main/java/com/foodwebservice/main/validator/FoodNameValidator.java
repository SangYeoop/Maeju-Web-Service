package com.foodwebservice.main.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class FoodNameValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(String.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        String foodName = (String)target;

        if(foodName.startsWith(" "))
            errors.reject("error", "띄어쓰기를 시작으로 검색할 수 없습니다.");

        if(foodName.isBlank() || foodName.isEmpty())
            errors.reject("error", "단어가 없는 경우 검색할 수 없습니다.");
    }
}

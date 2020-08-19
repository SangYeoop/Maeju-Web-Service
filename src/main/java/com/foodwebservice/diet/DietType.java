package com.foodwebservice.diet;

import org.springframework.web.servlet.HandlerInterceptor;

public enum  DietType {
    ADVENTURE, //모험
    SIMPLE, //간편
    HEALTH, //건강
    REASONABLE; //합리

    public static DietType getInstanceAsString(String value) {
        if(value.equals("모험"))
            return ADVENTURE;
        else if(value.equals("간편"))
            return SIMPLE;
        else if(value.equals("건강"))
            return HEALTH;
        else
            return REASONABLE;
    }

    public String getString() {
        if(this.equals(ADVENTURE))
            return "모험형";
        else if(this.equals(SIMPLE))
            return "간편형";
        else if(this.equals(HEALTH))
            return "건강/안전형";
        else
            return "합리형";
    }
}

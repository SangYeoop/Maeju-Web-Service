package com.foodwebservice.diet;

import org.springframework.web.servlet.HandlerInterceptor;

public enum  DietType {
    ADVENTURE("모험형"), //모험
    SIMPLE("간편형"), //간편
    HEALTH("건강형"), //건강
    REASONABLE("합리형"); //합리

    private String name;

    DietType(String name){
        this.name = name;
    }

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
        return name;
    }
}

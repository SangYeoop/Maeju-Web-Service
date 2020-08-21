package com.foodwebservice.diet;

import lombok.Getter;

@Getter
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
        for(DietType dietType : DietType.values()){
            if(value.equals(dietType.name))
                return dietType;
        }
        return REASONABLE;
    }

    public String getString() {
        return name;
    }
}

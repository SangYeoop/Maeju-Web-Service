package com.foodwebservice.diet;

import lombok.Getter;

@Getter
public enum  DietType {
    ADVENTURE("모험추구형"), //모험
    SIMPLE("간편추구형"), //간편
    HEALTH("건강/안전추구형"), //건강
    REASONABLE("합리추구형"), //합리
    NONE("식단유형이 정해지지 않았습니다.");

    private String name;

    DietType(String name){
        this.name = name;
    }

    public static DietType getInstanceAsString(String value) {
        for(DietType dietType : DietType.values()){
            if(value.equals(dietType.name))
                return dietType;
        }
        return NONE;
    }

    public String getString() {
        return name;
    }
}

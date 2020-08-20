package com.foodwebservice.food.condition;

public enum Kind {
    SIDE("밑반찬"),
    MAIN("메인반찬"),
    SOUP("국/탕"),
    STEW("찌개"),
    DESERT("디저트"),
    NOODLE("면/만두"),
    RICE("밥/죽/떡"),
    FUSION("퓨전"),
    KIMCHI("김치/젓갈/장류"),
    SAUCE("양념/소스/잼"),
    WESTERN("양식"),
    WESTERN_SOUP("스프"),
    SALAD("샐러드"),
    BREAD("빵"),
    SNACK("과자"),
    BEVERAGE("차/음료/술"),
    ETC("기타");

    private String name;

    Kind(String name){
        this.name = name;
    }

    public String getStringAsInstance(){
        return name;
    }

    public static Kind getInstanceAsString(String type){
        for(Kind kind : Kind.values()){
            if(kind.name.equals(type))
                return kind;
        }
        return ETC;
    }

}

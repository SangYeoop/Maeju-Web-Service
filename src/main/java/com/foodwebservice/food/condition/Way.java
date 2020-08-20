package com.foodwebservice.food.condition;

public enum Way {
    FRY("볶음"),
    SIMMER("끓이기"),
    PAN_FRY("부침"),
    COOK("조림"),
    SEASONING("무침"),
    MIX("비빔"),
    STEAM("찜"),
    PICKLE("절임"),
    OIL_FRY("튀김"),
    BOIL("삶기"),
    ROAST("굽기"),
    BOIL_SLIGHTLY("데치기"),
    SLICE_FISH("회"),
    ETC("기타");

    String name;

    Way(String name){
        this.name = name;
    }

    public String getStringAsInstance(){
       return name;
    }

    public static Way getInstanceAsString(String type){
        for (Way way : Way.values()){
            if(way.name.equals(type))
                return way;
        }
        return ETC;
    }

}

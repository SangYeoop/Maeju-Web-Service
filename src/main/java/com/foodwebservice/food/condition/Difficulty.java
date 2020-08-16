package com.foodwebservice.food.condition;

public enum Difficulty {
    ANYONE, //아무나 
    EASY,  //초급
    NORMAL,  //중급
    HARD; //고급
    
    public static Difficulty getInstanceAsString(String diff) {
        if(diff.equals("아무나"))
            return ANYONE;
        else if(diff.equals("초급"))
            return EASY;
        else if(diff.equals("중급"))
            return NORMAL;
        else if(diff.equals("고급"))
            return HARD;

        return null;
    }
}

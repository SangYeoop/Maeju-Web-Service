package com.foodwebservice.food.condition;

public enum Difficulty {
    ANYONE("아무나"),
    EASY("초급"),
    NORMAL("중급"),
    HARD("고급"),
    NONE("없음");

    private String name;

    Difficulty(String name){
        this.name = name;
    }
    
    public static Difficulty getInstanceAsString(String diff) {
        for(Difficulty difficulty : Difficulty.values()){
            if(difficulty.name.equals(diff))
                return difficulty;
        }
        return NONE;
    }


    public String getStringAsValue() {
        return name;
    }

}

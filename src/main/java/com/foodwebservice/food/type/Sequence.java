package com.foodwebservice.food.type;

import lombok.Getter;

@Getter
public enum Sequence {
    FIRST("1강"),
    SECOND("2강"),
    THIRD("4강"),
    FOURTH("8강"),
    FIFTH("16강"),
    SIXTH("32강");

    private String name;

    Sequence(String name){
        this.name = name;
    }

    public Sequence getPrevious() {
        return values()[(this.ordinal() - 1) % values().length];
    }

}

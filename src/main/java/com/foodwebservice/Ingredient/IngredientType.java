package com.foodwebservice.Ingredient;

import net.bytebuddy.asm.Advice;

public enum IngredientType {
    MEAT, //육류
    VEGETABLE, //채소류
    SEAFOOD, //해물류
    DAIRY, //달걀, 유제품
    PROCESSED, // 가공 식품
    RICE, // 쌀
    FLOUR, // 밀가루
    DRIED_FISH, // 건어물류
    MUSHROOM, // 버섯류
    FRUIT, // 과일류
    NUTS, // 견과류
    GRAIN, // 곡류
    ETC; // 기타

    public String getStringAsInstance(){
        if(this.equals(MEAT))
            return "육류";
        else if(this.equals(VEGETABLE))
            return "채소류";
        else if(this.equals(SEAFOOD))
            return "해물류";
        else if(this.equals(DAIRY))
            return "달걀/유제품";
        else if(this.equals(PROCESSED))
            return "가공 식품";
        else if(this.equals(RICE))
            return "쌀";
        else if(this.equals(FLOUR))
            return "밀가루";
        else if(this.equals(DRIED_FISH))
            return "건어물류";
        else if(this.equals(MUSHROOM))
            return "버섯류";
        else if(this.equals(FRUIT))
            return "과일류";
        else if(this.equals(NUTS))
            return "견과류";
        else if(this.equals(GRAIN))
            return "곡류";
        else
            return "기타";
    }

    public static IngredientType getInstanceAsString(String type){
        if(type.equals("소고기") || type.equals("돼지고기") || type.equals("닭고기") || type.equals("육류"))
            return MEAT;
        else if(type.equals("채소류"))
            return VEGETABLE;
        else if(type.equals("해물류"))
            return SEAFOOD;
        else if(type.equals("달걀/유제품"))
            return DAIRY;
        else if(type.equals("가공식품류"))
            return PROCESSED;
        else if(type.equals("쌀"))
            return RICE;
        else if(type.equals("밀가루"))
            return FLOUR;
        else if(type.equals("건어물류"))
            return DRIED_FISH;
        else if(type.equals("버섯류"))
            return MUSHROOM;
        else if(type.equals("과일류"))
            return FRUIT;
        else if(type.equals("견과류"))
            return NUTS;
        else if(type.equals("곡류"))
            return GRAIN;
        else
            return ETC;
    }

}

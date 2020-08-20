package com.foodwebservice.Ingredient;

import net.bytebuddy.asm.Advice;

public enum IngredientType {
    BEEF, // 소고기
    PORK, // 돼지고기
    CHICKEN, // 닭고기
    GUTS, // 특수부위
    MEAT, // 기타육류
    VEGETABLE, // 채소류
    FISH, // 생선
    SEAFOOD, // 수산물
    DAIRY, // 달걀/유제품
    PROCESSED, // 가공식품류
    RICE, // 쌀
    NOODLE, // 면
    BREAD, // 제과/제빵
    DRIED_FISH, // 건어물류
    MUSHROOM, // 버섯류
    FRUIT, // 과일류
    NUTS, // 콩/견과류
    GRAIN, // 곡류
    ETC; // 기타

    public String getStringAsInstance(){
        if(this.equals(BEEF))
            return "소고기";
        else if(this.equals(PORK))
            return "돼지고기";
        else if(this.equals(CHICKEN))
            return "닭고기";
        else if(this.equals(GUTS))
            return "특수부위";
        else if(this.equals(MEAT))
            return "기타육류";
        else if(this.equals(VEGETABLE))
            return "채소류";
        else if(this.equals(FISH))
            return "생선";
        else if(this.equals(SEAFOOD))
            return "수산물";
        else if(this.equals(DAIRY))
            return "달걀/유제품";
        else if(this.equals(PROCESSED))
            return "가공식품류";
        else if(this.equals(RICE))
            return "쌀";
        else if(this.equals(NOODLE))
            return "면";
        else if(this.equals(BREAD))
            return "제과/제빵";
        else if(this.equals(DRIED_FISH))
            return "건어물류";
        else if(this.equals(MUSHROOM))
            return "버섯류";
        else if(this.equals(FRUIT))
            return "과일류";
        else if(this.equals(NUTS))
            return "콩/견과류";
        else if(this.equals(GRAIN))
            return "곡류";
        else
            return "기타";
    }

    public static IngredientType getInstanceAsString(String type){
        if(type.equals("소고기"))
            return BEEF;
        else if(type.equals("돼지고기"))
            return PORK;
        else if(type.equals("닭고기"))
            return CHICKEN;
        else if(type.equals("기타육류"))
            return MEAT;
        else if(type.equals("특수부위"))
            return GUTS;
        else if(type.equals("채소류"))
            return VEGETABLE;
        else if(type.equals("생선"))
            return FISH;
        else if(type.equals("수산물"))
            return SEAFOOD;
        else if(type.equals("달걀/유제품"))
            return DAIRY;
        else if(type.equals("가공식품류"))
            return PROCESSED;
        else if(type.equals("쌀"))
            return RICE;
        else if(type.equals("면"))
            return NOODLE;
        else if(type.equals("제과/제빵"))
            return BREAD;
        else if(type.equals("건어물류"))
            return DRIED_FISH;
        else if(type.equals("버섯류"))
            return MUSHROOM;
        else if(type.equals("과일류"))
            return FRUIT;
        else if(type.equals("콩/견과류"))
            return NUTS;
        else if(type.equals("곡류"))
            return GRAIN;
        else
            return ETC;
    }


}

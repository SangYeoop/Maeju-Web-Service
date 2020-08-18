package com.foodwebservice.food.condition;

public enum Situation {
    DAILY, // 일상
    SPEED, // 스피드
    GUEST, // 손님접대
    DIET, // 다이어트
    BAR_SNACK, // 술안주
    LUNCH_BOX, // 도시락
    Nutrition, // 영양식
    SNACK, // 간식
    MIDNIGHT, // 야식
    STYLING, // 푸드 스타일링
    HANGOVER, // 숙취해소
    HOLIDAY, // 명절
    BABY, //이유식
    ETC; //기타


    public static Situation getInstanceAsString(String situation) {
        if(situation.equals("일상"))
            return DAILY;
        else if(situation.equals("초스피드"))
            return SPEED;
        else if(situation.equals("손님접대"))
            return GUEST;
        else if(situation.equals("다이어트"))
            return DIET;
        else if(situation.equals("술안주"))
            return BAR_SNACK;
        else if(situation.equals("도시락"))
            return LUNCH_BOX;
        else if(situation.equals("영양식"))
            return Nutrition;
        else if(situation.equals("간식"))
            return SNACK;
        else if(situation.equals("야식"))
            return MIDNIGHT;
        else if(situation.equals("푸드스타일링"))
            return STYLING;
        else if(situation.equals("해장"))
            return HANGOVER;
        else if(situation.equals("명절"))
            return HOLIDAY;
        else if(situation.equals("이유식"))
            return BABY;
        else
            return ETC;
    }
}

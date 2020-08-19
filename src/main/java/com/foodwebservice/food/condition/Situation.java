package com.foodwebservice.food.condition;

public enum Situation {
    DAILY, // 일상
    SPEED, // 스피드
    GUEST, // 손님접대
    DIET, // 다이어트
    BAR_SNACK, // 술안주
    LUNCH_BOX, // 도시락
    NUTRITION, // 영양식
    SNACK, // 간식
    MIDNIGHT, // 야식
    STYLING, // 푸드 스타일링
    HANGOVER, // 숙취해소
    HOLIDAY, // 명절
    BABY, //이유식
    ETC; //기타

    public String getStringAsInstance(){
        if(this.equals(DAILY))
            return "일상";
        else if(this.equals(SPEED))
            return "초스피드";
        else if(this.equals(GUEST))
            return "손님 접대";
        else if(this.equals(DIET))
            return "다이어트";
        else if(this.equals(BAR_SNACK))
            return "술안주";
        else if(this.equals(LUNCH_BOX))
            return "도시락";
        else if(this.equals(NUTRITION))
            return "영양식";
        else if(this.equals(SNACK))
            return "간식";
        else if(this.equals(MIDNIGHT))
            return "야식";
        else if(this.equals(STYLING))
            return "푸드 스타일링";
        else if(this.equals(HANGOVER))
            return "숙취 해소";
        else if(this.equals(HOLIDAY))
            return "명절";
        else if(this.equals(BABY))
            return "이유식";
        else
            return "기타";
    }

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
            return NUTRITION;
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

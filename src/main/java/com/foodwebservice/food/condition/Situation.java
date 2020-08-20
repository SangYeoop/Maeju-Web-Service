package com.foodwebservice.food.condition;

public enum Situation {
    DAILY("일상"), // 일상
    SPEED("초스피드"), // 스피드
    GUEST("손님접대"), // 손님접대
    DIET("다이어트"), // 다이어트
    BAR_SNACK("술안주"), // 술안주
    LUNCH_BOX("도시락"), // 도시락
    NUTRITION("영양식"), // 영양식
    SNACK("간식"), // 간식
    MIDNIGHT("야식"), // 야식
    STYLING("푸드스타일링"), // 푸드 스타일링
    HANGOVER("해장"), // 숙취해소
    HOLIDAY("명절"), // 명절
    BABY("이유식"), //이유식
    ETC("기타"); //기타
    
    private String name;
    
    Situation(String name){
        this.name = name;
    }
    
    public String getStringAsInstance(){
        return name;
    }

    public static Situation getInstanceAsString(String type) {
        for (Situation situation : Situation.values()){
            if(situation.name.equals(type)){
                return situation;
            }
        }
        return ETC;
    }
}

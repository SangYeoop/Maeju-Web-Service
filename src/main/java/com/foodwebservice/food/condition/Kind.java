package com.foodwebservice.food.condition;

public enum Kind {
    SIDE, //사이드 메뉴
    MAIN, //메인 메뉴
    SOUP, // 국,탕
    STEW, // 찌개
    DESERT, //디저트
    NOODLE, //면
    RICE, // 밥,죽,떡
    FUSION, //퓨전
    KIMCHI, //김치,젓갈,장류
    SAUCE,  //양념,소스
    WESTERN, //양식
    WESTERN_SOUP, //양식 스프
    SALAD, //샐러드
    BREAD, //빵
    SNACK, //과자
    BEVERAGE, //음료
    ETC; //기타

    public static Kind getInstanceAsString(String kind){
        if(kind.equals("밑반찬"))
            return SIDE;
        else if(kind.equals("메인반찬"))
            return MAIN;
        else if(kind.equals("국/탕"))
            return SOUP;
        else if(kind.equals("찌개"))
            return STEW;
        else if(kind.equals("디저트"))
            return DESERT;
        else if(kind.equals("면/만두"))
            return NOODLE;
        else if(kind.equals("밥/죽/떡"))
            return RICE;
        else if(kind.equals("퓨전"))
            return FUSION;
        else if(kind.equals("김치/젓갈/장류"))
            return KIMCHI;
        else if(kind.equals("양념/소스/잼"))
            return SAUCE;
        else if(kind.equals("양식"))
            return WESTERN;
        else if(kind.equals("샐러드"))
            return SALAD;
        else if(kind.equals("스프"))
            return WESTERN_SOUP;
        else if(kind.equals("빵"))
            return BREAD;
        else if(kind.equals("과자"))
            return SNACK;
        else if(kind.equals("차/음료/술"))
            return BEVERAGE;
        else
            return ETC;
    }

}

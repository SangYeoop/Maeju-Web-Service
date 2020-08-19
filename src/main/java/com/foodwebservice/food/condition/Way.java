package com.foodwebservice.food.condition;

public enum Way {
    FRY, //볶음
    SIMMER, //끓이기
    PAN_FRY, // 부침
    COOK, //조림
    SEASONING, //무침
    MIX, //비빔
    STEAM, //찜
    PICKLE, //절임
    OIL_FRY, //튀김
    BOIL,  //삶다
    ROAST, //굽다
    BOIL_SLIGHTLY, //데치다
    SLICE_FISH, //회
    ETC; // 기타

    public String getStringAsInstance(){
        if(this.equals(FRY))
            return "볶음";
        else if(this.equals(SIMMER))
            return "끓이기";
        else if(this.equals(PAN_FRY))
            return "부침";
        else if(this.equals(COOK))
            return "조림";
        else if(this.equals(SEASONING))
            return "무침";
        else if(this.equals(MIX))
            return "비빔";
        else if(this.equals(STEAM))
            return "찜";
        else if(this.equals(PICKLE))
            return "절임";
        else if(this.equals(OIL_FRY))
            return "튀김";
        else if(this.equals(BOIL))
            return "삶기";
        else if(this.equals(ROAST))
            return "굽기";
        else if(this.equals(BOIL_SLIGHTLY))
            return "데치기";
        else if(this.equals(SLICE_FISH))
            return "회";
        else
            return "기타";
    }

    public static Way getInstanceAsString(String way){
        if(way.equals("볶음"))
            return FRY;
        else if(way.equals("끓이기"))
            return SIMMER;
        else if(way.equals("부침"))
            return PAN_FRY;
        else if(way.equals("조림"))
            return COOK;
        else if(way.equals("무침"))
            return SEASONING;
        else if(way.equals("비빔"))
            return MIX;
        else if(way.equals("찜"))
            return STEAM;
        else if(way.equals("절임"))
            return PICKLE;
        else if(way.equals("튀김"))
            return OIL_FRY;
        else if(way.equals("삶기"))
            return BOIL;
        else if(way.equals("굽기"))
            return ROAST;
        else if(way.equals("데치기"))
            return BOIL_SLIGHTLY;
        else if(way.equals("회"))
            return SLICE_FISH;
        else
            return ETC;
    }

}

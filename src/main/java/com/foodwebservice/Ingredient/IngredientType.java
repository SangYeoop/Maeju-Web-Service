package com.foodwebservice.Ingredient;

public enum IngredientType {
    PORK("돼지고기"),
    BEEF("소고기"),
    CHICKEN("닭고기"),
    MEAT("육류"),
    VEGETABLE("채소류"),
    SEAFOOD("해물류"),
    DAIRY("달걀/유제품"),
    PROCESSED("가공식품류"),
    RICE("쌀"),
    FLOUR("밀가루"),
    DRIED_FISH("건어물류"),
    MUSHROOM("버섯류"),
    FRUIT("과일류"),
    NUTS("콩/견과류"),
    GRAIN("곡류"),
    ETC("기타");

    private String name;

    IngredientType(String name) {
        this.name = name;
    }

    public String getStringAsInstance(){
       return name;
    }

    public static IngredientType getInstanceAsString(String type){
        for(IngredientType ingredientType : IngredientType.values()) {
            if(ingredientType.name.equals(type))
                return ingredientType;
        }
        return ETC;
    }

}

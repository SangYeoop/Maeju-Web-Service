package com.foodwebservice.preference;

import com.foodwebservice.account.Account;
import com.foodwebservice.food.condition.Kind;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity @Getter
@Setter @EqualsAndHashCode(of = "id")
@NoArgsConstructor @AllArgsConstructor
public class KindPreference {

    @Id @GeneratedValue
    private Long id;

    @OneToOne
    private Account account;

    private int countBySide;

    private int countByMain;

    private int countBySoup;

    private int countByStew;

    private int countByDesert;

    private int countByNoodle;

    private int countByRice;

    private int countByFusion;

    private int countByKimchi;

    private int countBySauce;

    private int countByWestern;

    private int countByWesternSoup;

    private int countBySalad;

    private int countByBread;

    private int countBySnack;

    private int countByBeverage;

    private int countByEtc;


    public KindPreference count(Kind kind){
        if(kind.equals(Kind.SIDE))
            countBySide++;
        else if(kind.equals(Kind.MAIN))
            countByMain++;
        else if(kind.equals(Kind.SOUP))
            countBySoup++;
        else if(kind.equals(Kind.STEW))
            countByStew++;
        else if(kind.equals(Kind.DESERT))
            countByDesert++;
        else if(kind.equals(Kind.NOODLE))
            countByNoodle++;
        else if(kind.equals(Kind.RICE))
            countByRice++;
        else if(kind.equals(Kind.FUSION))
            countByFusion++;
        else if(kind.equals(Kind.KIMCHI))
            countByKimchi++;
        else if(kind.equals(Kind.SAUCE))
            countBySauce++;
        else if(kind.equals(Kind.WESTERN))
            countByWestern++;
        else if(kind.equals(Kind.WESTERN_SOUP))
            countByWesternSoup++;
        else if(kind.equals(Kind.SALAD))
            countBySalad++;
        else if(kind.equals(Kind.BREAD))
            countByBread++;
        else if(kind.equals(Kind.SNACK))
            countBySnack++;
        else if(kind.equals(Kind.BEVERAGE))
            countByBeverage++;
        else
            countByEtc++;
        return this;
    }
}

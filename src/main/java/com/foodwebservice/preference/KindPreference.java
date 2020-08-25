package com.foodwebservice.preference;

import com.foodwebservice.account.Account;
import com.foodwebservice.food.condition.Kind;
import com.foodwebservice.parser.Tuple;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Kind> getPreferenceKinds() {
        List<Tuple<Kind, Integer>> kinds = getTuplesKindCount();
        kinds.sort((tuple1, tuple2) -> tuple2.getSecond().compareTo(tuple1.getSecond()));

        return kinds.subList(0, 1).stream().map(Tuple::getFirst).collect(Collectors.toList());
    }

    private List<Tuple<Kind, Integer>> getTuplesKindCount() {
        List<Tuple<Kind, Integer>> kinds = new ArrayList<>();
        kinds.add(Tuple.of(Kind.SIDE, countBySide));
        kinds.add(Tuple.of(Kind.MAIN, countByMain));
        kinds.add(Tuple.of(Kind.SOUP, countBySoup));
        kinds.add(Tuple.of(Kind.STEW, countByStew));
        kinds.add(Tuple.of(Kind.DESERT, countByDesert));
        kinds.add(Tuple.of(Kind.NOODLE, countByNoodle));
        kinds.add(Tuple.of(Kind.RICE, countByRice));
        kinds.add(Tuple.of(Kind.FUSION, countByFusion));
        kinds.add(Tuple.of(Kind.KIMCHI, countByKimchi));
        kinds.add(Tuple.of(Kind.SAUCE, countBySauce));
        kinds.add(Tuple.of(Kind.WESTERN, countByWestern));
        kinds.add(Tuple.of(Kind.WESTERN_SOUP, countByWesternSoup));
        kinds.add(Tuple.of(Kind.SALAD, countBySalad));
        kinds.add(Tuple.of(Kind.BREAD, countByBread));
        kinds.add(Tuple.of(Kind.SNACK, countBySnack));
        kinds.add(Tuple.of(Kind.BEVERAGE, countByBeverage));
        return kinds;
    }

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

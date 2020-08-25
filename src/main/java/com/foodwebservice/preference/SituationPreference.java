package com.foodwebservice.preference;

import com.foodwebservice.account.Account;
import com.foodwebservice.food.condition.Kind;
import com.foodwebservice.food.condition.Situation;
import com.foodwebservice.parser.Tuple;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter @Setter @EqualsAndHashCode(of = "id")
@NoArgsConstructor @AllArgsConstructor
public class SituationPreference {

    @Id @GeneratedValue
    private Long id;

    @OneToOne
    private Account account;

    private int countByDaily;

    private int countBySpeed;

    private int countByGuest;

    private int countByDiet;

    private int countByBarSnack;

    private int countByLunchBox;

    private int countByNutrition;

    private int countBySnack;

    private int countByMidNight;

    private int countByStyling;

    private int countByHangover;

    private int countByHoliday;

    private int countByBaby;

    private int countByEtc;

    public List<Situation> getPreferenceSituations() {
        List<Tuple<Situation, Integer>> situations = getTuplesSituationCount();
        situations.sort((tuple1, tuple2) -> tuple2.getSecond().compareTo(tuple1.getSecond()));

        return situations.subList(0, 1).stream().map(Tuple::getFirst).collect(Collectors.toList());
    }

    private List<Tuple<Situation, Integer>> getTuplesSituationCount() {
        List<Tuple<Situation, Integer>> situations = new ArrayList<>();

        situations.add(Tuple.of(Situation.DAILY, countByDaily));
        situations.add(Tuple.of(Situation.SPEED, countBySpeed));
        situations.add(Tuple.of(Situation.GUEST, countByGuest));
        situations.add(Tuple.of(Situation.DIET, countByDiet));
        situations.add(Tuple.of(Situation.BAR_SNACK, countByBarSnack));
        situations.add(Tuple.of(Situation.LUNCH_BOX, countByLunchBox));
        situations.add(Tuple.of(Situation.NUTRITION, countByNutrition));
        situations.add(Tuple.of(Situation.SNACK, countBySnack));
        situations.add(Tuple.of(Situation.MIDNIGHT, countByMidNight));
        situations.add(Tuple.of(Situation.STYLING, countByStyling));
        situations.add(Tuple.of(Situation.HANGOVER, countByHangover));
        situations.add(Tuple.of(Situation.HOLIDAY, countByHoliday));
        situations.add(Tuple.of(Situation.BABY, countByBaby));

        return situations;
    }

    public SituationPreference count(Situation situation){
        if(situation.equals(Situation.DAILY))
            countByDaily++;
        else if(situation.equals(Situation.SPEED))
            countBySpeed++;
        else if(situation.equals(Situation.GUEST))
            countByGuest++;
        else if(situation.equals(Situation.DIET))
            countByDiet++;
        else if(situation.equals(Situation.BAR_SNACK))
            countByBarSnack++;
        else if(situation.equals(Situation.LUNCH_BOX))
            countByLunchBox++;
        else if(situation.equals(Situation.NUTRITION))
            countByNutrition++;
        else if(situation.equals(Situation.SNACK))
            countBySnack++;
        else if(situation.equals(Situation.MIDNIGHT))
            countByMidNight++;
        else if(situation.equals(Situation.STYLING))
            countByStyling++;
        else if(situation.equals(Situation.HANGOVER))
            countByHangover++;
        else if(situation.equals(Situation.HOLIDAY))
            countByHoliday++;
        else if(situation.equals(Situation.BABY))
            countByBaby++;
        else
            countByEtc++;
        return this;
    }
}

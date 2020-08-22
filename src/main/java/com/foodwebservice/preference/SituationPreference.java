package com.foodwebservice.preference;

import com.foodwebservice.account.Account;
import com.foodwebservice.food.condition.Situation;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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

    public void count(Situation situation){
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
    }
}

package com.foodwebservice.preference;

import com.foodwebservice.account.Account;
import com.foodwebservice.food.condition.Way;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Getter @Setter @EqualsAndHashCode(of = "id")
@NoArgsConstructor @AllArgsConstructor
public class WayPreference {

    @Id @GeneratedValue
    private Long id;

    @OneToOne
    private Account account;

    private int countByFry;

    private int countBySimmer;

    private int countByPanFry;

    private int countByCook;

    private int countBySeasoning;

    private int countByMix;

    private int countBySteam;

    private int countByPickle;

    private int countByOilFry;

    private int countByBoil;

    private int countByRoast;

    private int countByBoilSlightly;

    private int countBySliceFish;

    private int countByEtc;

    public WayPreference count(Way way){
        if(way.equals(Way.FRY))
            countByFry++;
        else if(way.equals(Way.SIMMER))
            countBySimmer++;
        else if(way.equals(Way.PAN_FRY))
            countByPanFry++;
        else if(way.equals(Way.COOK))
            countByCook++;
        else if(way.equals(Way.SEASONING))
            countBySeasoning++;
        else if(way.equals(Way.MIX))
            countByMix++;
        else if(way.equals(Way.STEAM))
            countBySteam++;
        else if(way.equals(Way.PICKLE))
            countByPickle++;
        else if(way.equals(Way.OIL_FRY))
            countByOilFry++;
        else if(way.equals(Way.BOIL))
            countByBoil++;
        else if(way.equals(Way.ROAST))
            countByRoast++;
        else if(way.equals(Way.BOIL_SLIGHTLY))
            countByBoilSlightly++;
        else if(way.equals(Way.SLICE_FISH))
            countBySliceFish++;
        else
            countByEtc++;
        return this;
    }
}

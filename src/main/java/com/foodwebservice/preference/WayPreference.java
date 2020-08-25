package com.foodwebservice.preference;

import com.foodwebservice.account.Account;
import com.foodwebservice.food.condition.Situation;
import com.foodwebservice.food.condition.Way;
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

    public List<Way> getPreferenceWays() {
        List<Tuple<Way, Integer>> ways = getTuplesWayCount();
        ways.sort((tuple1, tuple2) -> tuple2.getSecond().compareTo(tuple1.getSecond()));

        return ways.subList(0, 1).stream().map(Tuple::getFirst).collect(Collectors.toList());
    }

    private List<Tuple<Way, Integer>> getTuplesWayCount() {
        List<Tuple<Way, Integer>> ways = new ArrayList<>();
        ways.add(Tuple.of(Way.FRY, countByFry));
        ways.add(Tuple.of(Way.SIMMER, countBySimmer));
        ways.add(Tuple.of(Way.PAN_FRY, countByPanFry));
        ways.add(Tuple.of(Way.COOK, countByCook));
        ways.add(Tuple.of(Way.SEASONING, countBySeasoning));
        ways.add(Tuple.of(Way.MIX, countByMix));
        ways.add(Tuple.of(Way.STEAM, countBySteam));
        ways.add(Tuple.of(Way.PICKLE, countByPickle));
        ways.add(Tuple.of(Way.OIL_FRY, countByOilFry));
        ways.add(Tuple.of(Way.BOIL, countByBoil));
        ways.add(Tuple.of(Way.ROAST, countByRoast));
        ways.add(Tuple.of(Way.BOIL_SLIGHTLY, countByBoilSlightly));
        ways.add(Tuple.of(Way.SLICE_FISH, countBySliceFish));
        return ways;
    }

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

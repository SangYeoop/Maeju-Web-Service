package com.foodwebservice.preference;

import com.foodwebservice.Ingredient.IngredientType;
import com.foodwebservice.food.condition.Kind;
import com.foodwebservice.food.condition.Situation;
import com.foodwebservice.food.condition.Way;
import com.foodwebservice.parser.Tuple;

import java.util.HashSet;
import java.util.Set;

/*사용법
사용자가 어떤 음식을 선호하는지 파악하기 위해 종류별, 방법별, 상황별, 재료별로 각각의 SET을 만들어 저장했음.
SET으로 한 이유는 어차피 들어갈 원소의 개수가 정해져 있어서 편하게 하기 위함.
 */

public class UserPreferenceData {
    private Set<Tuple<Object, Integer>> kinds;
    private Set<Tuple<Object, Integer>> ways;
    private Set<Tuple<Object, Integer>> situations;
    private Set<Tuple<Object, Integer>> ingredients;

    public UserPreferenceData(){
        kinds = new HashSet<>();
        ways = new HashSet<>();
        situations = new HashSet<>();
        ingredients = new HashSet<>();

        for(Kind k : Kind.values()){
            kinds.add(Tuple.of(k, 0));
        }
        for(Way w : Way.values()){
            ways.add(Tuple.of(w, 0));
        }
        for(Situation s : Situation.values()){
            situations.add(Tuple.of(s, 0));
        }
        for(IngredientType i : IngredientType.values()){
            ingredients.add(Tuple.of(i, 0));
        }
    }

    public Set<Tuple<Object, Integer>> getIngredients() {
        return ingredients;
    }

    public Set<Tuple<Object, Integer>> getKinds() {
        return kinds;
    }

    public Set<Tuple<Object, Integer>> getSituations() {
        return situations;
    }

    public Set<Tuple<Object, Integer>> getWays() {
        return ways;
    }

    public void printAll(){
        System.out.println("종류");
        for(Tuple t : kinds){
            System.out.println(t.getFirst() + " " + t.getSecond());
        }
        System.out.println("방법");
        for(Tuple t : ways){
            System.out.println(t.getFirst() + " " + t.getSecond());
        }
        System.out.println("상황");
        for(Tuple t : situations){
            System.out.println(t.getFirst() + " " + t.getSecond());
        }
        System.out.println("재료");
        for(Tuple t : ingredients){
            System.out.println(t.getFirst() + " " + t.getSecond());
        }
    }
}

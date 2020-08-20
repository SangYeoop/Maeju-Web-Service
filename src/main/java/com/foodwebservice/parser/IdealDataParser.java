package com.foodwebservice.parser;

import com.foodwebservice.Ingredient.IngredientType;
import com.foodwebservice.food.condition.Kind;
import com.foodwebservice.food.condition.Situation;
import com.foodwebservice.food.condition.Way;
import com.foodwebservice.preference.UserPreferenceData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*사용법
IdealDataParser idealDataParser = new IdealDataParser();
idealDataParser.getPreferenceFromIdeal(arrayList);
arrayList는 사용자가 선택한 음식이름들이 들어있는 리스트
반환값은 UserPreferenceData임
 */


public class IdealDataParser {
    public UserPreferenceData getPreferenceFromIdeal(List<String> selectedItems){
        UserPreferenceData userPreferenceData = new UserPreferenceData();

        File file = new File("");
        String path = file.getAbsolutePath() + "\\src\\main\\resources\\text\\LeeSanghyung.txt";

        List<String> standard = readFile(path);

        for(String item : selectedItems){
            for(String s : standard){
                String[] strings = s.split(",");
                if(item.equals(strings[1])){
                    for(int i = 5; i < 9; i++){
                        String[] specificInfo = extractSpecificInfo(strings[i]);
                        for(String str : specificInfo){
                            if(i == 5){
                                addInfoTupleIntoSet_kind(userPreferenceData.getKinds(), str);
                            }else if(i == 6){
                                addInfoTupleIntoSet_way(userPreferenceData.getWays(), str);
                            }else if(i == 7){
                                addInfoTupleIntoSet_situation(userPreferenceData.getSituations(), str);
                            }else if(i == 8){
                                addInfoTupleIntoSet_ingredient(userPreferenceData.getIngredients(), str);
                            }
                        }
                    }
                    break;
                }
            }
        }

        return userPreferenceData;
    }

    private void addInfoTupleIntoSet_kind(Set<Tuple<Object, Integer>> specificPreferenceData, String specificInfo){
        for(Tuple<Object, Integer> t : specificPreferenceData){
            if(t.getFirst().equals(Kind.getInstanceAsString(specificInfo))){
                specificPreferenceData.remove(t);
                specificPreferenceData.add(Tuple.of(Kind.getInstanceAsString(specificInfo), t.getSecond()+1));
                return;
            }
        }
    }
    private void addInfoTupleIntoSet_way(Set<Tuple<Object, Integer>> specificPreferenceData, String specificInfo){
        for(Tuple<Object, Integer> t : specificPreferenceData){
            if(t.getFirst().equals(Way.getInstanceAsString(specificInfo))){
                specificPreferenceData.remove(t);
                specificPreferenceData.add(Tuple.of(Way.getInstanceAsString(specificInfo), t.getSecond()+1));
                return;
            }
        }
    }
    private void addInfoTupleIntoSet_ingredient(Set<Tuple<Object, Integer>> specificPreferenceData, String specificInfo){
        for(Tuple<Object, Integer> t : specificPreferenceData){
            if(t.getFirst().equals(IngredientType.getInstanceAsString(specificInfo))){
                specificPreferenceData.remove(t);
                specificPreferenceData.add(Tuple.of(IngredientType.getInstanceAsString(specificInfo), t.getSecond()+1));
                return;
            }
        }
    }

    private void addInfoTupleIntoSet_situation(Set<Tuple<Object, Integer>> specificPreferenceData, String specificInfo){
        for(Tuple<Object, Integer> t : specificPreferenceData){
            if(t.getFirst().equals(Situation.getInstanceAsString(specificInfo))){
                specificPreferenceData.remove(t);
                specificPreferenceData.add(Tuple.of(Situation.getInstanceAsString(specificInfo), t.getSecond()+1));
                return;
            }
        }
    }

    private String[] extractSpecificInfo(String info){
        Pattern pattern = Pattern.compile("\\((.*?)\\)");
        Matcher matcher = pattern.matcher(info);
        String str = null;
        while(matcher.find()){
            str = matcher.group(1);
        }

        String[] specificInfo = str.split("%");
        return specificInfo;
    }

    private List readFile(String filePath) {
        ArrayList<String> arrayList = new ArrayList<>();
        File file = new File(filePath);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                arrayList.add(line);
            }
            bufferedReader.close();
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

}


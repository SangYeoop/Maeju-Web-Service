package com.foodwebservice.diet.survey;


import com.foodwebservice.diet.DietType;
import com.foodwebservice.parser.Tuple;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Service
@Slf4j
public class QuestionService {

    private List<DietTypeQuestion> dietTypeQuestions = new ArrayList<>();

    @PostConstruct
    public void initQuestion() throws IOException {
        dietTypeQuestions = getQuestionString().stream().map((value) -> {
            DietTypeQuestion question = new DietTypeQuestion();
            String[] strs = value.split(",");
            question.setQuestion(strs[0]);
            question.setPositive(getPointBySurveyAndDietTypeList(strs[1]));
            question.setGeneral(getPointBySurveyAndDietTypeList(strs[2]));
            question.setNegative(getPointBySurveyAndDietTypeList(strs[3]));
            return question;
        }).collect(Collectors.toList());
    }


    private List<String> getQuestionString() throws IOException{
        ClassPathResource resource = new ClassPathResource("text/survey.txt");
        String data = new String(resource.getInputStream().readAllBytes());
        return List.of(data.split("\n"));
    }

    private List<Tuple<DietType, Integer>> getPointBySurveyAndDietTypeList(String value) {
        return List.of(value.split("&")).stream().map((string) -> {
            String[] spiltValues = string.split("%");
            DietType dietType = DietType.getInstanceAsString(spiltValues[0]);
            try {
                Integer value2 = Integer.parseInt(spiltValues[1]);
                return Tuple.of(dietType, value2);
            }catch (NumberFormatException e){
                Integer value2 = Integer.parseInt(spiltValues[1].split("\r")[0]);
                return Tuple.of(dietType, value2);
            }
        }).collect(Collectors.toList());
    }
}

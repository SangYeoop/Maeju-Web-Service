package com.foodwebservice.diet.survey;

import com.foodwebservice.diet.DietType;
import com.foodwebservice.parser.Tuple;
import lombok.*;

import java.util.List;

@Data
public class Question {

    private String question;

    private List<Tuple<DietType, Integer>> positive;

    private List<Tuple<DietType, Integer>> negative;

    private List<Tuple<DietType, Integer>> general;
}

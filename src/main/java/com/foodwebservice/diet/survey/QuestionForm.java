package com.foodwebservice.diet.survey;

import com.foodwebservice.diet.survey.ResponseDto;
import lombok.Data;

import java.util.List;

@Data
public class QuestionForm {
    private List<ResponseDto> responseDtos;
    private int index;


}

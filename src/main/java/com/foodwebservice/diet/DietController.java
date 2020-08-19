package com.foodwebservice.diet;

import com.foodwebservice.account.Account;
import com.foodwebservice.account.CurrentAccount;
import com.foodwebservice.diet.form.QuestionForm;
import com.foodwebservice.diet.survey.Question;
import com.foodwebservice.diet.survey.QuestionService;
import com.foodwebservice.diet.survey.ResponseDto;
import com.foodwebservice.diet.survey.ResponseType;
import com.foodwebservice.parser.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SessionAttributes("questionForm")
@RequiredArgsConstructor
@Controller
public class DietController {

    private final QuestionService questionService;
    private final DietService dietService;

    @ModelAttribute(name = "questionForm")
    public QuestionForm questionForm() {
        QuestionForm questionForm = new QuestionForm();
        questionForm.setIndex(0);
        questionForm.setResponseDtos(questionService.getQuestions().stream().map((question -> {
            ResponseDto responseDto = new ResponseDto();
            responseDto.setQuestion(question);
            responseDto.setResponseType(ResponseType.NONE);
            return responseDto;
        })).collect(Collectors.toList()));
        return questionForm;
    }

    @GetMapping("/diet")
    public String dietView(@CurrentAccount Account account, Model model) {
        model.addAttribute(account);
        return "diet/diet";
    }

    @GetMapping("/diet/type")
    public String dietTypeForm(@CurrentAccount Account account, Model model, QuestionForm questionForm) {
        model.addAttribute(account);
        return "diet/diet-type";
    }

    @PostMapping("/diet/type")
    public String dietTypeRequest(@CurrentAccount Account account, Model model, QuestionForm questionForm, ResponseType responseType, SessionStatus status, RedirectAttributes redirectAttributes){
        int index = questionForm.getIndex();
        if(questionForm.getResponseDtos().size() != index + 1) {
            questionForm.getResponseDtos().get(index).setResponseType(responseType);
            questionForm.setIndex(index + 1);
            return "redirect:/diet/type";
        }
        Diet diet = dietService.makeDiet(account, questionForm);
        status.setComplete();
        redirectAttributes.addFlashAttribute("message", "당신의 식단 유형은 " + diet.getDietType().getString() + " 입니다.");
        return "redirect:/";
    }

    @GetMapping("/diet/example")
    public String dietExampleView(){
        return "diet/diet-example";
    }
}

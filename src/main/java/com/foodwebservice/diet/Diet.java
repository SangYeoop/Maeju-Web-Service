package com.foodwebservice.diet;

import com.foodwebservice.account.Account;
import com.foodwebservice.diet.form.QuestionForm;
import com.foodwebservice.diet.survey.ResponseType;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor @NoArgsConstructor
@Entity @Getter @Setter
@EqualsAndHashCode(of = "id")
public class Diet {

    @Id @GeneratedValue
    private Long id;

    @OneToOne
    private Account account;

    @Enumerated(EnumType.STRING)
    private DietType dietType;

    private int adventurePoint;

    private int simplePoint;

    private int healthPoint;

    private int reasonablePoint;

    public Diet setPoint(QuestionForm questionForm){
        questionForm.getResponseDtos().forEach(responseDto -> {
            if(responseDto.getResponseType().equals(ResponseType.POSITIVE)){
                responseDto.getQuestion().getPositive().forEach(tuple -> {
                    setValueAsDietType(tuple.getFirst(), tuple.getSecond());
                });
            }
            else if(responseDto.getResponseType().equals(ResponseType.GENERAL)){
                responseDto.getQuestion().getGeneral().forEach(tuple -> {
                    setValueAsDietType(tuple.getFirst(), tuple.getSecond());
                });

            }else if(responseDto.getResponseType().equals(ResponseType.NEGATIVE)){
                responseDto.getQuestion().getNegative().forEach(tuple -> {
                    setValueAsDietType(tuple.getFirst(), tuple.getSecond());
                });
            }
        });
        return this;
    }

    public Diet setDietType(){
        if(adventurePoint >= simplePoint && adventurePoint >= reasonablePoint && adventurePoint >= healthPoint)
            this.dietType = DietType.ADVENTURE;
        else if(simplePoint >= adventurePoint && simplePoint >= reasonablePoint && simplePoint >= healthPoint)
            this.dietType = DietType.SIMPLE;
        else if(healthPoint >= adventurePoint && healthPoint >= reasonablePoint && healthPoint >= simplePoint)
            this.dietType = DietType.SIMPLE;
        else
            this.dietType = DietType.REASONABLE;
        return this;
    }

    private void setValueAsDietType(DietType dietType, Integer value){
        if(dietType.equals(DietType.ADVENTURE))
            this.adventurePoint += value;
        else if(dietType.equals(DietType.HEALTH))
            this.healthPoint += value;
        else if(dietType.equals(DietType.REASONABLE))
            this.reasonablePoint += value;
        else if(dietType.equals(DietType.SIMPLE))
            this.simplePoint += value;
    }
}

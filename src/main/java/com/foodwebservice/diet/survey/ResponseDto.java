package com.foodwebservice.diet.survey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseDto {
    private DietTypeQuestion question;
    private ResponseType responseType;
}

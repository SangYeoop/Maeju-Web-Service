package com.foodwebservice.account.form;

import com.foodwebservice.account.type.GenderType;
import com.foodwebservice.diet.DietType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@Data @AllArgsConstructor @NoArgsConstructor
public class ProfileUpdateForm {

    @NotEmpty
    String name;

    String email;

    @NotNull
    GenderType genderType;

    DietType dietType;
}

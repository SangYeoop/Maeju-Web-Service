package com.foodwebservice.account.form;

import com.foodwebservice.account.type.GenderType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data @AllArgsConstructor @NoArgsConstructor
public class ProfileUpdateForm {

    @NotEmpty
    String name;

    String email;

    @NotNull
    GenderType genderType;
}

package com.foodwebservice.account.form;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class SignUpForm {

    @NotBlank
    private String name;

    @Email @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String confirmPassword;

}

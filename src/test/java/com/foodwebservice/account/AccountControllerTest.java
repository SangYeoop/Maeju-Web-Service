package com.foodwebservice.account;

import com.foodwebservice.infra.MockMvcTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@MockMvcTest
class AccountControllerTest {

    @Autowired
    MockMvc mockMvc;

    @DisplayName("회원 가입 뷰")
    @Test
    public void signUpView() throws Exception{
        mockMvc.perform(get("/sign-up"))
                .andExpect(view().name("sign-up"))
                .andExpect(model().attributeExists("signUpForm"))
                .andExpect(status().isOk());

    }


    @DisplayName("회원 가입 - 성공")
    @Test
    public void signUpPostAndSuccess() throws Exception{
        mockMvc.perform(post("/sign-up")
                .param("email", "test@email.com")
                .param("name", "testname")
                .param("password", "@1234abcd")
                .param("confirmPassword", "@1234abcd")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @DisplayName("회원 가입 - 실패 (패스워드 특수문자 없음)")
    @Test
    public void signUpPostAndFailureNotExistSpecialCharacters() throws Exception{
        mockMvc.perform(post("/sign-up")
                .param("email", "test@email.com")
                .param("name", "testname")
                .param("password", "1234abcd")
                .param("confirmPassword", "1234abcd")
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("sign-up"))
                .andExpect(model().attributeExists("signUpForm"));
    }

    @DisplayName("회원 가입 - 실패 (패스워드 길이 부족)")
    @Test
    public void signUpPostAndFailureNotEnoughLength() throws Exception{
        mockMvc.perform(post("/sign-up")
                .param("email", "test@email.com")
                .param("name", "testname")
                .param("password", "@1234ab")
                .param("confirmPassword", "@1234ab")
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("sign-up"))
                .andExpect(model().attributeExists("signUpForm"));
    }

    @DisplayName("회원 가입 - 실패 (패스워드 불일치)")
    @Test
    public void signUpPostAndFailureNotConfirmPassword() throws Exception{
        mockMvc.perform(post("/sign-up")
                .param("email", "test@email.com")
                .param("name", "testname")
                .param("password", "@1234abc")
                .param("confirmPassword", "@1234abd")
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("sign-up"))
                .andExpect(model().attributeExists("signUpForm"));
    }


    @DisplayName("로그인 뷰")
    @Test
    public void loginView() throws Exception{
        mockMvc.perform(get("/login"))
                .andExpect(view().name("login"))
                .andExpect(status().isOk());
    }
}
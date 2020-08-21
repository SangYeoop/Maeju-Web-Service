package com.foodwebservice.diet;

import com.foodwebservice.infra.MockMvcTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@MockMvcTest
class DietControllerTest {

    @Autowired
    MockMvc mockMvc;

    @DisplayName("식단 유형 예제 뷰")
    @Test
    public void dietExampleView() throws Exception{
        mockMvc.perform(get("/diet/example"))
                .andExpect(status().isOk())
                .andExpect(view().name("diet/diet-example"));
    }
}
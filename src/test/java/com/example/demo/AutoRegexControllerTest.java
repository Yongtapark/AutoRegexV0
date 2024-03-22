package com.example.demo;


import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.autoregex.AutoRegex;
import com.example.demo.autoregex.AutoRegexFactory;
import com.example.demo.autoregex.dto.RequestForAutoRegexDto;
import com.example.demo.autoregex.dto.ResponseForAutoRegexDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@WebMvcTest(AutoRegexController.class)
class AutoRegexControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    AutoRegexService autoRegexService;
    @Test
    @DisplayName("flexible-regex api 테스트")
    void test1() throws Exception {
        //given
        final String STRING_A = "1번출구";
        final String STRING_B = "2번출구";
        RequestForAutoRegexDto request = new RequestForAutoRegexDto(STRING_A, STRING_B);

        String requestBody = objectMapper.writeValueAsString(request);
        AutoRegex regexDefault = new AutoRegexFactory().createRegexDefault();
        String flexibleRegex = regexDefault.createFlexibleRegex(STRING_A, STRING_B);
        ResponseForAutoRegexDto expectDto = new ResponseForAutoRegexDto(flexibleRegex);

        given(autoRegexService.createFlexibleRegex(anyString(),anyString())).willReturn(flexibleRegex);

        //when
        MvcResult result = mockMvc.perform(post("/api/auto-regex/flexible-regex")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        ResponseForAutoRegexDto actualDto = objectMapper.readValue(responseBody,
                ResponseForAutoRegexDto.class);

        //then
        Assertions.assertThat(expectDto).isEqualTo(actualDto);


    }
}
package com.example.demo;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.example.demo.autoregex.exceptions.AutoRegexOverMaxSizeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AutoRegexServiceTest {

    @Autowired
    AutoRegexService autoRegexService;
    @Test
    @DisplayName("AutoRegexFactory 로 bean 등록되었는지 체크 -> 최대 길이(6) 초과 시 예외 발생")
    void createFlexibleRegexCheckForBeanRegistration() {
        final String ID_1 = "banana-13";
        final String ID_2 = "banana-33";

        assertThatThrownBy(()-> autoRegexService.createFlexibleRegex(ID_1, ID_2)).isInstanceOf(
                AutoRegexOverMaxSizeException.class);
    }

    @Test
    @DisplayName("AutoRegexFactory 로 bean 등록되었는지 체크 -> 최대 길이(15) 초과 시 예외 발생")
    void createFixedRegexCheckForBeanRegistration() {
        final String ID_1 = "111-1111-22220-00000";
        final String ID_2 = "000-0000-00000-00000";
        assertThatThrownBy(()-> autoRegexService.createFixedRegex(ID_1, ID_2)).isInstanceOf(
                AutoRegexOverMaxSizeException.class);
    }
}
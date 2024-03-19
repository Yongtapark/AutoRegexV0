package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AutoRegexTest {

    final static String KOREAN_REGEX = "[ㄱ-ㅎㅏ-ㅣ가-힣]";
    final static String ENGLISH_REGEX = "[A-Za-z]";
    final static String NUMBER_REGEX = "\\d+";

    @Test
    @DisplayName("맨 앞 글자가 변할 때, 두 문자열을 비교하여 정규표현식을 생성한다.")
    void test1() {
        final String ID_1 ="1번출구";
        final String ID_2 ="2번출구";
        final String REGEX_EXPECT = NUMBER_REGEX+"번출구";

        String regex = AutoRegex.createFlexibleRegex(ID_1, ID_2);

        Assertions.assertThat(regex).isEqualTo(REGEX_EXPECT);
    }

    @Test
    @DisplayName("중간 글자가 변할 때, 두 문자열을 비교하여 정규표현식을 생성한다.")
    void test2() {
        final String ID_1 ="제 1 광산";
        final String ID_2 ="제 2 광산";
        final String REGEX_EXPECT = "제 "+NUMBER_REGEX+" 광산";

        String regex = AutoRegex.createFlexibleRegex(ID_1, ID_2);

        Assertions.assertThat(regex).isEqualTo(REGEX_EXPECT);
    }

    @Test
    @DisplayName("변하는 글자가 한글일 때, 두 문자열을 비교하여 정규표현식을 생성한다.")
    void test3() {
        final String ID_1 ="가-1";
        final String ID_2 ="나-3";
        final String REGEX_EXPECT = KOREAN_REGEX+"-"+NUMBER_REGEX;

        String regex = AutoRegex.createFlexibleRegex(ID_1, ID_2);

        Assertions.assertThat(regex).isEqualTo(REGEX_EXPECT);
    }
    @Test
    @DisplayName("변하는 글자가 영어일 때, 두 문자열을 비교하여 정규표현식을 생성한다.")
    void test4() {
        final String ID_1 ="a-1";
        final String ID_2 ="b-3";
        final String REGEX_EXPECT = ENGLISH_REGEX+"-"+NUMBER_REGEX;

        String regex = AutoRegex.createFlexibleRegex(ID_1, ID_2);

        Assertions.assertThat(regex).isEqualTo(REGEX_EXPECT);
    }

    @Test
    @DisplayName("변하는 글자가 영어일 때, 두 문자열을 비교하여 정규표현식을 생성한다.")
    @Disabled
    void test5() {
        final String ID_1 ="111-1111-2222";
        final String ID_2 ="000-0000-0000";
        final String REGEX_EXPECT = ENGLISH_REGEX+"-"+NUMBER_REGEX;

        String regex = AutoRegex.createFixedRegex(ID_1, ID_2);

        Assertions.assertThat(regex).isEqualTo(REGEX_EXPECT);
    }
}

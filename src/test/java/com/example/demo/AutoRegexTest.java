package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.example.demo.autoregex.AutoRegex;
import com.example.demo.autoregex.AutoRegexFactory;
import com.example.demo.autoregex.exceptions.AutoRegexDifferentLengthException;
import com.example.demo.autoregex.exceptions.AutoRegexOverMaxSizeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AutoRegexTest {

    final static String KOREAN_REGEX = "[ㄱ-ㅎㅏ-ㅣ가-힣]";
    final static String ENGLISH_REGEX = "[A-Za-z]";
    final static String NUMBERS_REGEX = "\\d+";

    @Test
    @DisplayName("맨 앞 글자가 변할 때, 두 문자열을 비교하여 정규표현식을 생성한다.")
    void test1() {
        final String ID_1 = "1번출구";
        final String ID_2 = "2번출구";
        final String REGEX_EXPECT = NUMBERS_REGEX + "번출구";

        AutoRegexFactory autoRegexFactory = new AutoRegexFactory();
        AutoRegex autoRegex = autoRegexFactory.createRegexDefault();
        String regex = autoRegex.createFlexibleRegex(ID_1, ID_2);

        assertThat(regex).isEqualTo(REGEX_EXPECT);
    }

    @Test
    @DisplayName("중간 글자가 변할 때, 두 문자열을 비교하여 정규표현식을 생성한다.")
    void test2() {
        final String ID_1 = "제 1 광산";
        final String ID_2 = "제 2 광산";
        final String REGEX_EXPECT = "제 " + NUMBERS_REGEX + " 광산";

        AutoRegexFactory autoRegexFactory = new AutoRegexFactory();
        AutoRegex autoRegex = autoRegexFactory.createRegexDefault();
        String regex = autoRegex.createFlexibleRegex(ID_1, ID_2);

        assertThat(regex).isEqualTo(REGEX_EXPECT);
    }

    @Test
    @DisplayName("변하는 글자가 한글일 때, 두 문자열을 비교하여 정규표현식을 생성한다.")
    void test3() {
        final String ID_1 = "가-1";
        final String ID_2 = "나-3";
        final String REGEX_EXPECT = KOREAN_REGEX + "-" + NUMBERS_REGEX;

        AutoRegexFactory autoRegexFactory = new AutoRegexFactory();
        AutoRegex autoRegex = autoRegexFactory.createRegexDefault();
        String regex = autoRegex.createFlexibleRegex(ID_1, ID_2);

        assertThat(regex).isEqualTo(REGEX_EXPECT);
    }


    @Test
    @DisplayName("변하는 글자가 영어일 때, 두 문자열을 비교하여 정규표현식을 생성한다.")
    void test4() {
        final String ID_1 = "a-1";
        final String ID_2 = "b-3";
        final String REGEX_EXPECT = ENGLISH_REGEX + "-" + NUMBERS_REGEX;

        AutoRegexFactory autoRegexFactory = new AutoRegexFactory();
        AutoRegex autoRegex = autoRegexFactory.createRegexDefault();
        String regex = autoRegex.createFlexibleRegex(ID_1, ID_2);

        assertThat(regex).isEqualTo(REGEX_EXPECT);
    }

    @Test
    @DisplayName("변하는 글자가 숫자형식이고, 형태가 일정 할 때, 두 문자열을 비교하여 정규표현식을 생성한다.")
    void test5WithFixedRegex() {
        final String ID_1 = "111-1111-2222";
        final String ID_2 = "000-0000-0000";
        final String REGEX_EXPECT = "\\d\\d\\d-\\d\\d\\d\\d-\\d\\d\\d\\d";

        AutoRegexFactory autoRegexFactory = new AutoRegexFactory();
        AutoRegex autoRegex = autoRegexFactory.createRegexDefault();
        String regex = autoRegex.createFixedRegex(ID_1, ID_2);

        assertThat(regex).isEqualTo(REGEX_EXPECT);
    }

    @Test
    @DisplayName("비교하는 두 문자열의 길이가 다르면 예외를 발생한다.")
    void test6WhenBothLengthAreDifferentFromFlexibleRegex() {
        final String ID_1 = "a-1";
        final String ID_2 = "b-33";
        AutoRegexFactory autoRegexFactory = new AutoRegexFactory();
        AutoRegex autoRegex = autoRegexFactory.createRegexDefault();

        assertThatThrownBy(()-> autoRegex.createFlexibleRegex(ID_1, ID_2)).isInstanceOf(
                AutoRegexDifferentLengthException.class);
    }

    @Test
    @DisplayName("비교하는 두 문자열의 길이가 다르면 예외를 발생한다.")
    void test6WhenBothLengthAreDifferentFromFixedRegex() {
        final String ID_1 = "111-1111-2222";
        final String ID_2 = "000-0000-00000";
        AutoRegexFactory autoRegexFactory = new AutoRegexFactory();
        AutoRegex autoRegex = autoRegexFactory.createRegexDefault();

        assertThatThrownBy(()-> autoRegex.createFixedRegex(ID_1, ID_2)).isInstanceOf(
                AutoRegexDifferentLengthException.class);
    }

    @Test
    @DisplayName("두 문자의 길이가 최대 길이제한을 넘으면 예외를 발생한다.")
    void test7WhenFlexibleRegexOverLength() {
        final String ID_1 = "banana-13";
        final String ID_2 = "banana-33";
        AutoRegexFactory autoRegexFactory = new AutoRegexFactory();
        AutoRegex autoRegex = autoRegexFactory.createRegexDefault();

        assertThatThrownBy(()-> autoRegex.createFlexibleRegex(ID_1, ID_2)).isInstanceOf(
                AutoRegexOverMaxSizeException.class);
    }

    @Test
    @DisplayName("두 문자의 길이가 최대 길이제한을 넘으면 예외를 발생한다.")
    void test7WhenFixedRegexOverLength() {
        final String ID_1 = "111-1111-22220-00000";
        final String ID_2 = "000-0000-00000-00000";
        AutoRegexFactory autoRegexFactory = new AutoRegexFactory();
        AutoRegex autoRegex = autoRegexFactory.createRegexDefault();

        assertThatThrownBy(()-> autoRegex.createFixedRegex(ID_1, ID_2)).isInstanceOf(
                AutoRegexOverMaxSizeException.class);
    }
}

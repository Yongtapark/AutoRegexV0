package com.example.demo;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class AutoRegex {
    final static String KOREAN_REGEX = "[ㄱ-ㅎㅏ-ㅣ가-힣]";
    final static String ENGLISH_REGEX = "[A-Za-z]";
    final static String NUMBERS_REGEX = "\\d+";
    final static String NUMBER_REGEX = "\\d";

    public static String createFlexibleRegex(String minContext, String maxContext) {

        int minLength = minContext.length();
        int maxLength = maxContext.length();
        ArrayList<Integer> sameStringIndexList = new ArrayList<>();
        ArrayList<Integer> differentStringIndexList = new ArrayList<>();
        // 1.두 문자열 길이 체크
        if (minLength == maxLength) {
            //1-1 문자열 중 동일한 index를 list에 저장, 아닌 경우엔 틀린 list에 저장
            char[] minContextCharArray = minContext.toCharArray();
            char[] maxContextCharArray = maxContext.toCharArray();
            for (int i = 0; i < minContext.length(); i++) {
                if (minContextCharArray[i] == maxContextCharArray[i]) {
                    sameStringIndexList.add(i);
                } else {
                    differentStringIndexList.add(i);
                }
            }
        } else {
            //문자열 길이가 동일하지 않을 때
            // 예외처리

        }

        String[] tmp = new String[maxContext.length()];
        for (int i = 0; i < differentStringIndexList.size(); i++) {
            int index = differentStringIndexList.get(i);
            try {
                Integer.parseInt(String.valueOf(minContext.charAt(index)));

                tmp[index] = NUMBERS_REGEX;

            } catch (NumberFormatException e) {
                if (isKorean(maxContext, index)) {

                    tmp[index] = KOREAN_REGEX;
                } else if (isEnglish(maxContext, index)) {
                    tmp[index] = ENGLISH_REGEX;
                }
            }
        }

        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i] == null) {
                tmp[i] = String.valueOf(maxContext.charAt(i));
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tmp.length; i++) {
            sb.append(tmp[i]);
        }

        return sb.toString();
    }

    public static String createFixedRegex(String minContext, String maxContext) {

        int minLength = minContext.length();
        int maxLength = maxContext.length();
        ArrayList<Integer> sameStringIndexList = new ArrayList<>();
        ArrayList<Integer> differentStringIndexList = new ArrayList<>();
        // 1.두 문자열 길이 체크
        if (minLength == maxLength && minLength<=15) {
            //1-1 문자열 중 동일한 index를 list에 저장, 아닌 경우엔 틀린 list에 저장
            char[] minContextCharArray = minContext.toCharArray();
            char[] maxContextCharArray = maxContext.toCharArray();
            for (int i = 0; i < minContext.length(); i++) {
                if (minContextCharArray[i] == maxContextCharArray[i]) {
                    sameStringIndexList.add(i);
                } else {
                    differentStringIndexList.add(i);
                }
            }
        } else {
            //문자열 길이가 동일하지 않을 때
            // 예외처리

        }

        String[] tmp = new String[maxContext.length()];
        for (int i = 0; i < differentStringIndexList.size(); i++) {
            int index = differentStringIndexList.get(i);
            try {
                Integer.parseInt(String.valueOf(minContext.charAt(index)));

                tmp[index] = NUMBER_REGEX;

            } catch (NumberFormatException e) {
                if (isKorean(maxContext, index)) {

                    tmp[index] = KOREAN_REGEX;
                } else if (isEnglish(maxContext, index)) {
                    tmp[index] = ENGLISH_REGEX;
                }
            }
        }

        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i] == null) {
                tmp[i] = String.valueOf(maxContext.charAt(i));
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tmp.length; i++) {
            sb.append(tmp[i]);
        }

        return sb.toString();
    }

    private static boolean isKorean(String maxContext, int index) {
        return Pattern.compile(KOREAN_REGEX).matcher(String.valueOf(maxContext.charAt(index))).matches();
    }

    private static boolean isEnglish(String maxContext, int index) {

        return Pattern.compile(ENGLISH_REGEX).matcher(String.valueOf(maxContext.charAt(index))).matches();
    }
}

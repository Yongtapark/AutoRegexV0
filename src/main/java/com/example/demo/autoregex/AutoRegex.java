package com.example.demo.autoregex;

import com.example.demo.autoregex.exceptions.AutoRegexDifferentLengthException;
import com.example.demo.autoregex.exceptions.AutoRegexOverMaxSizeException;
import java.util.ArrayList;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;

@Component
public class AutoRegex {
    private int flexibleMaxSize = Integer.MAX_VALUE;
    private int fixedMaxSize = Integer.MAX_VALUE;
    final static String KOREAN_REGEX = "[ㄱ-ㅎㅏ-ㅣ가-힣]";
    final static String ENGLISH_REGEX = "[A-Za-z]";
    final static String NUMBERS_REGEX = "\\d+";
    final static String NUMBER_REGEX = "\\d";

    public AutoRegex() {
    }

    public AutoRegex(int flexibleMaxSize, int fixedMaxSize) {
        this.flexibleMaxSize = flexibleMaxSize;
        this.fixedMaxSize = fixedMaxSize;
    }

    public String createFlexibleRegex(String stringA, String stringB) {

        int lengthA = stringA.length();
        int lengthB = stringB.length();
        ArrayList<Integer> matchingIndexList = new ArrayList<>();
        ArrayList<Integer> mismatchingIndexList = new ArrayList<>();
        if (lengthA != lengthB) {
            throw new AutoRegexDifferentLengthException(stringB);
        }

        if (lengthB > flexibleMaxSize) {
            throw new AutoRegexOverMaxSizeException(stringB);
        }

        char[] stringACharArray = stringA.toCharArray();
        char[] stringBCharArray = stringB.toCharArray();
        for (int i = 0; i < stringA.length(); i++) {
            if (stringACharArray[i] == stringBCharArray[i]) {
                matchingIndexList.add(i);
            } else {
                mismatchingIndexList.add(i);
            }
        }

        String[] regexParts = new String[stringB.length()];
        for (int i = 0; i < mismatchingIndexList.size(); i++) {
            int index = mismatchingIndexList.get(i);
            try {
                Integer.parseInt(String.valueOf(stringA.charAt(index)));

                regexParts[index] = NUMBERS_REGEX;

            } catch (NumberFormatException e) {
                if (isKorean(stringB, index)) {

                    regexParts[index] = KOREAN_REGEX;
                } else if (isEnglish(stringB, index)) {
                    regexParts[index] = ENGLISH_REGEX;
                }
            }
        }

        for (int i = 0; i < regexParts.length; i++) {
            if (regexParts[i] == null) {
                regexParts[i] = String.valueOf(stringB.charAt(i));
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < regexParts.length; i++) {
            sb.append(regexParts[i]);
        }

        return sb.toString();
    }

    public String createFixedRegex(String stringA, String stringB) {

        int lengthA = stringA.length();
        int lengthB = stringB.length();
        ArrayList<Integer> matchingIndexList = new ArrayList<>();
        ArrayList<Integer> mismatchingIndexList = new ArrayList<>();
        if (lengthA != lengthB) {
            throw new AutoRegexDifferentLengthException(stringB);
        }
        if (lengthB > fixedMaxSize) {
            throw new AutoRegexOverMaxSizeException(stringB);
        }

        char[] stringACharArray = stringA.toCharArray();
        char[] stringBCharArray = stringB.toCharArray();
        for (int i = 0; i < stringA.length(); i++) {
            if (stringACharArray[i] == stringBCharArray[i]) {
                matchingIndexList.add(i);
            } else {
                mismatchingIndexList.add(i);
            }
        }

        String[] regexParts = new String[stringB.length()];
        for (int i = 0; i < mismatchingIndexList.size(); i++) {
            int index = mismatchingIndexList.get(i);
            try {
                Integer.parseInt(String.valueOf(stringA.charAt(index)));

                regexParts[index] = NUMBER_REGEX;

            } catch (NumberFormatException e) {
                if (isKorean(stringB, index)) {
                    regexParts[index] = KOREAN_REGEX;
                } else if (isEnglish(stringB, index)) {
                    regexParts[index] = ENGLISH_REGEX;
                }
            }
        }

        for (int i = 0; i < regexParts.length; i++) {
            if (regexParts[i] == null) {
                regexParts[i] = String.valueOf(stringB.charAt(i));
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < regexParts.length; i++) {
            sb.append(regexParts[i]);
        }

        return sb.toString();
    }

    private boolean isKorean(String context, int index) {
        return Pattern.compile(KOREAN_REGEX).matcher(String.valueOf(context.charAt(index))).matches();
    }

    private boolean isEnglish(String context, int index) {

        return Pattern.compile(ENGLISH_REGEX).matcher(String.valueOf(context.charAt(index))).matches();
    }
}

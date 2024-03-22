package com.example.demo.autoregex.exceptions;

public class AutoRegexOverMaxSizeException extends AutoRegexException{
    private final static String DEFAULT_MESSAGE = "최대 문자열 길이를 초과하였습니다. = ";
    public AutoRegexOverMaxSizeException(String s) {
        super(DEFAULT_MESSAGE+s);
    }
}

package com.example.demo.autoregex.exceptions;

public class AutoRegexDifferentLengthException extends AutoRegexException{
    private final static String DEFAULT_MESSAGE = "두 문자열의 길이는 동일해야 합니다. = ";
    public AutoRegexDifferentLengthException(String s) {
        super(DEFAULT_MESSAGE+s);
    }
}

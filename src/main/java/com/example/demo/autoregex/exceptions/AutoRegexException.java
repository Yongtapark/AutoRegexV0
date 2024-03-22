package com.example.demo.autoregex.exceptions;

public class AutoRegexException extends IllegalArgumentException{
    public AutoRegexException(String s) {
        super("[ERROR] "+ s);
    }
}

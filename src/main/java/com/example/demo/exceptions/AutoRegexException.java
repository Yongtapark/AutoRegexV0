package com.example.demo.exceptions;

public class AutoRegexException extends IllegalArgumentException{
    public AutoRegexException(String s) {
        super("[ERROR] "+ s);
    }
}

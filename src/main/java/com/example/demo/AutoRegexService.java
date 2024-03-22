package com.example.demo;

import com.example.demo.autoregex.AutoRegex;


public class AutoRegexService {
    private final AutoRegex autoRegex;

    public AutoRegexService(AutoRegex autoRegex) {
        this.autoRegex = autoRegex;
    }

    public String createFlexibleRegex(String stringA, String stringB){
        return autoRegex.createFlexibleRegex(stringA,stringB);
    }

    public String createFixedRegex(String stringA, String stringB){
        return autoRegex.createFixedRegex(stringA,stringB);
    }
}

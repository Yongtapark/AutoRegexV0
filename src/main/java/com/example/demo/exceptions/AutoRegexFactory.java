package com.example.demo.exceptions;

import com.example.demo.AutoRegex;

public class AutoRegexFactory {
    final static int defaultFlexibleSize = 6;
    final static int defaultFixedSize = 15;
    public AutoRegex createRegexDefault(){
        return new AutoRegex(defaultFlexibleSize,defaultFixedSize);
    }
}

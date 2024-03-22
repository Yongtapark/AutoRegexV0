package com.example.demo;

import com.example.demo.autoregex.AutoRegexFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutoRegexConfig {

    @Bean
    public AutoRegexService autoRegexService() {
        AutoRegexFactory factory = new AutoRegexFactory();
        return new AutoRegexService(factory.createRegexDefault());
    }
}

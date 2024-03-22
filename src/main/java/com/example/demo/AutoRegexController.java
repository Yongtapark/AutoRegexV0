package com.example.demo;

import com.example.demo.autoregex.dto.RequestForAutoRegexDto;
import com.example.demo.autoregex.dto.ResponseForAutoRegexDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auto-regex/")
public class AutoRegexController {
    private final AutoRegexService autoRegexService;

    public AutoRegexController(AutoRegexService autoRegexService) {
        this.autoRegexService = autoRegexService;
    }

    @PostMapping("flexible-regex")
    public ResponseEntity<ResponseForAutoRegexDto> flexibleRegex(@RequestBody RequestForAutoRegexDto requestForAutoRegexDto) {
        String flexibleRegex = autoRegexService.createFlexibleRegex(requestForAutoRegexDto.stringA(),
                requestForAutoRegexDto.stringB());
        return ResponseEntity.ok(new ResponseForAutoRegexDto(flexibleRegex));
    }
}

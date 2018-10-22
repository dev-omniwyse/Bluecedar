package com.bluecedar.analyzer.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bluecedar.analyzer.security.TokenGenerator;
import com.bluecedar.analyzer.security.model.Device;

@RestController
@RequestMapping("/token")
public class TokenController {


    private TokenGenerator tokenGenerator;

    public TokenController(TokenGenerator jwtGenerator) {
        this.tokenGenerator = jwtGenerator;
    }

    @PostMapping
    public String generate(@RequestBody final Device jwtUser) {

        return tokenGenerator.generate(jwtUser);

    }
}

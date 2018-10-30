package com.bluecedar.analyzer.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bluecedar.analyzer.security.TokenGenerator;
import com.bluecedar.analyzer.security.model.Device;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author Ramu Enugala
 *
 */

@RestController
@RequestMapping("/token")
public class TokenController {


    private TokenGenerator tokenGenerator;

    public TokenController(TokenGenerator jwtGenerator) {
        this.tokenGenerator = jwtGenerator;
    }

    @PostMapping( consumes = "application/json" )
    @ApiOperation(value = "Genetrate AUTH-TOKEN",
		notes = "This endpoint will generate authentication token for given device uniqueID ")
    public String generate(@Valid @RequestBody final Device device) {

        return tokenGenerator.generate(device);

    }
}

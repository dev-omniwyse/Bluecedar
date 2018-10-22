package com.bluecedar.analyzer.security;

import java.sql.Date;

import org.springframework.stereotype.Component;

import com.bluecedar.analyzer.security.model.Device;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenGenerator {

	private String secretKey = "bluecedar-omniwyse";
	
    public String generate(Device jwtUser) {


        Claims claims = Jwts.claims()
                .setSubject(jwtUser.getDeviceUID());
        claims.put("deviceUID", String.valueOf(jwtUser.getDeviceUID()));


        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + 300000))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }
}

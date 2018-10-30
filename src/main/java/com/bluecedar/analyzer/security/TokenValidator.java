package com.bluecedar.analyzer.security;

import org.springframework.stereotype.Component;

import com.bluecedar.analyzer.security.model.Device;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
/**
 * 
 * @author Ramu Enugala
 *
 */
@Component
public class TokenValidator {


    private String secretKey = "bluecedar-omniwyse";

    public Device validate(String token) {

        Device jwtUser = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();

            jwtUser = new Device();

            jwtUser.setDeviceUID(body.getSubject());
        }
        catch (Exception e) {
            System.out.println(e);
        }

        return jwtUser;
    }
}

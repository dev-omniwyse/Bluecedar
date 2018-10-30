package com.bluecedar.analyzer.security.model;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
/**
 * 
 * @author Ramu Enugala
 *
 */
public class AuthenticationToken extends UsernamePasswordAuthenticationToken{

    private String token;

    public AuthenticationToken(String token) {
        super(null, null);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}

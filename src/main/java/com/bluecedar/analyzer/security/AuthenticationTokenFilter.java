package com.bluecedar.analyzer.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.bluecedar.analyzer.security.model.AuthenticationToken;

/**
 * 
 * @author Ramu Enugala
 *
 */
public class AuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

    public AuthenticationTokenFilter() {
    	 super("/analyzer/**");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {

        String header = httpServletRequest.getHeader("AUTH-TOKEN");


        if (header == null || !header.startsWith("Token-")) {
            throw new RuntimeException("AUTH-TOKEN is missing");
        }

        String authenticationToken = header.substring(6);

        AuthenticationToken token = new AuthenticationToken(authenticationToken);
        return getAuthenticationManager().authenticate(token);
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }
}

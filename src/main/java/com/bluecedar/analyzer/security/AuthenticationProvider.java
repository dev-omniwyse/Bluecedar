package com.bluecedar.analyzer.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.bluecedar.analyzer.security.model.AuthenticationToken;
import com.bluecedar.analyzer.security.model.Device;
import com.bluecedar.analyzer.security.model.DeviceDetails;

/**
 * 
 * @author Ramu Enugala
 *
 */
@Component
public class AuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private TokenValidator validator;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

        AuthenticationToken jwtAuthenticationToken = (AuthenticationToken) usernamePasswordAuthenticationToken;
        String token = jwtAuthenticationToken.getToken();

        Device jwtUser = validator.validate(token);

        if (jwtUser == null) {
            throw new RuntimeException("AUTH-TOKEN is incorrect");
        }

        return new DeviceDetails(jwtUser.getDeviceUID(),token);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return (AuthenticationToken.class.isAssignableFrom(aClass));
    }
}

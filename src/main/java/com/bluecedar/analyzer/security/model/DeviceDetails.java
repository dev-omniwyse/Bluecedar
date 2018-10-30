package com.bluecedar.analyzer.security.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
/**
 * 
 * @author Ramu Enugala
 *
 */
public class DeviceDetails implements UserDetails {

    private String deviceUID;
    private String token;


    public DeviceDetails(String deviceUID, String token ) {

    	this.deviceUID = deviceUID;
        this.token = token;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return deviceUID;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public String getUserName() {
        return deviceUID;
    }

    public String getToken() {
        return token;
    }

}

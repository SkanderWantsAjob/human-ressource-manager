package com.HRM.HRmanager.authentication;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

public class RobotAuthentication implements Authentication{

    @Override
    public String getName() {
        return "ROBOT AUTHENTICATION";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("ROLE_robot");
    }

    @Override
    public Object getCredentials() {
        return AuthorityUtils.createAuthorityList("ROLE_robot");
    }

    @Override
    public Object getDetails() {
      return null;
    }

    @Override
    public Object getPrincipal() {
        return getName();
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        throw new IllegalArgumentException("Unimplemented method 'setAuthenticated'");
    }

}

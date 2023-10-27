package com.hackathon.dronedelivery.util.auth;


import com.hackathon.dronedelivery.model.User;
import com.hackathon.dronedelivery.service.AuthService;
import com.hackathon.dronedelivery.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class AuthProvider implements AuthenticationProvider {
    AuthService authService;
    UsersService usersService;

    public AuthProvider(@Autowired AuthService authService, @Autowired UsersService usersService) {
        this.authService = authService;
        this.usersService = usersService;
    }

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        try{
            String name = authentication.getName();
            String password = authentication.getCredentials().toString();
            User user = usersService.getUser(name);
            var grantedAuthority = user.getAuthorities();
            if(grantedAuthority!=null) {
                return new UsernamePasswordAuthenticationToken(
                        name, password, grantedAuthority);
            }
            else{
                return null;
            }
        }
        catch (AuthenticationException e){
            return null;
        }
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
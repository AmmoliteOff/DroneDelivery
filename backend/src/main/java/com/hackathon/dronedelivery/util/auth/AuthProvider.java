package com.hackathon.dronedelivery.util.auth;


import com.hackathon.dronedelivery.model.User;
import com.hackathon.dronedelivery.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthProvider implements AuthenticationProvider {

    private final UserService userService;


    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        try{
            String name = authentication.getName();
            String password = authentication.getCredentials().toString();
            User user = userService.getUser(name);
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
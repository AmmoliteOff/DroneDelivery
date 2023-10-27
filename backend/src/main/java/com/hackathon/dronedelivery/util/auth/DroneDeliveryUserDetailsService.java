package com.hackathon.dronedelivery.util.auth;

//public class AccountingUserDetailsService {
//}

import com.hackathon.dronedelivery.model.User;
import com.hackathon.dronedelivery.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class DroneDeliveryUserDetailsService implements UserDetailsService {
    @Autowired
    private UsersService usersService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = usersService.getUser(login);
        if (user == null) {
            throw new UsernameNotFoundException(login);
        }
        return new DroneDeliveryUserPrincipal(user);
    }
}
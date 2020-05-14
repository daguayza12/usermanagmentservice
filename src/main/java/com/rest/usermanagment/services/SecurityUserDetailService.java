package com.rest.usermanagment.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SecurityUserDetailService implements UserDetailsService {
    @Autowired
    private UserService userService;

    /**
     *  Method used to return a valid user before creating token
      * @param username
     * @return User
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.rest.usermanagment.models.User user = userService.findByEmail(username);
        return new User(user.getEmail(), "pass",
                new ArrayList<>());
    }
}

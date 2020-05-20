package com.rest.usermanagment.controllers;

import com.rest.usermanagment.models.AuthRequest;
import com.rest.usermanagment.models.AuthResponse;
import com.rest.usermanagment.models.User;
import com.rest.usermanagment.security.util.JwtTokenUtil;
import com.rest.usermanagment.services.IUserService;
import com.rest.usermanagment.services.SecurityUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class JwtAuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private SecurityUserDetailService userDetailService;

    @Autowired
    private IUserService<User> userService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    /**
     * Authenticates user with Springs authentication manager
     * Once user is authenticated a token is created and returned back to client
     * @param authRequest
     * @return
     * @throws Exception
     */
    @PostMapping("/auth")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), "pass")
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        // creates instance of userdetail needed when generating a token
        final UserDetails userDetails = userDetailService
                .loadUserByUsername(authRequest.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    /**
     * Http mapping for retrieving user information after token is created
     * @param email
     * @return user
     */
    @GetMapping("/auth/{email}")
    public User getLoggedInUser(@PathVariable String email)  {
        return userService.findByEmail(email);
    }
}

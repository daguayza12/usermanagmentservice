package com.rest.usermanagment.controllers;

import com.rest.usermanagment.models.AuthRequest;
import com.rest.usermanagment.models.AuthResponse;
import com.rest.usermanagment.models.User;
import com.rest.usermanagment.security.util.JwtTokenUtil;
import com.rest.usermanagment.services.IQueryService;
import com.rest.usermanagment.services.SecurityUserDetailService;
import com.rest.usermanagment.services.UserService;
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
    private IQueryService<User> userService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
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
        final UserDetails userDetails = userDetailService
                .loadUserByUsername(authRequest.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(token));
    }
    @GetMapping("/auth/{email}")
    public User getLoggedInUser(@PathVariable String email)  {
        return userService.findByEmail(email);
    }

}

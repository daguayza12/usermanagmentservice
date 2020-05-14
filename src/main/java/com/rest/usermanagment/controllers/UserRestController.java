package com.rest.usermanagment.controllers;

import com.rest.usermanagment.models.User;
import com.rest.usermanagment.converters.UserEntityToUser;
import com.rest.usermanagment.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class UserRestController {
    @Autowired
    private IService<User> userService;
    @Autowired
    private UserEntityToUser userEntityToUser;

    @GetMapping("/users")
    public Set<User> getUsers(){
        return userService.findAll();
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user) throws Exception {
        return userService.save(user);
    }

    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable int userId){
        userService.deleteById(userId);
        return "Deleted user id - " + userId;
    }


}

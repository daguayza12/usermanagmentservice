package com.rest.usermanagment.controllers;

import com.rest.usermanagment.models.User;

import com.rest.usermanagment.services.ICrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class UserRestController {
    @Autowired
    private ICrudService<User> userService;

    @GetMapping("/users")
    public Set<User> getUsers(){

        return userService.findAll();
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
        return userService.saveOrUpdate(user);
    }
    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable long userId){
        userService.deleteById(userId);
        return "Deleted user id - " + userId;
    }


}

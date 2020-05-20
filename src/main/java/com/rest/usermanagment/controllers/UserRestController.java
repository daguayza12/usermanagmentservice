package com.rest.usermanagment.controllers;

import com.rest.usermanagment.models.User;

import com.rest.usermanagment.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class UserRestController {
    @Autowired
    private IUserService<User> userService;

    /**
     * Http mapping for getting all users
     * @return
     */
    @GetMapping("/users")
    public Set<User> getUsers(){

        return userService.findAll();
    }

    /**
     * Http mapping for adding user
     * @param user
     * @return
     */
    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
        return userService.saveOrUpdate(user);
    }

    /**
     * Http mapping for deleting user by Id
     * @param userId
     * @return
     */
    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable long userId){
        userService.deleteById(userId);
        return "Deleted user id - " + userId;
    }


}

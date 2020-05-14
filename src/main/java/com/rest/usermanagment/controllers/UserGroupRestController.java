package com.rest.usermanagment.controllers;

import com.rest.usermanagment.models.UserGroup;
import com.rest.usermanagment.services.ICrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class UserGroupRestController {
    @Autowired
    private ICrudService<UserGroup> userGroupService;

    @GetMapping("/groups")
    public Set<UserGroup> addUser(){
        return userGroupService.findAll();
    }
    @PostMapping("/groups")
    public UserGroup addGroup(@RequestBody UserGroup userGroup) throws Exception {
       return userGroupService.save(userGroup);
    }

    @DeleteMapping("/groups/{groupId}")
    public String deleteGroup(@PathVariable int groupId){
        userGroupService.deleteById(groupId);
        return "Deleted user id - " + groupId;
    }

}

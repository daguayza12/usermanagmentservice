package com.rest.usermanagment.controllers;

import com.rest.usermanagment.models.Group;
import com.rest.usermanagment.services.ICrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class GroupRestController {
    @Autowired
    private ICrudService<Group> userGroupService;

    @GetMapping("/groups")
    public Set<Group> addUser(){
        return userGroupService.findAll();
    }
    @PostMapping("/groups")
    public Group addGroup(@RequestBody Group group) throws Exception {
       return userGroupService.save(group);
    }
    @DeleteMapping("/groups/{groupId}")
    public String deleteGroup(@PathVariable long groupId){
        userGroupService.deleteById(groupId);
        return "Deleted user id - " + groupId;
    }
    @GetMapping("/groups/{groupId}")
    public Group getGroupById(@PathVariable long groupId){
        return userGroupService.findById(groupId);
    }
}

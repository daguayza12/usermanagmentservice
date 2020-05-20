package com.rest.usermanagment.controllers;

import com.rest.usermanagment.models.Group;
import com.rest.usermanagment.services.ICrudService;
import com.rest.usermanagment.services.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;



@RestController
@RequestMapping("/api")
public class GroupRestController {
    @Autowired
    private IGroupService<Group> userGroupService;

    /**
     * Http mapping for returning all Groups
     * @return groups
     */
    @GetMapping("/groups")
    public Set<Group> getGroups(){
        return userGroupService.findAll();
    }

    /**
     * Http mapping for adding group
     * @param group
     * @return group
     */
    @PostMapping("/groups")
    public Group addGroup(@RequestBody Group group)   {
       return userGroupService.saveOrUpdate(group);
    }

    /**
     * Http mapping for deleting a group by groupId
     * @param groupId
     * @return String
     */
    @DeleteMapping("/groups/{groupId}")
    public String deleteGroup(@PathVariable long groupId){
        userGroupService.deleteById(groupId);
        return "Deleted user id - " + groupId;
    }

    /**
     * Http mapping for getting group by Id
     * @param groupId
     * @return group
     */
    @GetMapping("/groups/{groupId}")
    public Group getGroupById(@PathVariable long groupId){
        return userGroupService.findById(groupId);
    }
}

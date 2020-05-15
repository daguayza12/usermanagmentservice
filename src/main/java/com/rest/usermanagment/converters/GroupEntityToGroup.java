package com.rest.usermanagment.converters;

import com.rest.usermanagment.models.User;
import com.rest.usermanagment.models.Group;
import com.rest.usermanagment.entities.UserEntity;
import com.rest.usermanagment.entities.GroupEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Converts Group entity object to group model object that is used by client to display data
 */
@Component
public class GroupEntityToGroup implements Converter<GroupEntity, Group> {

    @Autowired
    private UserEntityToUser userEntityToUser;

    @Override
    public Group convert(GroupEntity groupEntity) {
        Group group = new Group();
        group.setGroupName(groupEntity.getGroupName());
        group.setGroupId(groupEntity.getGroupId());
        if(groupEntity.getUsersEntity()!= null){
            Set<User> userSet = new LinkedHashSet<>();
            for(UserEntity userEntity : groupEntity.getUsersEntity()){
                userSet.add(userEntityToUser.convert(userEntity));
            }
            group.setUsers(userSet);
        }
        return group;
    }
}

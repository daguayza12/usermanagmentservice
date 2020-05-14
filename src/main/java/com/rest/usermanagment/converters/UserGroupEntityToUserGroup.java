package com.rest.usermanagment.converters;

import com.rest.usermanagment.models.User;
import com.rest.usermanagment.models.UserGroup;
import com.rest.usermanagment.entities.UserEntity;
import com.rest.usermanagment.entities.UserGroupEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

@Component
public class UserGroupEntityToUserGroup implements Converter<UserGroupEntity, UserGroup> {

    @Autowired
    private UserEntityToUser userEntityToUser;

    @Override
    public UserGroup convert(UserGroupEntity userGroupEntity) {
        UserGroup userGroup = new UserGroup();
        userGroup.setGroupName(userGroupEntity.getGroupName());
        userGroup.setGroupId(userGroupEntity.getGroupId());
        if(userGroupEntity.getUsersEntity()!= null){
            Set<User> userSet = new LinkedHashSet<>();
            for(UserEntity userEntity : userGroupEntity.getUsersEntity()){
                userSet.add(userEntityToUser.convert(userEntity));
            }
            userGroup.setUsers(userSet);
        }
        return userGroup;
    }
}

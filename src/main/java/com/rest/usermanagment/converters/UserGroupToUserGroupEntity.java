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
public class UserGroupToUserGroupEntity implements Converter<UserGroup, UserGroupEntity> {
    @Autowired
    private UserToUserEntity userToUserEntity;
    @Override
    public UserGroupEntity convert(UserGroup userGroup) {
        UserGroupEntity userGroupEntity = new UserGroupEntity();
        userGroupEntity.setGroupName(userGroup.getGroupName());
        userGroupEntity.setGroupId(userGroup.getGroupId());
        if(userGroup.getUsers() !=null){
            Set<UserEntity> userEntitySet = new LinkedHashSet<>();
            for(User user : userGroup.getUsers()){
                userEntitySet.add(userToUserEntity.convert(user));
            }
            userGroupEntity.setUsersEntity(userEntitySet);
        }
        return userGroupEntity;

    }
}

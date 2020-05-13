package com.rest.usermanagment.converters;

import com.rest.usermanagment.payload.User;
import com.rest.usermanagment.entities.UserEntity;
import com.rest.usermanagment.entities.UserGroupEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserEntity implements Converter<User, UserEntity> {
    @Override
    public UserEntity convert(User user) {
        UserEntity userEntity = new UserEntity();

        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setEmail(user.getEmail());
        userEntity.setUserId(user.getUserId());
        userEntity.setUserRole(user.getUserRole());
        if(user.getGroupId() !=0) {
            UserGroupEntity usergroupEntity = new UserGroupEntity();
            usergroupEntity.setGroupId(user.getGroupId());
            usergroupEntity.setGroupName(user.getGroupName());
            userEntity.setUserGroupEntity(usergroupEntity);
        }
        return userEntity;
    }
}

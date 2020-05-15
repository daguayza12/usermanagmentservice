package com.rest.usermanagment.converters;

import com.rest.usermanagment.models.User;
import com.rest.usermanagment.entities.UserEntity;
import com.rest.usermanagment.entities.GroupEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
/**
 * Converts model object to user entity object which is used by data layer
 */
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
            GroupEntity usergroupEntity = new GroupEntity();
            usergroupEntity.setGroupId(user.getGroupId());
            usergroupEntity.setGroupName(user.getGroupName());
            userEntity.setGroupEntity(usergroupEntity);
        }
        return userEntity;
    }
}

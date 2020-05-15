package com.rest.usermanagment.converters;

import com.rest.usermanagment.models.User;
import com.rest.usermanagment.entities.UserEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserEntityToUser implements Converter<UserEntity, User> {


    @Override
    public User convert(UserEntity userEntity) {
        User user = new User();
        user.setFirstName(userEntity.getFirstName());
        user.setLastName(userEntity.getLastName());
        user.setEmail(userEntity.getEmail());
        user.setUserId(userEntity.getUserId());
        user.setUserRole(userEntity.getUserRole());
        if(userEntity.getGroupEntity() !=null) {
            user.setGroupId(userEntity.getGroupEntity().getGroupId());
            user.setGroupName(userEntity.getGroupEntity().getGroupName());
        }
        return user;
    }
}

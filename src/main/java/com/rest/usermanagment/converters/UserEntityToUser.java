package com.rest.usermanagment.converters;

import com.rest.usermanagment.payload.User;
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
        if(userEntity.getUserGroupEntity() !=null) {
            user.setGroupId(userEntity.getUserGroupEntity().getGroupId());
            user.setGroupName(userEntity.getUserGroupEntity().getGroupName());
        }
        return user;
    }
}

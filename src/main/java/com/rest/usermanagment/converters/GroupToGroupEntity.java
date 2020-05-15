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

@Component
public class GroupToGroupEntity implements Converter<Group, GroupEntity> {
    @Autowired
    private UserToUserEntity userToUserEntity;
    @Override
    public GroupEntity convert(Group group) {
        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setGroupName(group.getGroupName());
        groupEntity.setGroupId(group.getGroupId());
        if(group.getUsers() !=null){
            Set<UserEntity> userEntitySet = new LinkedHashSet<>();
            for(User user : group.getUsers()){
                userEntitySet.add(userToUserEntity.convert(user));
            }
            groupEntity.setUsersEntity(userEntitySet);
        }
        return groupEntity;

    }
}

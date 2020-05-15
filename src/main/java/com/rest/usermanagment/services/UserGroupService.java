package com.rest.usermanagment.services;

import com.rest.usermanagment.converters.UserGroupEntityToUserGroup;
import com.rest.usermanagment.converters.UserGroupToUserGroupEntity;
import com.rest.usermanagment.exceptions.DuplicateUserGroupException;
import com.rest.usermanagment.models.UserGroup;
import com.rest.usermanagment.entities.UserGroupEntity;
import com.rest.usermanagment.exceptions.UserGroupNotFoundException;
import com.rest.usermanagment.repositories.UserGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserGroupService implements ICrudService<UserGroup> {
    @Autowired
    private UserGroupRepository userGroupRepository;

    @Autowired
    private UserGroupToUserGroupEntity userGrpToUserGrpEntity;
    @Autowired
    private UserGroupEntityToUserGroup userGrpEntityToUserGrp;

    @Override
    public UserGroup save(UserGroup userGroup) throws DuplicateUserGroupException{
        UserGroupEntity userGroupEntity = userGrpToUserGrpEntity.convert(userGroup);
        try {
            UserGroupEntity savedGroupEntity = userGroupRepository.save(Objects.requireNonNull(userGroupEntity));
            userGroup = userGrpEntityToUserGrp.convert(savedGroupEntity);
        }catch (DataIntegrityViolationException e){
            throw new DuplicateUserGroupException("Group already exists.");
        }
       return userGroup;
    }

    @Override
    public void deleteById(long id) {
        try {
            userGroupRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new UserGroupNotFoundException("User group with id: "+ id + " was not found.");
        }
    }

    @Override
    public UserGroup findById(long id) {
        Optional<UserGroupEntity> optionalUser = userGroupRepository.findById(id);
        if (optionalUser.isEmpty()){
            throw new UserGroupNotFoundException("User group with id: "+ id + " was not found.");
        }
        return userGrpEntityToUserGrp.convert(optionalUser.get());
    }

    @Override
    public Set<UserGroup> findAll() {
        Set<UserGroupEntity> userGroupsEntity = new LinkedHashSet<>();
        userGroupRepository.findAll().iterator().forEachRemaining(userGroupsEntity::add);
        Set<UserGroup> userGroups = new LinkedHashSet<>();
        for(UserGroupEntity userGroupEntity : userGroupsEntity){
            UserGroup userGroup = userGrpEntityToUserGrp.convert(userGroupEntity);
            userGroups.add(userGroup);
        }
        return userGroups;
    }
}

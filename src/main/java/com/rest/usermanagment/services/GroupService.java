package com.rest.usermanagment.services;

import com.rest.usermanagment.converters.GroupEntityToGroup;
import com.rest.usermanagment.converters.GroupToGroupEntity;
import com.rest.usermanagment.exceptions.DuplicateGroupException;
import com.rest.usermanagment.models.Group;
import com.rest.usermanagment.entities.GroupEntity;
import com.rest.usermanagment.exceptions.GroupNotFoundException;
import com.rest.usermanagment.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GroupService implements ICrudService<Group> {
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupToGroupEntity userGrpToUserGrpEntity;
    @Autowired
    private GroupEntityToGroup userGrpEntityToUserGrp;

    @Override
    public Group save(Group group) throws DuplicateGroupException {
        GroupEntity groupEntity = userGrpToUserGrpEntity.convert(group);
        try {
            GroupEntity savedGroupEntity = groupRepository.save(Objects.requireNonNull(groupEntity));
            group = userGrpEntityToUserGrp.convert(savedGroupEntity);
        }catch (DataIntegrityViolationException e){
            throw new DuplicateGroupException("Group already exists.");
        }
       return group;
    }

    @Override
    public void deleteById(long id) {
        try {
            groupRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new GroupNotFoundException("User group with id: "+ id + " was not found.");
        }
    }

    @Override
    public Group findById(long id) {
        Optional<GroupEntity> optionalUser = groupRepository.findById(id);
        if (optionalUser.isEmpty()){
            throw new GroupNotFoundException("User group with id: "+ id + " was not found.");
        }
        return userGrpEntityToUserGrp.convert(optionalUser.get());
    }

    @Override
    public Set<Group> findAll() {
        Set<GroupEntity> userGroupsEntity = new LinkedHashSet<>();
        groupRepository.findAll().iterator().forEachRemaining(userGroupsEntity::add);
        Set<Group> groups = new LinkedHashSet<>();
        for(GroupEntity groupEntity : userGroupsEntity){
            Group group = userGrpEntityToUserGrp.convert(groupEntity);
            groups.add(group);
        }
        return groups;
    }
}

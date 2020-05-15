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

    /**
     * calls repo save method that will save or update group info based on input
     * if input is passed with existing group then it will perform an update
     * @param group
     * @return
     * @throws DuplicateGroupException
     */
    @Override
    public Group saveOrUpdate(Group group) throws DuplicateGroupException {
        GroupEntity groupEntity = userGrpToUserGrpEntity.convert(group);
        try {
            GroupEntity savedGroupEntity = groupRepository.save(Objects.requireNonNull(groupEntity));
            group = userGrpEntityToUserGrp.convert(savedGroupEntity);
        }catch (DataIntegrityViolationException e){
            throw new DuplicateGroupException("Group already exists.");
        }
       return group;
    }

    /**
     * Deletes group based on groupId
     * @param id
     */
    @Override
    public void deleteById(long id) {
        try {
            groupRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new GroupNotFoundException("User group was not found.");
        }
    }

    /**
     * Finds Group based on groupId
     * Used to verify a group exists before adding a user to group
     * @param id
     * @return
     */
    @Override
    public Group findById(long id) {
        Optional<GroupEntity> optionalUser = groupRepository.findById(id);
        if (optionalUser.isEmpty()){
            throw new GroupNotFoundException("User group was not found.");
        }
        return userGrpEntityToUserGrp.convert(optionalUser.get());
    }

    /**
     * Used to load all groups to group page
     * @return
     */
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

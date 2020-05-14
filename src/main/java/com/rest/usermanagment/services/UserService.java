package com.rest.usermanagment.services;
import com.rest.usermanagment.converters.UserToUserEntity;
import com.rest.usermanagment.converters.UserEntityToUser;
import com.rest.usermanagment.exceptions.DuplicateUserException;
import com.rest.usermanagment.models.User;
import com.rest.usermanagment.entities.UserEntity;
import com.rest.usermanagment.exceptions.UserNotFoundException;
import com.rest.usermanagment.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements IService<User>{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserEntityToUser userEntityToUser;
    @Autowired
    private UserToUserEntity userToUserEntity;
    @Autowired
    private UserGroupService userGroupService;

    @Override
    public User save(User user) throws DuplicateUserException {
       UserEntity userEntity = userToUserEntity.convert(user);
       if(userEntity.getUserGroupEntity()!=null){
           userGroupService.findById(userEntity.getUserGroupEntity().getGroupId());
       }
       try {
           UserEntity savedEntity = userRepository.save(Objects.requireNonNull(userEntity));
           user = userEntityToUser.convert(savedEntity);
       }catch (DataIntegrityViolationException e){
           throw new DuplicateUserException("User email already exists.");
       }
       return user;
    }
    @Override
    public void deleteById(long id) {
        try {
            userRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new UserNotFoundException("User was not found.");
        }
    }

    @Override
    public User findById(long id) {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()){
            throw new UserNotFoundException("User was not found.");
        }
        return userEntityToUser.convert(optionalUser.get());
    }

    @Override
    public Set<User> findAll() {
        Set<UserEntity> usersEntity = new LinkedHashSet<>();
        userRepository.findAll().iterator().forEachRemaining(usersEntity::add);
        Set<User> users = new LinkedHashSet<>();
        for(UserEntity userEntity : usersEntity){
            User user = userEntityToUser.convert(userEntity);
            users.add(user);
        }

        return users;
    }
}

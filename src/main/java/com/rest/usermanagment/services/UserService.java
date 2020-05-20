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
public class UserService implements IQueryService<User>,ICrudService<User>{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserEntityToUser userEntityToUser;
    @Autowired
    private UserToUserEntity userToUserEntity;
    @Autowired
    private GroupService groupService;

    /**
     * calls repo save method that will save or update user info based on input
     * if input is passed with existing userId then it will perform an update
     * @param user
     * @return
     * @throws DuplicateUserException
     */
    @Override
    public User saveOrUpdate(User user)   {
       UserEntity userEntity = userToUserEntity.convert(user);
       if(userEntity.getGroupEntity()!=null){
           groupService.findById(userEntity.getGroupEntity().getGroupId());
       }
       try {
           UserEntity savedEntity = userRepository.save(Objects.requireNonNull(userEntity));
           user = userEntityToUser.convert(savedEntity);
       }catch (DataIntegrityViolationException e){
           throw new DuplicateUserException("User email already exists.");
       }

       return user;
    }

    /**
     * Deletes user based on userId sent from client
     * @param id
     */
    @Override
    public void deleteById(long id) {
        try {
            userRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new UserNotFoundException("User was not found.");
        }
    }

    /**
     * Finds user by userId
     * @param id
     * @return
     */
    @Override
    public User findById(long id) {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()){
            throw new UserNotFoundException("User was not found.");
        }
        return userEntityToUser.convert(optionalUser.get());
    }

    /**
     * Returns all users in the db. This is used when loading users in the user page
     * @return
     */
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

    /**
     * Finds user by email. This is called when authenticating a user
     * @param email
     * @return
     */
    @Override
    public User findByEmail(String email) {
            UserEntity userEntity = userRepository.findByEmail(email);
            if(null==userEntity){
                throw new UserNotFoundException("User was not found.");
            }
            return userEntityToUser.convert(userEntity);

    }
}

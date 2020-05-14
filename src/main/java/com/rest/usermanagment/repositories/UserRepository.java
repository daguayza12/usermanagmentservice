package com.rest.usermanagment.repositories;

import com.rest.usermanagment.entities.UserEntity;
import com.rest.usermanagment.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity,Long> {

    @Query("SELECT u FROM UserEntity u  WHERE u.email =:email")
     UserEntity findByEmail(@Param("email") String email);
}

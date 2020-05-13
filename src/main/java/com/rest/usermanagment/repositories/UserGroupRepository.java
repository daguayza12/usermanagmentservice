package com.rest.usermanagment.repositories;

import com.rest.usermanagment.entities.UserGroupEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserGroupRepository extends CrudRepository<UserGroupEntity,Long> {
}

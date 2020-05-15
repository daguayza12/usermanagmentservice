package com.rest.usermanagment.repositories;

import com.rest.usermanagment.entities.GroupEntity;
import org.springframework.data.repository.CrudRepository;

public interface GroupRepository extends CrudRepository<GroupEntity,Long> {
}

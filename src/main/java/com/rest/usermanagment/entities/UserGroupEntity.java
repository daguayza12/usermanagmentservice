package com.rest.usermanagment.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the usergroup database table.
 * 
 */
@Entity
@Table(name = "usergroup")
public class UserGroupEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="group_id")
	private long groupId;

	@Column(name="group_name")
	private String groupName;



	//bi-directional many-to-one association to User
	@OneToMany(mappedBy= "userGroupEntity")
	private Set<UserEntity> userEntity;

	public UserGroupEntity() {
	}
	public Set<UserEntity> getUsersEntity() {
		return userEntity;
	}

	public void setUsersEntity(Set<UserEntity> userEntity) {
		this.userEntity = userEntity;
	}
	public long getGroupId() {
		return this.groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}


}
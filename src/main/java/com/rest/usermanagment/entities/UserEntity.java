package com.rest.usermanagment.entities;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the user database table.
 *
 */
@Entity
@Table(name="user")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="user_id")
    private long userId;

    @Column(name="email",nullable = false)
    private String email;

    @Column(name="first_name",nullable = false)
    private String firstName;

    @Column(name="last_name",nullable = false)
    private String lastName;

    @Column(name="user_role")
    private String userRole;

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }



    //bi-directional many-to-one association to Usergroup
    @ManyToOne
    @JoinColumn(name="group_id")
    private GroupEntity groupEntity;

    public GroupEntity getGroupEntity() {
        return groupEntity;
    }

    public void setGroupEntity(GroupEntity usergroupEntity) {
        this.groupEntity = usergroupEntity;
    }


    public UserEntity() {
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long id) {
        this.userId = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}

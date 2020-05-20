package com.rest.usermanagment.services;

public interface IUserService<T> extends ICrudService<T>{

    T findByEmail(String email);

}

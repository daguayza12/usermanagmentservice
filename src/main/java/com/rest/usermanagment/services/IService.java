package com.rest.usermanagment.services;


import java.util.Set;

public interface IService<T> {

    T save(T entity) throws Exception;
    void deleteById(long id);
    T findById(long id);
    Set<T> findAll();
}

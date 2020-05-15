package com.rest.usermanagment.services;


import java.util.Set;

public interface ICrudService<T> {

    T saveOrUpdate(T entity) throws Exception;
    void deleteById(long id);
    T findById(long id);
    Set<T> findAll();
}

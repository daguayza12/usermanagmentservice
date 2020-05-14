package com.rest.usermanagment.services;

public interface IQueryService<T> {

    T findByEmail(String email);
}

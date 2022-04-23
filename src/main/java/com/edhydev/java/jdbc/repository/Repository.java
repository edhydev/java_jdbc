package com.edhydev.java.jdbc.repository;

import java.util.List;

public interface Repository<T> {
    List<T> findAll();

    T findById(Long id);

    void save(T t);

    void delete(Long id);
}

package org.example.repository;

import java.util.List;

public interface Repository<T,ID> {
    void save(T entity) throws Exception;
    void update(T entity);
    void delete(ID id);
    T findById(ID id) throws Exception;
    List<T> findAll() throws Exception;
}
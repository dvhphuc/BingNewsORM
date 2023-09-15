package org.example.repository;

import java.util.List;

public abstract class EntityRepository<T> {
    protected Class<T> entityClass;
    public abstract void save(T entity) throws Exception;
    public abstract T update(T entity);
    public abstract void delete(T entity);
    public abstract T findById(String id) throws Exception;
    public abstract List<T> findAll() throws Exception;
}

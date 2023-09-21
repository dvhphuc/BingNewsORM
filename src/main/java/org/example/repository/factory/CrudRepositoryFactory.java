package org.example.repository.factory;

import org.example.repository.CrudRepository;

public interface CrudRepositoryFactory {
    CrudRepository getRepository(Class<?> clazz);
}
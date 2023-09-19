package org.example.repository.factory;

import org.example.model.AdTopic;
import org.example.model.Article;
import org.example.repository.CrudRepository;
import org.example.repository.Repository;
import org.example.repository.impl.CrudRepositoryImpl;

public abstract class RepositoryFactory {
    public static CrudRepository getRepository(Class clazz) {
        return new CrudRepositoryImpl(clazz);
    }
}

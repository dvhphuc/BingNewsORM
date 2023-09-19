package org.example.repository.factory;

import org.example.model.AdTopic;
import org.example.repository.CrudRepository;
import org.example.repository.Repository;
import org.example.repository.impl.CrudRepositoryImpl;

public class AdTopicRepositoryFactory {
    public static CrudRepository getAdTopicRepository() {
        return new CrudRepositoryImpl<AdTopic,String>(AdTopic.class);
    }
}

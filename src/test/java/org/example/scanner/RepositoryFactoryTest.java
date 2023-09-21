package org.example.scanner;

import configuration.ConfigReader;
import org.example.DbConnection;
import org.example.repository.AdTopicRepository;
import org.example.repository.ArticleRepository;
import org.example.repository.CrudRepository;
import org.junit.jupiter.api.Test;

import java.lang.reflect.ParameterizedType;
import java.util.List;

class RepositoryFactoryTest {

    @Test
    void createRepositoryImplementInRunTime() throws Exception {
        DbConnection dbConnection = new DbConnection(ConfigReader.getConnectionString());
        RepositoryFactory repoFactory = new RepositoryFactory();

        var repoInterfaces = List.of(ArticleRepository.class, AdTopicRepository.class);
        for (var crudRepository : repoInterfaces) {
            CrudRepository repo = repoFactory.createRepoImpl(crudRepository);
            System.out.println(repo.findAll().size());
        }

    }
}
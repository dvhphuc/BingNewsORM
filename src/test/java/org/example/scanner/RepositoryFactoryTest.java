package org.example.scanner;

import configuration.ConfigReader;
import org.example.DbConnection;
import org.example.repository.AdTopicRepository;
import org.example.repository.ArticleRepository;
import org.example.repository.CrudRepository;
import org.example.repository.factory.RepositoryFactory;
import org.junit.jupiter.api.Test;

import java.util.List;

class RepositoryFactoryTest {

    @Test
    void createRepositoryImplementInRunTime() throws Exception {
        var dbConnection = new DbConnection(ConfigReader.getConnectionString());
        var repoFactory = new RepositoryFactory();

        var repoInterfaces = List.of(ArticleRepository.class, AdTopicRepository.class);
        for (var repoInterface : repoInterfaces) {
            CrudRepository repo = repoFactory.createRepoImpl(repoInterface);
            assert repo.findAll().size() > 0;
            System.out.println(repo.findAll().size());
        }

    }
}
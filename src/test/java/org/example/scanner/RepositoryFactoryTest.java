package org.example.scanner;

import configuration.ConfigReader;
import org.example.DbConnection;
import org.example.annotation.Entity;
import org.example.repository.AdTopicRepository;
import org.example.repository.ArticleRepository;
import org.example.repository.CrudRepository;
import org.example.repository.factory.RepositoryFactory;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;

import java.util.List;
import java.util.Set;

class RepositoryFactoryTest {

    @Test
    void createRepositoryImplementInRunTime() throws Exception {
        var dbConnection = new DbConnection(ConfigReader.getConnectionString());
        var repoFactory = new RepositoryFactory(dbConnection);

        var repoInterfaces = List.of(ArticleRepository.class, AdTopicRepository.class);
        for (var repoInterface : repoInterfaces) {
            CrudRepository repo = repoFactory.createRepoImpl(repoInterface);
            assert repo.findAll().size() > 0;
        }

    }

    @Test
    void testGetAllClassOfInterface() {
        Reflections reflections = new Reflections("org.example.repository");
        Set<Class<? extends CrudRepository>> classes = reflections.getSubTypesOf(CrudRepository.class);
        classes.forEach(System.out::println);
    }

    @Test
    void testGetAllClasses() {
        var rootApp = new RootApp();
        assert rootApp.getDependencyMap().getDependencies(CrudRepository.class).size() > 0;
    }
}
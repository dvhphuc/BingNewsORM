package org.example.repository;


import configuration.ConfigReader;
import org.example.DbConnection;
import org.example.repository.factory.RepositoryFactory;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

class EntityRepositoryTest {

    void makeConnectionToDb() throws Exception {
        var fileInputStream = new FileInputStream("src/main/java/configuration/config.properties");
        var configReader = new ConfigReader(fileInputStream);
        var connectionString = configReader.getConnectionString();
        var dbConnection = new DbConnection(connectionString);
    }

    @Test
    void testUseInterfaceExtendJPA() throws Exception {
        makeConnectionToDb();

        var repo = (ArticleRepository) RepositoryFactory.createRepoImpl(ArticleRepository.class);
        assert repo.findAll().size() > 0;

        var adRepo = (AdTopicRepository) RepositoryFactory.createRepoImpl(AdTopicRepository.class);
        assert adRepo.findAll().size() > 0;
    }


}
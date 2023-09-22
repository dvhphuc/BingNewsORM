package org.example.repository;


import configuration.ConfigReader;
import org.example.DbConnection;
import org.example.repository.factory.RepositoryFactory;
import org.junit.jupiter.api.Test;

class EntityRepositoryTest {

    @Test
    void testUseInterfaceExtendJPA() throws Exception {
        DbConnection dbConnection = new DbConnection(ConfigReader.getConnectionString());
        var repo = (ArticleRepository) RepositoryFactory.createRepoImpl(ArticleRepository.class);
        assert repo.findAll().size() > 0;

        var adRepo = (AdTopicRepository) RepositoryFactory.createRepoImpl(AdTopicRepository.class);
        assert adRepo.findAll().size() > 0;
    }



}
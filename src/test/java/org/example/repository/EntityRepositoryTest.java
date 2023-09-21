package org.example.repository;


import configuration.ConfigReader;
import org.example.DbConnection;
import org.example.repository.factory.RepositoryFactory;
import org.junit.jupiter.api.Test;

class EntityRepositoryTest {

    @Test
    void testUseInterfaceExtendJPA() throws Exception {
        DbConnection dbConnection = new DbConnection(ConfigReader.getConnectionString());
        ArticleRepository articleRepository = (ArticleRepository) RepositoryFactory.createRepoImpl(ArticleRepository.class);
        assert articleRepository.findAll().size() > 0;

        AdTopicRepository adTopicRepository = (AdTopicRepository) RepositoryFactory.createRepoImpl(AdTopicRepository.class);
        assert adTopicRepository.findAll().size() > 0;
    }



}
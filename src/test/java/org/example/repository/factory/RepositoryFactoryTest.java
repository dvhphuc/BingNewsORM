package org.example.repository.factory;

import org.example.DbConnection;
import org.example.model.AdTopic;
import org.example.model.Article;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryFactoryTest {

    @Test
    void getRepository() throws Exception {
        var dbConnection = new DbConnection("jdbc:sqlserver://localhost;database=BingNews;integratedSecurity=true;trustServerCertificate=true;");
        var articleRepository = RepositoryFactory.getRepository(Article.class);
        var adtopic = RepositoryFactory.getRepository(AdTopic.class);
        System.out.println(articleRepository.findAll().size());
        //System.out.println(adtopic.findAll().size());
    }
}
package org.example.API.controller;

import configuration.ConfigReader;
import org.example.API.service.impl.ArticleService;
import org.example.DbConnection;
import org.example.repository.ArticleRepository;
import org.example.repository.factory.RepositoryFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArticleControllerTest {

    @Test
    void testGetArticles() throws Exception {
        var atcileRepository = (ArticleRepository) RepositoryFactory.createRepoImpl(ArticleRepository.class);
        var articleService = new ArticleService(atcileRepository);
        var articleController = new ArticleController(articleService);
        assert articleController.getArticles().length() > 0;
    }

}
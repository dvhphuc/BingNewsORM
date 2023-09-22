package org.example.api.controller;

import org.example.api.service.impl.ArticleService;
import org.example.repository.ArticleRepository;
import org.example.repository.factory.RepositoryFactory;
import org.junit.jupiter.api.Test;

class ArticleControllerTest {

    @Test
    void testGetArticles() throws Exception {
        var atcileRepository = (ArticleRepository) RepositoryFactory.createRepoImpl(ArticleRepository.class);
        var articleService = new ArticleService(atcileRepository);
        var articleController = new ArticleController(articleService);
        assert articleController.getArticles().length() > 0;
    }

}
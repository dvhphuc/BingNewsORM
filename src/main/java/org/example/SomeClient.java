package org.example;

import org.example.repository.EntityRepository;

public class SomeClient {
    private final EntityRepository articleRepository;
    SomeClient(EntityRepository _articleRepository) {
        this.articleRepository = _articleRepository;
    }

    public void doSomething() throws Exception {
        var articles = articleRepository.findAll();
        for (var article : articles) {
            System.out.println(article);
        }

        var atc = new Article("test1", "test1", "test1", "test1", "test1","test1");
        articleRepository.save(atc);
    }
}

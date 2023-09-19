package org.example.repository.factory;

import org.example.model.Article;
import org.example.repository.ArticleRepository;
import org.example.repository.CrudRepository;
import org.example.repository.Repository;
import org.example.repository.impl.CrudRepositoryImpl;

public class ArticleRepositoryFactory {
    public static CrudRepository getArticleRepository() {
        return new CrudRepositoryImpl<Article,String>(Article.class);
    }
}

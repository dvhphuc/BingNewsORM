package org.example.API.service.impl;

import org.example.annotation.Autowired;
import org.example.model.Article;
import org.example.repository.ArticleRepository;
import org.json.JSONArray;

public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public String getArticles() throws Exception {
        var articles = articleRepository.findAll();
        return new JSONArray(articles).toString();
    }

    public String getArticle(String id) throws Exception {
        var article = articleRepository.findById(id);
        return article.toString();
    }

    public String postArticle(Article article) throws Exception {
        articleRepository.save(article);
        return article.toString();
    }

    public String deleteArticle(String id) throws Exception {
        articleRepository.delete(id);
        return "Article deleted";
    }

    public String updateArticle(Article article) throws Exception {
        articleRepository.update(article);
        return article.toString();
    }

}

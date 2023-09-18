package org.example.API.controller;
import org.example.annotation.Controller;
import org.example.annotation.GetMapping;
import org.example.model.Article;
import org.example.repository.ArticleRepository;
import org.example.repository.impl.CrudRepositoryImpl;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ArticleController {
    ArticleRepository articleRepository;
    public ArticleController() throws Exception {
        articleRepository = new ArticleRepository(new CrudRepositoryImpl<Article,String>(Article.class));
    }
    @GetMapping("/articles")
    public String getArticles() throws Exception {
        var articles = articleRepository.findAll();
        System.out.println(articles.get(0).toString().length());
        return "Hello";
    }
}

package org.example.API.controller;
import org.example.API.service.impl.ArticleService;
import org.example.annotation.*;
import org.example.model.Article;
import org.example.repository.ArticleRepository;
import org.example.repository.impl.CrudRepositoryImpl;

import javax.servlet.http.HttpServletRequest;

@Controller("Article")
public class ArticleController {
    ArticleService articleService;
    public ArticleController(ArticleService _articleService) throws Exception {
        articleService = _articleService;
    }

    @GetMapping("articles")
    public String getArticles() throws Exception {
        return articleService.getArticles();
    }

    @GetMapping("articles/{id}")
    public String getArticle(@PathVariable("id") String id) throws Exception {
        return articleService.getArticle(id);
    }

    @PostMapping("/articles")
    public String postArticle(HttpServletRequest request) throws Exception {
        var article = new Article();
        article.setGuid(request.getParameter("guid"));
        article.setTitle(request.getParameter("title"));
        article.setPubDate(request.getParameter("pubDate"));
        article.setChannelId(request.getParameter("channelId"));
        articleService.postArticle(article);
        return article.toString();
    }

    @DeleteMapping("/articles/{id}")
    public String deleteArticle(@PathVariable("id") String id) throws Exception {
        return articleService.deleteArticle(id);
    }
}

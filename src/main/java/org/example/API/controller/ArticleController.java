//package org.example.API.controller;
//import org.example.annotation.*;
//import org.example.model.Article;
//import org.example.repository.impl.CrudRepositoryImpl;
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import javax.servlet.http.HttpServletRequest;
//
//@Controller("Article")
//public class ArticleController {
//    ArticleRepository1 articleRepository;
//    public ArticleController() throws Exception {
//        articleRepository = new ArticleRepository1(new CrudRepositoryImpl<Article,String>(Article.class));
//    }
//
//    @GetMapping("articles")
//    public String getArticles() throws Exception {
//        var articles = articleRepository.findAll();
//        return new JSONArray(articles).toString();
//    }
//
//    @GetMapping("articles/{id}")
//    public String getArticle(@PathVariable("id") String id) throws Exception {
//        var article = articleRepository.findById(id);
//        return new JSONObject(article).toString();
//    }
//
//    @PostMapping("/articles")
//    public String postArticles(HttpServletRequest request) throws Exception {
//        var body = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
//        var jsonObject = new JSONObject(body);
//        String guid = jsonObject.getString("guid");
//        if (articleRepository.findById(guid) != null) {
//            return "Article already exists";
//        }
//
//        String title = jsonObject.getString("title");
//        String imgUrl = jsonObject.getString("imgUrl");
//        String pubDate = jsonObject.getString("pubDate");
//        String sourceLink = jsonObject.getString("sourceLink");
//        String channelId = jsonObject.getString("channelId");
//        var article = new Article(guid, title, imgUrl, pubDate, sourceLink, channelId);
//        articleRepository.save(article);
//        return new JSONObject(article).toString();
//    }
//
//    @DeleteMapping("/articles/{id}")
//    public String deleteArticle(HttpServletRequest request) throws Exception {
//        var guid = request.getParameter("guid");
//        var article = articleRepository.findById(guid);
//        if (article == null) {
//            return "Article not found";
//        }
//        articleRepository.delete(article.getGuid());
//        return "Article deleted";
//    }
//}

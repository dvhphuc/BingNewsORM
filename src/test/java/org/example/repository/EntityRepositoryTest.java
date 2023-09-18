package org.example.repository;


import org.example.model.Article;
import org.example.DbConnection;
import org.example.repository.impl.CrudRepositoryImpl;
import org.junit.jupiter.api.Test;

class EntityRepositoryTest {

    @org.junit.jupiter.api.Test
    void save() throws Exception {
        var dbConnection = new DbConnection("jdbc:sqlserver://localhost;database=BingNews;integratedSecurity=true;trustServerCertificate=true;");
        var crudRepoImpl = new CrudRepositoryImpl<Article, String>(Article.class);
        var articleRepository = new ArticleRepository(crudRepoImpl);
        var article = new Article("1", "title", "content", "author", "url", "abc");
        articleRepository.save(article);
    }

    @org.junit.jupiter.api.Test
    void findAll() throws Exception {
        var dbConnection = new DbConnection("jdbc:sqlserver://localhost;database=BingNews;integratedSecurity=true;trustServerCertificate=true;");
        var crudRepoImpl = new CrudRepositoryImpl<Article, String>(Article.class);
        var articleRepository = new ArticleRepository(crudRepoImpl);
        assert articleRepository.findAll().size() > 0;
    }

    @org.junit.jupiter.api.Test
    void findById() throws Exception {
        var dbConnection = new DbConnection("jdbc:sqlserver://localhost;database=BingNews;integratedSecurity=true;trustServerCertificate=true;");
        var crudRepoImpl = new CrudRepositoryImpl<Article, String>(Article.class);
        var articleRepository = new ArticleRepository(crudRepoImpl);
        assert articleRepository.findById("1") != null;
    }

    @Test
    void testFindByPredicate() throws Exception {
        var dbConnection = new DbConnection("jdbc:sqlserver://localhost;database=BingNews;integratedSecurity=true;trustServerCertificate=true;");
        var crudRepoImpl = new CrudRepositoryImpl<Article, String>(Article.class);
        var articleRepository = new ArticleRepository(crudRepoImpl);
        var articles = articleRepository.getInPage(article -> article.getTitle().equals("test"));
        articles.forEach(article -> System.out.println(article.getTitle()));
    }

    @Test
    void testPagination() throws Exception {
        var dbConnection = new DbConnection("jdbc:sqlserver://localhost;database=BingNews;integratedSecurity=true;trustServerCertificate=true;");
        var crudRepoImpl = new CrudRepositoryImpl<Article, String>(Article.class);
        var articleRepository = new ArticleRepository(crudRepoImpl);

        var articlesInPage1 = articleRepository.getInPage(1, 3);
        assert articlesInPage1.size() == 3;
    }
}
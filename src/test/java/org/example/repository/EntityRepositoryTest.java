package org.example.repository;


import configuration.ConfigReader;
import org.example.DbConnection;
import org.example.model.Article;
import org.example.query.SelecAllOnFieldsBuilder;
import org.example.repository.factory.RepositoryFactory;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;

class EntityRepositoryTest {

    void makeConnectionToDb() throws Exception {
        var fileInputStream = new FileInputStream("src/main/java/configuration/config.properties");
        var configReader = new ConfigReader(fileInputStream);
        var connectionString = configReader.getConnectionString();
        var dbConnection = new DbConnection(connectionString);
    }

    @Test
    void testUseInterfaceExtendJPA() throws Exception {
        makeConnectionToDb();

        var repo = (ArticleRepository) RepositoryFactory.createRepoImpl(ArticleRepository.class);
        assert repo.findAll().size() > 0;

        var adRepo = (AdTopicRepository) RepositoryFactory.createRepoImpl(AdTopicRepository.class);
        assert adRepo.findAll().size() > 0;
    }

    @Test
    void testFindById() throws Exception {
        String id = "https://laodong.vn/cong-doan/mua-do-den-tiep-suc-cho-1800-lao-dong-chong-nong-1234134.ldo";
        makeConnectionToDb();
        var repo = (ArticleRepository) RepositoryFactory.createRepoImpl(ArticleRepository.class);
        assert repo.findById(id).getGuid().equals(id);
    }

    @Test
    void testFindAllByQuery() throws Exception {
        makeConnectionToDb();

        var repo = (ArticleRepository) RepositoryFactory.createRepoImpl(ArticleRepository.class);
        String sqlQuery = "SELECT TOP (1000) [GUID]\n" +
                "      ,[TITLE]\n" +
                "      ,[IMGURL]\n" +
                "      ,[PUBDATE]\n" +
                "      ,[SOURCELINK]\n" +
                "      ,[ChannelId]\n" +
                "  FROM [BingNews].[dbo].[Article]";
        assert repo.find(sqlQuery).size() > 0;
    }

    @Test
    void testFindOnFields() throws Exception {
        makeConnectionToDb();
        var repo = (ArticleRepository) RepositoryFactory.createRepoImpl(ArticleRepository.class);
        String[] fields = {"IMGURL", "TITLE"};
        assert repo.findAll(fields).size() > 0;
    }

    @Test
    void testFindByPredicate() throws Exception {
        makeConnectionToDb();
        var repo = (ArticleRepository) RepositoryFactory.createRepoImpl(ArticleRepository.class);
        assert repo.find(article -> article.getGuid().equals("test")).size() > 0;
    }
    @Test
    void testInsertData()throws Exception {
        makeConnectionToDb();
        var article = new Article();
        article.setGuid("test");
        article.setTitle("test");
        article.setChannelId("test");
        article.setImgUrl("test");
        article.setPubDate("test");
        article.setSourceLink("test");

        var repo = (ArticleRepository) RepositoryFactory.createRepoImpl(ArticleRepository.class);
        repo.save(article);

        assert repo.findById("test").getGuid().equals("test");

    }


    @Test
    void testUpdateData()throws Exception {
        makeConnectionToDb();
        var article = new Article();
        article.setGuid("test");
        article.setTitle("test");
        article.setChannelId("test");
        article.setImgUrl("test");
        article.setPubDate("test");
        article.setSourceLink("test");

        var repo = (ArticleRepository) RepositoryFactory.createRepoImpl(ArticleRepository.class);
        repo.save(article);

        article.setTitle("test2");
        repo.update(article);

        assert repo.findById("test").getTitle().equals("test2");

    }

    @Test
    void testDeleteData()throws Exception {
        makeConnectionToDb();
        var article = new Article("test", "test", "test", "test", "test", "test");

        var repo = (ArticleRepository) RepositoryFactory.createRepoImpl(ArticleRepository.class);
        repo.save(article);

        repo.delete("test");

        assert repo.findById("test") == null;

    }
}
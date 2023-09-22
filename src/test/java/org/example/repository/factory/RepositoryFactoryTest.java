package org.example.repository.factory;

import org.example.DbConnection;
import org.example.model.Article;
import org.example.repository.impl.CrudRepositoryImpl;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

class RepositoryFactoryTest {
    public String extractDbConnection() throws IOException {
        var configFilePath = "src/main/java/configuration/config.properties";
        FileInputStream propsInput = new FileInputStream(configFilePath);
        Properties props = new Properties();
        props.load(propsInput);
        return props.getProperty("ConnectionString");
    }

    @Test
    void getRepository() throws Exception {
        var dbConnection = new DbConnection(extractDbConnection());
        CrudRepositoryImpl<Article, String> articleRepoImpl = new CrudRepositoryImpl<>(Article.class, dbConnection);
        assert articleRepoImpl.findAll().size() > 0;
    }

}
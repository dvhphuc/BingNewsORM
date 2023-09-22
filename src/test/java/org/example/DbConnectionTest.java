package org.example;

import configuration.ConfigReader;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;

import static org.junit.jupiter.api.Assertions.*;

class DbConnectionTest {

    @Test
    void testGetConnectionUrl() throws Exception {
        var fileInputStream = new FileInputStream("src/main/java/configuration/config.properties");
        var configReader = new ConfigReader(fileInputStream);
        var connectionString = configReader.getConnectionString();
        var dbConnection = new DbConnection(connectionString);
        var connectionUrl = dbConnection.getConnectionUrl();
        assert connectionUrl != null;
    }

    @Test
    void getConnection() throws Exception {
        var fileInputStream = new FileInputStream("src/main/java/configuration/config.properties");
        var configReader = new ConfigReader(fileInputStream);
        var connectionString = configReader.getConnectionString();
        var dbConnection = new DbConnection(connectionString);
        var connection = dbConnection.getConnection();
        assert connection != null;
    }

    @Test
    void closeConnection() throws Exception {
        var fileInputStream = new FileInputStream("src/main/java/configuration/config.properties");
        var configReader = new ConfigReader(fileInputStream);
        var connectionString = configReader.getConnectionString();
        var dbConnection = new DbConnection(connectionString);
        dbConnection.closeConnection();

    }
}
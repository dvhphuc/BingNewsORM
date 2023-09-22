package org.example;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;

import static org.junit.jupiter.api.Assertions.*;

class DbConnectionTest {

    @Test
    void testGetConnectionUrl() throws Exception {
        var fileInputStream = new FileInputStream("src/main/java/configuration/config.properties");
    }

    @Test
    void getConnection() {
    }

    @Test
    void getStatement() {
    }

    @Test
    void closeConnection() {
    }
}
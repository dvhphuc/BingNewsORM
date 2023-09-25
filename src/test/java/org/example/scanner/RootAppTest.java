package org.example.scanner;

import configuration.ConfigReader;
import org.example.DbConnection;
import org.example.api.controller.ArticleController;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;

class RootAppTest {

    @Test
    void testCreateBeansInOrder() throws Exception {
        var rootApp = new RootApp();
        rootApp.register(ClassA.class);
        rootApp.getDependencyMap().arrangeBeans();
        assert RootApp.getInstances().size() == 4; // ClassA, ClassB, ClassC, ClassC1
    }

    @Test
    void testAutowiredInController() throws Exception {
        var fileInputStream = new FileInputStream("src/main/java/configuration/config.properties");
        var configReader = new ConfigReader(fileInputStream);
        var connectionString = configReader.getConnectionString();
        var dbConnection = new DbConnection(connectionString);

        var rootApp = new RootApp();
        rootApp.register(ArticleController.class);
        rootApp.getDependencyMap().arrangeBeans();
        rootApp.createBeanInOrder();
        System.out.println(rootApp.getDependencyMap().getDependentTree().size());
    }

}
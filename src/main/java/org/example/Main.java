package org.example;

import com.sun.net.httpserver.HttpServer;
import org.example.API.WebService;
import org.example.API.controller.ArticleController;

import java.net.InetSocketAddress;

public class Main {
    static public void main(String[] args) throws Exception {

        String connectionString = "jdbc:sqlserver://localhost;database=BingNews;integratedSecurity=true;trustServerCertificate=true;";
        DbConnection.connectionUrl = connectionString;

        int PORT = 8080;

        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);

        var webService = new WebService(server);
        webService.addController(ArticleController.class);

        webService.start();
    }
}
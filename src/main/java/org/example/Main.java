package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.example.model.Article;
import org.example.repository.ArticleRepository;
import org.example.repository.CrudRepository;
import org.example.repository.impl.CrudRepositoryImpl;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {
    static public void main(String[] args) throws Exception {

        String connectionString = "jdbc:sqlite:src/main/resources/db.sqlite";
        DbConnection.connectionUrl = connectionString;

        var crudRepository = new CrudRepositoryImpl<>(Article.class);
        var articleRepository = new ArticleRepository(crudRepository);

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/articles", exchange -> {
            String path = exchange.getRequestBody().toString();
            String[] pathParts = path.split("/");
            try {
                var articles = articleRepository.findAll();
                exchange.sendResponseHeaders(200, articles.size());
                exchange.getResponseBody().write(articles.toString().getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        server.createContext("/articles/:id", exchange -> {
            String path = exchange.getRequestBody().toString();
            String[] pathParts = path.split("/");
            String id = pathParts[2];
            try {
                var article = articleRepository.findById(id);
                exchange.sendResponseHeaders(200, 1);
                exchange.getResponseBody().write(article.toString().getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
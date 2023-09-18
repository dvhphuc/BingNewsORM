package org.example.API;

import com.sun.net.httpserver.HttpServer;
import org.example.API.controller.ArticleController;
import org.junit.jupiter.api.Test;

import java.net.InetSocketAddress;

class WebServiceTest {

    @Test
    void testGetArticles() throws Exception {
        var server = HttpServer.create(new InetSocketAddress(8080), 0);
        var webService = new WebService(server);
        webService.addController(ArticleController.class);
        webService.start();
    }

    @Test
    void testIsOKAY() {
        var webService = new WebService(null);

    }
}
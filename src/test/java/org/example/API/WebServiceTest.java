package org.example.API;

import com.sun.net.httpserver.HttpServer;
import org.example.API.controller.ArticleController;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;

class WebServiceTest {

    @Test
    void addController() throws IOException {
        var server = HttpServer.create(new InetSocketAddress(8080), 0);
        var api = new WebService(server);
        api.addController(ArticleController.class);
    }
}
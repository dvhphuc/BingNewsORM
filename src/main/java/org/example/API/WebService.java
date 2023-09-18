package org.example.API;

import com.sun.net.httpserver.HttpServer;
import org.example.annotation.GetMapping;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class WebService {
    HttpServer server;
    public WebService(HttpServer server) {
        this.server = server;
    }

    public void addController(Class<?> controller) {
        Method[] methods = controller.getDeclaredMethods();
        for (Method method : methods) {
            GetMapping getMapping = method.getAnnotation(GetMapping.class);
            String path = getMapping.value();
            server.createContext(path, httpExchange -> {
                Object response = null;
                try {
                    response = method.invoke(controller.newInstance());
                } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                    e.printStackTrace();
                }
                String responseString = response.toString();
                httpExchange.sendResponseHeaders(200, responseString.length());
                var os = httpExchange.getResponseBody();
                os.write(responseString.getBytes());
                os.close();
            });
        }
    }

    public void start() {
        server.start();
    }

}

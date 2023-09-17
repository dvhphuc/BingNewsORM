package org.example.API;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.example.annotation.GetMapping;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class API {
    HttpServer server;
    public API(HttpServer server) {
        this.server = server;
    }

    public void addController(Class<?> controller) {
        Method[] methods = controller.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(GetMapping.class)) {
                GetMapping getMapping = method.getAnnotation(GetMapping.class);
                String path = getMapping.values();
                HttpHandler handler = new HttpHandler() {
                    @Override
                    public void handle(HttpExchange exchange) {
                        try {
                            method.invoke(controller.newInstance(), exchange);
                        } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                            e.printStackTrace();
                        }
                    }
                };
                server.createContext(path, handler);
            }
        }
    }



}

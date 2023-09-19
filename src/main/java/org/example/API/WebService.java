package org.example.API;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.example.annotation.GetMapping;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class WebService {
    HttpServer server;
    List<Class> controllerClasses = new ArrayList<>();

    public WebService(HttpServer server) {
        this.server = server;
    }

    public void addController(Class<?> controllerClass) {
        controllerClasses.add(controllerClass);
    }

    public void createServerContext() throws Exception {
        server.createContext("/", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String requestURI = exchange.getRequestURI().toString();
                try {
                    Class<?> controllerClass = getControllerClass(requestURI);
                    Method controllerMethod = getMethod(requestURI);

                    Object controllerInstance = controllerClass.getConstructor().newInstance();
                    Object response = controllerMethod.invoke(controllerInstance);
                    String responseString = response.toString();

                    exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
                    exchange.sendResponseHeaders(200, responseString.getBytes().length);
                    exchange.getResponseBody().write(responseString.getBytes());
                    exchange.close();

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void start() {
        server.start();
    }

    public Class<?> getControllerClass(String requestURI) throws ClassNotFoundException {
        String[] requestURISplit = requestURI.split("/");
        String controllerName = requestURISplit[1];
        String controllerClassName = "org.example.API.controller." + controllerName.substring(0, 1).toUpperCase(Locale.ROOT) + controllerName.substring(1) + "Controller";
        return Class.forName(controllerClassName);
    }

    public Method getMethod(String requestURI) throws Exception {
        Class<?> controllerClass = getControllerClass(requestURI);
        String[] requestURISplit = requestURI.split("/");
        String methodName = requestURISplit[2];
        for (Method method : controllerClass.getMethods()) {
            if (!method.isAnnotationPresent(GetMapping.class)) {
                continue;
            }
            GetMapping getMapping = method.getAnnotation(GetMapping.class);
            if (!getMapping.value().equals(methodName)) {
                continue;
            }
            return method;
        }
        throw new Exception("Method not found");
    }
}
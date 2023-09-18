package org.example.API;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import org.example.annotation.Controller;
import org.example.annotation.GetMapping;
import org.example.annotation.PostMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

public class WebService {
    HttpServer server;
    public WebService(HttpServer server) {
        this.server = server;
    }

    public void addServerContext(Class<?> controller, Annotation annotation, Method method) throws Exception {
        if (annotation.annotationType().equals(GetMapping.class)) {
            String path = ((GetMapping) annotation).value();
            server.createContext(path, (HttpExchange exchange) -> {
                Object response = null;
                try {
                    response = method.invoke(controller.getDeclaredConstructor().newInstance());
                } catch (IllegalAccessException | InvocationTargetException | InstantiationException |
                         NoSuchMethodException e) {
                    e.printStackTrace();
                }
                String responseText = response.toString();
                exchange.getResponseHeaders().set("Content-Type", "application/json; charset=" + StandardCharsets.UTF_8);
                exchange.sendResponseHeaders(200, responseText.getBytes(StandardCharsets.UTF_8).length);
                var os = exchange.getResponseBody();
                os.write(responseText.getBytes(StandardCharsets.UTF_8));
                os.close();
            });
        }

        if (annotation.annotationType().equals(PostMapping.class)) {
            String path = ((PostMapping) annotation).value();
            server.createContext(path, (HttpExchange exchange) -> {
                Object response = null;
                try {
                    response = method.invoke(controller.getDeclaredConstructor().newInstance(), exchange.getRequestURI().getQuery());
                } catch (IllegalAccessException | InvocationTargetException | InstantiationException |
                         NoSuchMethodException e) {
                    e.printStackTrace();
                }
                String responseText = response.toString();
                exchange.getResponseHeaders().set("Content-Type", "application/json; charset=" + StandardCharsets.UTF_8);
                exchange.sendResponseHeaders(200, responseText.getBytes(StandardCharsets.UTF_8).length);
                var os = exchange.getResponseBody();
                os.write(responseText.getBytes(StandardCharsets.UTF_8));
                os.close();
            });
        }
    }


    public void addController(Class<?> controller) throws Exception {
        if (!controller.isAnnotationPresent(Controller.class))
            return;

        Method[] methods = controller.getDeclaredMethods();
        for (Method method : methods) {
            var getMapping = method.getAnnotation(GetMapping.class);
            addServerContext(controller, getMapping, method);
        }
    }

    public void start() {
        server.start();
    }

    public boolean isSame(String templateUri, String clientUri) {
        String[] templateItems = templateUri.split("/");
        String[] clientItems = clientUri.split("/");
        if (templateItems.length != clientItems.length) {
            return false;
        }
        for (int i = 0; i < templateItems.length; i++) {
            if (templateItems[i].equals(clientItems[i])) {
                continue;
            }
            if (templateItems[i].startsWith("{") && templateItems[i].endsWith("}")) {
                continue;
            }
            return false;
        }
        return true;
    }

}

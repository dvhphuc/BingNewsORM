//package org.example.API;
//
//import com.sun.net.httpserver.HttpExchange;
//import com.sun.net.httpserver.HttpHandler;
//import com.sun.net.httpserver.HttpServer;
//import org.example.annotation.Controller;
//
//import java.io.IOException;
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.List;
//
//public class WebService {
//    HttpServer server;
//    List<Class> controllerClasses = new ArrayList<>();
//
//    public WebService(HttpServer server) {
//        this.server = server;
//    }
//
//    public void addControllerClass(Class<?> controllerClass) {
//        controllerClasses.add(controllerClass);
//    }
//
//    public void createServerContextForGetMapping() throws Exception {
//        server.createContext("/", new HttpHandler() {
//            @Override
//            public void handle(HttpExchange exchange) throws IOException {
//                String requestURI = exchange.getRequestURI().toString();
//                try {
//                    Class<?> controllerClass = getControllerClassFromURI(requestURI);
//                    //Method controllerMethod = getControllerMethodFromURI(requestURI);
//                    Object controllerInstance = controllerClass.getConstructor().newInstance();
//
//                    Object response = controllerMethod.invoke(controllerInstance);
//                    String responseString = response.toString();
//                    exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
//                    exchange.sendResponseHeaders(200, responseString.getBytes().length);
//                    exchange.getResponseBody().write(responseString.getBytes());
//                    exchange.close();
//
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//    }
//
//    public void start() {
//        server.start();
//    }
//
//    public Class<?> getControllerClassFromURI(String requestURI) throws ClassNotFoundException {
//        // localhost/article
//        String[] requestURISplit = requestURI.split("/");
//        String controllerName = requestURISplit[1].toUpperCase();
//        for (var clazz : controllerClasses) {
//            String controllerValue = ((Controller) clazz.getAnnotation(Controller.class)).value();
//            if (controllerName.equals(controllerValue.toUpperCase()))
//                return clazz;
//        }
//        return null;
//    }
//
//    public Method getControllerMethodFromURI(String requestURI) throws Exception {
//        Class<?> controllerClass = getControllerClassFromURI(requestURI);
//        // implementing...
//    }
//}
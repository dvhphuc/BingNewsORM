package org.example.scanner;

import org.example.annotation.Autowired;
import org.example.dependencyinjection.DefaultBeanFactory;
import org.example.dependencyinjection.DependencyMap;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class RootApp {
    public DependencyMap getDependencyMap() {
        return dependencyMap;
    }

    private final DependencyMap dependencyMap = new DependencyMap(new DefaultBeanFactory());

    public static List<Object> getInstances() {
        return instances;
    }

    private static List<Object> instances = new ArrayList<>();

    public RootApp() {
        //This is Root Application
    }

    public void register(Class<?> clazz) {
        var autowiredClasses = new LinkedList<Class<?>>();
        Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Autowired.class))
                .forEach(field -> autowiredClasses.add(field.getType()));

        dependencyMap.add(clazz, autowiredClasses);
        // Recursive to register all dependencies
        autowiredClasses.forEach(subclazz -> register(subclazz));
    }

    public static void addInstance(Object instance) {
        instances.add(instance);
    }

    public void createBeanInOrder() {
        instances.forEach(instance -> {
            Arrays.stream(instance.getClass().getDeclaredFields())
                    .filter(field -> field.isAnnotationPresent(Autowired.class))
                    .forEach(field -> {
                        try {
                            field.setAccessible(true);
                            field.set(instance, dependencyMap.getDependentTree().get(field.getType()).get(0).newInstance());
                        } catch (InstantiationException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    });
        });
    }

}

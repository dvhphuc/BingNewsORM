package org.example.scanner;

import org.example.annotation.Autowired;
import org.example.dependencyinjection.DefaultBeanFactory;
import org.example.dependencyinjection.DependencyMap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RootApp {
    public DependencyMap getDependencyMap() {
        return dependencyMap;
    }

    private final DependencyMap dependencyMap = new DependencyMap(new DefaultBeanFactory());

    private static List<Object> instances = new ArrayList<>();

    public RootApp() {
        //This is Root Application
    }

    public static void addInstace(Object instance) {
        instances.add(instance);
    }



    public void buidDependenciesMap(Class<?> clazz) {
        System.out.println(clazz.getSimpleName());
        if (dependencyMap.getDependencies(clazz) == null) {
            dependencyMap.add(clazz, new LinkedList<>());
        }
        var fields = clazz.getDeclaredFields();
        for (var field : fields) {
            if (!field.isAnnotationPresent(Autowired.class)) {
                continue;
            }

            if (!dependencyMap.getDependencies(clazz).contains(field.getType()))
                dependencyMap.getDependencies(clazz).add(field.getType());

            var subClass = field.getType(); // subClass is Autowired class
            buidDependenciesMap(subClass);
        }
    }

    public void register(Class<?> clazz) throws Exception {
        buidDependenciesMap(clazz);
        dependencyMap.createDependencyInstance();
    }
}

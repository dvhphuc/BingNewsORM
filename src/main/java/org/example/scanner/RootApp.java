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
    public static void addInstance(Object instance) {
        instances.add(instance);
    }

    public void buildDependencyMap(Class<?> clazz) {
        var fields = clazz.getDeclaredFields();
        var dependencies = new LinkedList<Class<?>>();
        for (var field : fields) {
            if (field.isAnnotationPresent(Autowired.class)) {
                dependencies.add(field.getType());
            }
        }
        dependencyMap.add(clazz, dependencies);
    }

    public void register(Class<?> clazz) throws Exception {
        buildDependencyMap(clazz);
        dependencyMap.createDependencyInstance();
    }

}

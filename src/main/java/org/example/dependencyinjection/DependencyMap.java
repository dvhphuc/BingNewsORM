package org.example.dependencyinjection;

import java.util.*;
import java.util.logging.Logger;

public class DependencyMap {
    private final Map<Class<?>, List<Class<?>>> dependentMap = new HashMap<>();
    private final Map<Class<?>, List<Class<?>>> parentMap = new HashMap<>();
    private final Set<Class<?>> classesNeedCreateInstance = new HashSet<>();
    private final DependencyProvider dependencyProvider;

    private static final Logger logger = Logger.getLogger(DependencyMap.class.getName());

    public DependencyMap(DependencyProvider dependencyProvider) {
        this.dependencyProvider = dependencyProvider;
    }

    public void add(Class<?> clazz, List<Class<?>> dependencies) {
        dependentMap.put(clazz, dependencies);
        classesNeedCreateInstance.add(clazz);
        for (Class<?> dependency : dependencies) {
            parentMap.computeIfAbsent(dependency, k -> new LinkedList<>()).add(clazz);
            classesNeedCreateInstance.add(dependency);
        }
    }

    public void createDependencyInstance() throws Exception {
        while (!classesNeedCreateInstance.isEmpty()) {
            Class<?> creatableClass = getCreatableClass(classesNeedCreateInstance);
            if (creatableClass == null) {
                return;
            }
            Object instance = dependencyProvider.getInstance(creatableClass);
            logger.info("Created instance of " + creatableClass.getName());
            classesNeedCreateInstance.remove(creatableClass);
            updateBeanDependencies(creatableClass); //Remove dependence of creatableClass from other classes
        }
    }

    public Class<?> getCreatableClass(Set<Class<?>> pool) {
        for (Class<?> node : pool) {
            if (dependentMap.get(node).isEmpty()) {
                return node;
            }
        }
        return null;
    }

    public void updateBeanDependencies(Class<?> node) {
        if (parentMap.get(node) == null)
            return;
        for (var parent : parentMap.get(node)) {
            dependentMap.get(parent).remove(node);
        }
    }

    public List<Class<?>> getDependencies(Class<?> clazz) {
        return dependentMap.get(clazz);
    }

    public Set<Class<?>> getClassesNeedCreateInstance() {
        return classesNeedCreateInstance;
    }
}
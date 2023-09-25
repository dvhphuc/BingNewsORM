package org.example.dependencyinjection;

import org.example.scanner.RootApp;

import java.util.*;
import java.util.logging.Logger;

public class DependencyMap {
    public Map<Class<?>, List<Class<?>>> getDependentTree() {
        return dependentTree;
    }

    private final Map<Class<?>, List<Class<?>>> dependentTree = new HashMap<>();
    public final Map<Class<?>, List<Class<?>>> parentsBean = new HashMap<>();

    private final Set<Class<?>> classesNeedCreateInstance = new HashSet<>();
    private final BeanFactory beanFactory;

    private static final Logger logger = Logger.getLogger(DependencyMap.class.getName());

    public DependencyMap(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void add(Class<?> clazz, List<Class<?>> dependencies) {
        dependentTree.put(clazz, dependencies);
        classesNeedCreateInstance.add(clazz);
        dependencies.forEach(dependency -> {
            parentsBean.computeIfAbsent(dependency, k -> new LinkedList<>());
            parentsBean.get(dependency).add(clazz);
            classesNeedCreateInstance.add(clazz);
        });
    }

    public void arrangeBeans() throws Exception {
        while (!classesNeedCreateInstance.isEmpty()) {
            Class<?> creatableClass = getCreatableClass(classesNeedCreateInstance);
             //For subClass of CreatableClass

            //var beanInstance = beanFactory.getInstance(creatableClass);
            RootApp.addInstance(creatableClass);

            logger.info("Created instance of " + creatableClass.getName());
            classesNeedCreateInstance.remove(creatableClass);
            updateDependentMap(creatableClass);
        }
    }


    public Class<?> getCreatableClass(Set<Class<?>> pool) {
        return pool.stream()
                .filter(clazz -> dependentTree.get(clazz).isEmpty())
                .findFirst()
                .orElse(null);
    }

    public void updateDependentMap(Class<?> node) {
        parentsBean.getOrDefault(node, new LinkedList<>())
                .forEach(parent -> dependentTree.get(parent).remove(node));
    }
}
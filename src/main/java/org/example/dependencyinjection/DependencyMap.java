package org.example.dependencyinjection;

import org.example.scanner.RootApp;

import java.util.*;
import java.util.logging.Logger;

public class DependencyMap {
    private final Map<Class<?>, List<Class<?>>> dependentMap = new HashMap<>();
    public final Map<Class<?>, List<Class<?>>> parentsBean = new HashMap<>();

    public Set<Class<?>> getClassesNeedCreateInstance() {
        return classesNeedCreateInstance;
    }

    private final Set<Class<?>> classesNeedCreateInstance = new HashSet<>();
    private final BeanFactory beanFactory;

    private static final Logger logger = Logger.getLogger(DependencyMap.class.getName());

    public DependencyMap(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void add(Class<?> clazz, List<Class<?>> dependencies) {
        dependentMap.put(clazz, dependencies);
        classesNeedCreateInstance.add(clazz);
        dependencies.forEach(dependency -> {
            parentsBean.computeIfAbsent(dependency, k -> new LinkedList<>());
            parentsBean.get(dependency).add(clazz);
            classesNeedCreateInstance.add(clazz);
        });
    }

    public void createDependencyInstance() throws Exception {
        while (!classesNeedCreateInstance.isEmpty()) {
            Class<?> creatableClass = getCreatableClass(classesNeedCreateInstance);

            var beanInstance = beanFactory.getInstance(creatableClass);
            RootApp.addInstance(beanInstance);

            logger.info("Created instance of " + creatableClass.getName());
            classesNeedCreateInstance.remove(creatableClass);

            updateDependentMap(creatableClass);
        }
    }

    public Class<?> getCreatableClass(Set<Class<?>> pool) {
        return pool.stream()
                .filter(clazz -> dependentMap.get(clazz).isEmpty())
                .findFirst()
                .orElse(null);
    }

    public void updateDependentMap(Class<?> node) {
        parentsBean.getOrDefault(node, new LinkedList<>())
                .forEach(parent -> dependentMap.get(parent).remove(node));
    }
    public List<Class<?>> getDependencies(Class<?> clazz) {
        return dependentMap.get(clazz);
    }

}
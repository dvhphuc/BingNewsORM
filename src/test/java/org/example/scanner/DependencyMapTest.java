package org.example.scanner;

import org.example.dependencyinjection.DefaultBeanFactory;
import org.example.dependencyinjection.DependencyMap;
import org.example.dependencyinjection.BeanFactory;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;


class DependencyMapTest {
    @Test
    void testAdd() {

        BeanFactory beanFactory = new DefaultBeanFactory();

        DependencyMap dependencyMap = new DependencyMap(beanFactory);
        dependencyMap.add(ClassA.class, new LinkedList<>(List.of(ClassB.class)));
        dependencyMap.add(ClassB.class, new LinkedList<>(List.of(ClassC.class)));
        dependencyMap.add(ClassC.class, new LinkedList<>());

        assert dependencyMap.parentsBean.get(ClassC.class).get(0) == ClassB.class;
    }

    @Test
    void testArrangeDependenciesInOrder() throws Exception {
        BeanFactory beanFactory = new DefaultBeanFactory();

        DependencyMap dependencyMap = new DependencyMap(beanFactory);
        dependencyMap.add(ClassA.class, new LinkedList<>(List.of(ClassB.class)));
        dependencyMap.add(ClassB.class, new LinkedList<>(List.of(ClassC.class)));
        dependencyMap.add(ClassC.class, new LinkedList<>(List.of(ClassC1.class)));
        dependencyMap.add(ClassC1.class, new LinkedList<>());

        dependencyMap.createDependencyInstance();

    }

    @Test
    void add() {

    }

    @Test
    void createDependencies() {
    }

    @Test
    void getCreatableClass() {
    }
}
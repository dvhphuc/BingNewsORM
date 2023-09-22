package org.example.scanner;

import org.example.dependencyinjection.DefaultDependencyProvider;
import org.example.dependencyinjection.DependencyMap;
import org.example.dependencyinjection.DependencyProvider;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;


class DependencyMapTest {
    @Test
    void testAdd() {

        DependencyProvider dependencyProvider = new DefaultDependencyProvider();

        DependencyMap dependencyMap = new DependencyMap(dependencyProvider);
        dependencyMap.add(ClassA.class, new LinkedList<>(List.of(ClassB.class)));
        dependencyMap.add(ClassB.class, new LinkedList<>(List.of(ClassC.class)));
        dependencyMap.add(ClassC.class, new LinkedList<>());

        assert dependencyMap.getDependencies(ClassA.class).size() == 1;
    }

    @Test
    void testArrangeDependenciesInOrder() throws Exception {
        DependencyProvider dependencyProvider = new DefaultDependencyProvider();

        DependencyMap dependencyMap = new DependencyMap(dependencyProvider);
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
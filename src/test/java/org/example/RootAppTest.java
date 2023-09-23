package org.example;

import org.example.scanner.ClassA;
import org.example.scanner.ClassC;
import org.example.scanner.ClassC1;
import org.example.scanner.ClassB;
import org.example.scanner.RootApp;
import org.junit.jupiter.api.Test;

public class RootAppTest {
    @Test
    void testRegisterClass() throws Exception {
        var rootApp = new RootApp();
        rootApp.register(ClassA.class);
        rootApp.getDependencyMap().getClassesNeedCreateInstance().forEach(System.out::println);
        assert rootApp.getDependencyMap().getDependencies(ClassA.class).size() == 1;
    }
}

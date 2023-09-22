package org.example;

import org.example.scanner.ClassA;
import org.example.scanner.RootApp;
import org.junit.jupiter.api.Test;

public class RootAppTest {
    @Test
    void testRegisterClass() throws Exception {
        var rootApp = new RootApp();
        rootApp.register(ClassA.class);
    }
}

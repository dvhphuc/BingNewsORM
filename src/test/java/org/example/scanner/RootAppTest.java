package org.example.scanner;

import org.junit.jupiter.api.Test;

class RootAppTest {

    @Test
    void testRegister() {
        var rootApp = new RootApp();
        rootApp.register(ClassA.class);
        assert rootApp.getDependencyMap().getDependentTree().size() == 4; // 4 means ClassA, ClassB, ClassC, ClassC1
    }

    @Test
    void testCreateBeanInstancesAfterRegister() throws Exception {
        var rootApp = new RootApp();
        rootApp.register(ClassA.class);
        rootApp.getDependencyMap().createDependencyInstance();
    }

}
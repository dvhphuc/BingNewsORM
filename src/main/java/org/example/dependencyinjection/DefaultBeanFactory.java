package org.example.dependencyinjection;

import java.util.HashMap;
import java.util.Map;

public class DefaultBeanFactory implements BeanFactory {
    private final Map<Class<?>, Object> instances = new HashMap<>();

    @Override
    public <T> T getInstance(Class<T> clazz) throws Exception {
        if (instances.containsKey(clazz)) {
            return (T) instances.get(clazz);
        }
        T instance = clazz.getDeclaredConstructor().newInstance();
        instances.put(clazz, instance);
        return instance;
    }
}
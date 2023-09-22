package org.example.dependencyinjection;

public interface BeanFactory {
    <T> T getInstance(Class<T> clazz) throws Exception;
}
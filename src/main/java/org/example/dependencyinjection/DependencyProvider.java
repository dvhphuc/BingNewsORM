package org.example.dependencyinjection;

public interface DependencyProvider {
    <T> T getInstance(Class<T> clazz) throws Exception;
}
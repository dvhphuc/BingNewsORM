package org.example.scanner;

import org.example.repository.ArticleRepository;
import org.example.repository.CrudRepository;
import org.example.repository.impl.CrudRepositoryImpl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;

public class RepositoryFactory {
    public RepositoryFactory() {
    }

    public static CrudRepository createRepoImpl(Class<?> repoInterface) {
        ParameterizedType type = (ParameterizedType) repoInterface.getGenericInterfaces()[0];
        InvocationHandler handler = new RepositoryInvocationHandler(new CrudRepositoryImpl(type));
        return (CrudRepository) Proxy.newProxyInstance(repoInterface.getClassLoader(), new Class[]{repoInterface}, handler);
    }
}

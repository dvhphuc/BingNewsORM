package org.example.repository.factory;

import org.example.DbConnection;
import org.example.repository.CrudRepository;
import org.example.repository.impl.CrudRepositoryImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;

public class RepositoryFactory {
    private static DbConnection dbConnection;
    public RepositoryFactory(DbConnection _dbConnection) {
        dbConnection = _dbConnection;
    }

    public static CrudRepository createRepoImpl(Class<?> repoInterface) throws Exception {
        ParameterizedType type = (ParameterizedType) repoInterface.getGenericInterfaces()[0];
        InvocationHandler handler = new RepositoryInvocationHandler(new CrudRepositoryImpl(type));
        return (CrudRepository) Proxy.newProxyInstance(repoInterface.getClassLoader(), new Class[]{repoInterface}, handler);
    }
}

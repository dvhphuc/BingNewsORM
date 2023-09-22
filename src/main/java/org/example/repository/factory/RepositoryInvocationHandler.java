package org.example.repository.factory;

import org.example.repository.impl.CrudRepositoryImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RepositoryInvocationHandler implements InvocationHandler {
    private CrudRepositoryImpl crudRepositoryImpl;
    public RepositoryInvocationHandler(CrudRepositoryImpl _crudRepositoryImpl) {
        crudRepositoryImpl = _crudRepositoryImpl;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(crudRepositoryImpl, args);
    }
}

package org.example.query;

import org.example.annotation.Primary;

import java.lang.reflect.Field;

public class SelectByIdQueryBuilder {
    public <T, ID> String build(Class<T> entityClass, ID id) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT ");
        query.append("*");
        query.append(" FROM ");
        query.append(entityClass.getSimpleName());
        query.append(" WHERE ");
        for (Field field : entityClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(Primary.class)) {
                query.append(field.getName());
                query.append(" = ");
                query.append(id);
                query.append(";");
                return query.toString();
            }
        }
        throw new RuntimeException("Primary key not found");
    }
}

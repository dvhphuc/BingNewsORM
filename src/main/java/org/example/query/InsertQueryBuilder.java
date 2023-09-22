package org.example.query;

import java.lang.reflect.Field;
import java.util.stream.Stream;

public class InsertQueryBuilder {
    public String build(Object object) throws IllegalAccessException {
        String className = object.getClass().getSimpleName();
        Field[] fields = object.getClass().getDeclaredFields();
        StringBuilder queryBuilder = new StringBuilder();

        buildInsertClause(queryBuilder, className);
        buildColumns(queryBuilder, fields);
        buildValues(queryBuilder, fields, object);

        return queryBuilder.toString();
    }

    private void buildInsertClause(StringBuilder queryBuilder, String className) {
        queryBuilder.append("INSERT INTO ").append(className).append(" (");
    }

    private void buildColumns(StringBuilder queryBuilder, Field[] fields) {
        Stream.of(fields)
                .forEach(field -> queryBuilder.append(field.getName()).append(", "));
        queryBuilder.setLength(queryBuilder.length() - 2); // Remove the trailing comma and space
    }

    private void buildValues(StringBuilder queryBuilder, Field[] fields, Object object) throws IllegalAccessException {
        queryBuilder.append(") VALUES (");
        for (Field field : fields) {
            field.setAccessible(true);
            queryBuilder.append("'").append(field.get(object)).append("', ");
        }
        queryBuilder.setLength(queryBuilder.length() - 2); // Remove the trailing comma and space
        queryBuilder.append(")");
    }
}

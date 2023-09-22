package org.example.query;

import java.lang.reflect.Field;

public class InsertQueryBuilder {
    public String build(Object object) {
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
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                queryBuilder.append(field.getName()).append(", ");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        queryBuilder.setLength(queryBuilder.length() - 2); // Remove the trailing comma and space
    }

    private void buildValues(StringBuilder queryBuilder, Field[] fields, Object object) {
        queryBuilder.append(") VALUES (");
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                queryBuilder.append("'").append(field.get(object)).append("', ");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        queryBuilder.setLength(queryBuilder.length() - 2); // Remove the trailing comma and space
        queryBuilder.append(")");
    }
}

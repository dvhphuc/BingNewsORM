package org.example.query;

import java.lang.reflect.Field;

public class UpdateQueryBuilder {
    public String build(Object object) throws IllegalAccessException {
        String className = object.getClass().getSimpleName();
        StringBuilder queryBuilder = new StringBuilder();

        buildUpdateClause(queryBuilder, className);
        buildSetClause(queryBuilder, object);
        buildWhereClause(queryBuilder, object);

        return queryBuilder.toString();
    }

    private void buildUpdateClause(StringBuilder queryBuilder, String className) {
        queryBuilder.append("UPDATE ").append(className).append(" SET ");
    }

    private void buildSetClause(StringBuilder queryBuilder, Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                queryBuilder.append(field.getName()).append(" = '").append(field.get(object)).append("', ");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        queryBuilder.setLength(queryBuilder.length() - 2); // Remove the trailing comma and space
    }

    private void buildWhereClause(StringBuilder queryBuilder, Object object) throws IllegalAccessException {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object fieldValue = field.get(object);
            if (fieldValue != null) {
                queryBuilder.append(" WHERE ").append(field.getName()).append(" = '").append(fieldValue).append("'");
                break; // Assuming you want to use the first non-null field as the WHERE condition
            }

        }
    }
}

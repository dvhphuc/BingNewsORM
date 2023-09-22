package org.example.query;

import java.lang.reflect.Field;

public class DeleteQueryBuilder {
    public String build(Object object) throws IllegalAccessException {
        String className = object.getClass().getSimpleName();
        StringBuilder queryBuilder = new StringBuilder();

        buildDeleteClause(queryBuilder, className);
        buildWhereClause(queryBuilder, object);

        return queryBuilder.toString();
    }

    private void buildDeleteClause(StringBuilder queryBuilder, String className) {
        queryBuilder.append("DELETE FROM ").append(className);
    }

    private void buildWhereClause(StringBuilder queryBuilder, Object object) throws IllegalAccessException {
        Field[] fields = object.getClass().getDeclaredFields();
        boolean firstField = true;

        for (Field field : fields) {
            field.setAccessible(true);
            Object fieldValue = field.get(object);
            if (fieldValue == null)
                continue;

            if (firstField) {
                queryBuilder.append(" WHERE ");
                firstField = false;
            } else {
                queryBuilder.append(" AND ");
            }
            queryBuilder.append(field.getName()).append(" = '").append(fieldValue).append("'");
        }
    }
}

package org.example.query;

public class SelectAllQueryBuilder {
    public String build(Class<?> clazz) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT ");
        query.append("*");
        query.append(" FROM ");
        query.append(clazz.getSimpleName());
        query.append(";");
        return query.toString();
    }
}

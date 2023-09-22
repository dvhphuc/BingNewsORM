package org.example.query;

import java.util.stream.Stream;

public class SelecAllOnFieldsBuilder {
    public String build(Class<?> clazz, String[] fields) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT ");
        Stream.of(fields)
                .forEach(field -> query.append(field).append(", "));
        query.setLength(query.length() - 2); // Remove the trailing comma and space
        query.append(" FROM ");
        query.append(clazz.getSimpleName());
        query.append(";");
        return query.toString();
    }
}

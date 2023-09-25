package org.example.query;

import java.util.StringJoiner;

public class SelectFieldsQueryBuilder {
    public String build(Class<?> clazz, String[] fields) {
        StringJoiner selectFields = new StringJoiner(", ");
        for (String field : fields) {
            selectFields.add(field);
        }

        return String.format("SELECT %s FROM %s;", selectFields.toString(), clazz.getSimpleName());
    }
}

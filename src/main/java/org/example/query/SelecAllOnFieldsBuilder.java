package org.example.query;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SelecAllOnFieldsBuilder {
    public String build(Class<?> clazz, String[] fields) {
        String fieldList = Stream.of(fields)
                .collect(Collectors.joining(", "));

        return String.format("SELECT %s FROM %s;", fieldList, clazz.getSimpleName());
    }

}

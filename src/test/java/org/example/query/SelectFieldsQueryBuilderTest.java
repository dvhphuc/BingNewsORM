package org.example.query;

import org.example.scanner.ClassC1;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SelectFieldsQueryBuilderTest {

    @Test
    void build() {
        var selectFieldsQueryBuilder = new SelectFieldsQueryBuilder();
        var query = selectFieldsQueryBuilder.build(ClassC1.class, new String[]{"field1", "field2"});
        System.out.println(query);
    }
}
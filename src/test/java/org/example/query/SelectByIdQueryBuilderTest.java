package org.example.query;

import org.example.model.Article;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SelectByIdQueryBuilderTest {

    @Test
    void build() {
        var queryBuilder = new SelectByIdQueryBuilder();
        var query = queryBuilder.build(Article.class, "test");
        assertEquals("SELECT * FROM Article WHERE guid = 'test';", query);
    }
}
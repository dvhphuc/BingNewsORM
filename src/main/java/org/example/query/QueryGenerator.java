package org.example.query;

public class QueryGenerator {
    public static String insertQuery(Object object) {
        return new InsertQueryBuilder().build(object);
    }

    public static String updateQuery(Object object) {
        return new UpdateQueryBuilder().build(object);
    }

    public static String deleteQuery(Object object) {
        return new DeleteQueryBuilder().build(object);
    }

    public static String selectAllQuery(Class<?> clazz) {
        return new SelectAllQueryBuilder().build(clazz);
    }

    public static <T, ID> String selectByIdQuery(Class<T> entityClass, ID id) {
        return new SelectByIdQueryBuilder().build(entityClass, id);
    }
}
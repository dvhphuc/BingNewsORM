package org.example.repository;

public interface CrudRepository<T,ID> extends Repository<T, ID> , QueryPredicateExecutor<T>, Pagination<T,ID>, SQLQueryExecutor<T> {
}

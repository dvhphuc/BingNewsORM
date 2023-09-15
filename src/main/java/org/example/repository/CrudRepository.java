package org.example.repository;

import org.example.QueryPredicateExecutor;

import java.util.function.Predicate;

public interface CrudRepository<T,ID> extends Repository<T, ID>, QueryPredicateExecutor<T> {
    Iterable<T> find(Predicate<T> predicate) throws Exception;
    long count(Predicate<T> predicate);
}

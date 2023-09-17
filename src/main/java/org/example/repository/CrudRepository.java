package org.example.repository;

import org.example.Pagination;
import org.example.QueryPredicateExecutor;

import java.util.function.Predicate;

public interface CrudRepository<T,ID> extends Repository<T, ID>, QueryPredicateExecutor<T>, Pagination<T> {
}

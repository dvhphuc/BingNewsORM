package org.example.repository;

import java.lang.reflect.Field;
import java.util.List;
import java.util.function.Predicate;

public interface QueryPredicateExecutor<T> {

    List<T> find(String query) throws Exception;
    List<T> findAll(String[] fields) throws Exception;
    List<T> find(Predicate<T> predicate) throws Exception;
    long count(Predicate<T> predicate) throws Exception;
}

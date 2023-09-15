package org.example;

import java.util.Optional;
import java.util.function.Predicate;

public interface QueryPredicateExecutor<T> {
    Iterable<T> find(Predicate<T> predicate) throws Exception;
    long count(Predicate<T> predicate);
}

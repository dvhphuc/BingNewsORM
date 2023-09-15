package org.example;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface QueryPredicateExecutor<T> {
    List<T> find(Predicate<T> predicate) throws Exception;
    long count(Predicate<T> predicate) throws Exception;
}

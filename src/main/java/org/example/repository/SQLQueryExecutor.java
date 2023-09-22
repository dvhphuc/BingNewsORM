package org.example.repository;

import java.util.List;

public interface SQLQueryExecutor<T>{
    List<T> find(String query) throws Exception;
}

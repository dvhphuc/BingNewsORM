package org.example;

import java.util.List;

public interface Pagination<T> {
    List<T> find(int page, int size) throws Exception;
}

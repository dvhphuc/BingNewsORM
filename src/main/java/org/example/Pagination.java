package org.example;

import java.util.List;

public interface Pagination<T> {
    List<T> getInPage(int page, int size) throws Exception;
}

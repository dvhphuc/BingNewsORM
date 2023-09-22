package org.example.repository.impl;

import org.example.annotation.Column;
import org.example.model.*;
import org.example.DbConnection;
import org.example.repository.Pagination;
import org.example.repository.QueryPredicateExecutor;
import org.example.query.QueryGenerator;
import org.example.repository.CrudRepository;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class CrudRepositoryImpl<T, ID> implements CrudRepository<T, ID> {
    private final Class<T> entityClass;

    private final Statement statement = DbConnection.getConnection().createStatement();


    public CrudRepositoryImpl(Class<T> entityClass) throws Exception {
        this.entityClass = entityClass;
    }

    public CrudRepositoryImpl(ParameterizedType type) throws Exception {
        entityClass = (Class<T>) type.getActualTypeArguments()[0];
    }

    @Override
    public void save(T entity) throws Exception {
        String insertQuery = QueryGenerator.insertQuery(entity);
        DbConnection.getStatement().execute(insertQuery);
    }

    @Override
    public void update(T entity) throws Exception {
        String updateQuery = QueryGenerator.updateQuery(entity);
        DbConnection.getStatement().execute(updateQuery);
    }

    @Override
    public void delete(ID id) throws Exception {
        String deleteQuery = QueryGenerator.deleteQuery(id);
        DbConnection.getStatement().execute(deleteQuery);;
    }

    @Override
    public T findById(ID id) throws Exception {
        String selectQuery = QueryGenerator.selectByIdQuery(entityClass, id);
        return getResultSet(selectQuery).get(0);
    }

    @Override
    public List<T> findAll() throws Exception {
        String selectQuery = QueryGenerator.selectAllQuery(entityClass);
        return getResultSet(selectQuery);
    }

    @Override
    public List<T> find(String query) throws Exception {
        return getResultSet(query);
    }

    @Override
    public List<T> find(Predicate<T> predicate) throws Exception {
        var selectAllQuery = QueryGenerator.selectAllQuery(entityClass);
        List<T> list = getResultSet(selectAllQuery);
        List<T> filteredList = new ArrayList<>();
        for (T entity : list) {
            if (predicate.test(entity)) {
                filteredList.add(entity);
            }
        }
        return filteredList;
    }

    private List<T> getResultSet(String sqlQuery) throws Exception {
        var resultSet = statement.executeQuery(sqlQuery);

        Field[] fields = entityClass.getDeclaredFields();

        List<T> list = new ArrayList<>();

        while (resultSet.next()) {
            T entity = entityClass.getDeclaredConstructor().newInstance();
            for (Field field : fields) {
                field.setAccessible(true);
                field.set(entity, resultSet.getObject(field.getName()));
            }
            list.add(entity);
        }
        return list;
    }

    @Override
    public long count(Predicate<T> predicate) throws Exception {
        var list = getResultSet(QueryGenerator.selectAllQuery(entityClass));
        return list.stream().filter(predicate).count();
    }

    @Override
    public List<T> getInPage(int page, int size) throws Exception {
        var selectAllQuery = QueryGenerator.selectAllQuery(entityClass);
        List<T> list = getResultSet(selectAllQuery);
        List<T> filteredList = new ArrayList<>();
        for (int i = (page - 1) * size; i < page * size; i++) {
            filteredList.add(list.get(i));
        }
        return filteredList;
    }

}

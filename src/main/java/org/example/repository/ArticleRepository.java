package org.example.repository;
import org.example.model.Article;

import java.util.List;
import java.util.function.Predicate;

public class ArticleRepository implements CrudRepository<Article, String> {
    private final CrudRepository<Article, String> repository;
    public ArticleRepository(CrudRepository _repository) {
        repository = _repository;
    }

    @Override
    public void save(Article entity) throws Exception {
        repository.save(entity);
    }

    @Override
    public void update(Article entity) {
        repository.update(entity);
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Article findById(String id) throws Exception {
        return repository.findById(id);
    }

    @Override
    public List<Article> findAll() throws Exception {
        return repository.findAll();
    }

    @Override
    public List<Article> find(Predicate<Article> predicate) throws Exception {
        return repository.find(predicate);
    }

    @Override
    public long count(Predicate<Article> predicate) {
        return 0;
    }
}
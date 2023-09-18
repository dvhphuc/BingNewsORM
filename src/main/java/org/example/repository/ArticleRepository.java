package org.example.repository;
import org.example.model.Article;

import java.util.List;
import java.util.function.Predicate;

public class ArticleRepository {
    private final CrudRepository<Article, String> repository;
    public ArticleRepository(CrudRepository _repository) {
        repository = _repository;
    }

    public void save(Article entity) throws Exception {
        repository.save(entity);
    }

    public void update(Article entity) {
        repository.update(entity);
    }

    public void delete(String id) {

    }

    public Article findById(String id) throws Exception {
        return repository.findById(id);
    }

    public List<Article> findAll() throws Exception {
        return repository.findAll();
    }

    public List<Article> getInPage(Predicate<Article> predicate) throws Exception {
        return repository.find(predicate);
    }

    public long count(Predicate<Article> predicate) {
        return 0;
    }

    public List<Article> getInPage(int page, int size) throws Exception {
        return repository.getInPage(page, size);
    }
}
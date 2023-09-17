package org.example.repository;

import org.example.model.AdTopic;

import java.util.List;
import java.util.function.Predicate;

public class AdTopicRepository {
    private CrudRepository<AdTopic, String> repository;
    public AdTopicRepository(CrudRepository _repository) {
        repository = _repository;
    }

    public void save(AdTopic entity) throws Exception {
        repository.save(entity);
    }

    public void update(AdTopic entity) {
        repository.update(entity);
    }

    public void delete(String id) {

    }

    public AdTopic findById(String id) throws Exception {
        return repository.findById(id);
    }

    public List<AdTopic> findAll() throws Exception {
        return repository.findAll();
    }

    public List<AdTopic> find(Predicate<AdTopic> predicate) throws Exception {
        return repository.find(predicate);
    }

    public long count(Predicate<AdTopic> predicate) throws Exception {
        return repository.count(predicate);
    }
}

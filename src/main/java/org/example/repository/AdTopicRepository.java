package org.example.repository;

import org.example.model.AdTopic;

import java.util.List;
import java.util.function.Predicate;

public class AdTopicRepository implements CrudRepository<AdTopic, String> {
    private CrudRepository<AdTopic, String> repository;
    public AdTopicRepository(CrudRepository _repository) {
        repository = _repository;
    }

    @Override
    public void save(AdTopic entity) throws Exception {
        repository.save(entity);
    }

    @Override
    public void update(AdTopic entity) {
        repository.update(entity);
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public AdTopic findById(String id) throws Exception {
        return repository.findById(id);
    }

    @Override
    public List<AdTopic> findAll() throws Exception {
        return repository.findAll();
    }

    @Override
    public List<AdTopic> find(Predicate<AdTopic> predicate) throws Exception {
        return repository.find(predicate);
    }

    @Override
    public long count(Predicate<AdTopic> predicate) throws Exception {
        return repository.count(predicate);
    }
}

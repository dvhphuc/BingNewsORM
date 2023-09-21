package org.example.repository;

import org.example.model.Article;
import org.example.repository.impl.CrudRepositoryImpl;

import java.util.List;

public interface ArticleRepository extends CrudRepository<Article,String> {
}

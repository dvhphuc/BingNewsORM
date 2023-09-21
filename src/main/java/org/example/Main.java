
package org.example;

import configuration.ConfigReader;
import org.example.repository.ArticleRepository;
import org.example.scanner.RepositoryFactory;

public class Main {
    static public void main(String[] args) throws Exception {
        DbConnection dbConnection = new DbConnection(ConfigReader.getConnectionString());

        Class<?> interfaceToImplement = ArticleRepository.class;
        ArticleRepository articleRepository = (ArticleRepository) RepositoryFactory.createRepoImpl(interfaceToImplement);
        System.out.println(articleRepository.findAll().size());
    }



}
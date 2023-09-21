
package org.example;

import configuration.ConfigReader;
import org.example.repository.ArticleRepository;
import org.example.repository.factory.RepositoryFactory;

import java.util.logging.Logger;

public class Main {
    static Logger logger = Logger.getLogger(Main.class.getName());
    static public void main(String[] args) throws Exception {

        Class<?> interfaceToImplement = ArticleRepository.class;
        ArticleRepository articleRepository = (ArticleRepository) RepositoryFactory.createRepoImpl(interfaceToImplement);
        System.out.println(articleRepository.findAll().size());
    }



}
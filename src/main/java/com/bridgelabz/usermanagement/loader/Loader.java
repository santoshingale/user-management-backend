package com.bridgelabz.usermanagement.loader;

import com.bridgelabz.usermanagement.elastic.UserDataElasticRepo;
import com.bridgelabz.usermanagement.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Component
public class Loader {

    @Autowired
    UserDataElasticRepo elasticsearchRepository;

    @Autowired
    UserDataRepository userDataRepository;

    @Autowired
    ElasticsearchOperations operations;

    @PostConstruct
    @Transactional
    public void loadAll() {
        elasticsearchRepository.saveAll(userDataRepository.findAll());
    }
}

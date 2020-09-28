package com.bridgelabz.usermanagement.elastic;

import com.bridgelabz.usermanagement.model.UserData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDataElasticRepo extends ElasticsearchRepository<UserData, Long> {
    List<UserData> findAll();

    List<UserData> findByFirstname(String firstname);

    //    @Query("{\"bool\": {\"should\": [{\"match\": {\"firstname\": \"?0\"}},{\"match\": {\"lastname\": \"?0\"}}]}}")
    //    @Query("{ \"wildcard\": { \"lastname\" : \"*til*\" }}")
    @Query("{\"query_string\" : {\"query\" : \"*?0*\",\"fields\" : [\"firstname\",\"lastname\",\"email\"]}}")
    List<UserData> customSearch(String lastname, Pageable pageable);
}

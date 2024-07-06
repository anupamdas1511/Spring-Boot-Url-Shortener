package com.anupam.url_shortner.repositories;

import com.anupam.url_shortner.models.Url;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends MongoRepository<Url, String> {
    Url findByKey(String key);
}

package com.anupam.url_shortner.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.anupam.url_shortner.repositories")
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Value("${spring.data.mongodb.uri}")
    private String mongoClientUri;

    @Override
    protected String getDatabaseName() {
        return "shortened_urls";
    }

    @Override
    public MongoClient mongoClient() {
        return MongoClients.create(mongoClientUri);
    }
}

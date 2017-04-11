package com.hkarabakla.example.mongo.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

@Configuration
@PropertySource(value = "application.properties")
public class MongoConfig extends AbstractMongoConfiguration {

    @Value(value = "${spring.data.mongodb.database}")
    private String dbNameForTest;

    @Value(value = "${spring.data.mongodb.host}")
    private String dbHostAddress;

    @Bean
    public GridFsTemplate gridFsTemplate() throws Exception {
        return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
    }

    @Override
    protected String getDatabaseName() {
        return dbNameForTest;
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient(dbHostAddress);
    }
}

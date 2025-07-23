package com.example.Couchbase.config;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.manager.query.CreatePrimaryQueryIndexOptions;
import com.example.Couchbase.model.Airline;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.core.CouchbaseTemplate;

@Configuration
public class CouchbaseConfiguration {

    @Autowired
    CouchbaseTemplate couchbaseTemplate;

    @Autowired
    Cluster cluster;

    /**
     * Add the _class field to all Airline documents
     */
    @PostConstruct
    private void postConstruct() {
        cluster.queryIndexes().createPrimaryIndex(
                couchbaseTemplate.getBucketName(),
                CreatePrimaryQueryIndexOptions.createPrimaryQueryIndexOptions().ignoreIfExists(true)
        );

        // Need to post-process travel data to add _class attribute
        cluster.query("update `travel-sample` set _class='" + Airline.class.getName() + "' where type = 'airline'");
    }
}

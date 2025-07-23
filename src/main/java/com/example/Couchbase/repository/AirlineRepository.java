package com.example.Couchbase.repository;

import com.example.Couchbase.model.Airline;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirlineRepository extends CrudRepository<Airline, String> {

    List<Airline> findByIata(String code);
}

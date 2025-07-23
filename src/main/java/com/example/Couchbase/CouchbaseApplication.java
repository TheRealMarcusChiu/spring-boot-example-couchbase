package com.example.Couchbase;

import com.example.Couchbase.model.Airline;
import com.example.Couchbase.repository.AirlineRepository;
import org.instancio.Instancio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class CouchbaseApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CouchbaseApplication.class, args);
	}

	@Autowired
	AirlineRepository airlineRepository;

	@Override
	public void run(String... args) throws Exception {
		airlineRepository.deleteAll();

		// 1. save
		Airline airline = Instancio.create(Airline.class);
		airline.setIata("iata");
		airline = airlineRepository.save(airline);
		System.out.println("1. " + airline);

		// 2. saveAll
		int count = 10;
		var airlines = Instancio.ofList(Airline.class)
				.size(count)
				.create();
		System.out.println("2. saveAll");
		airlineRepository.saveAll(airlines)
				.forEach(System.out::println);

		// 3. Query Methods
		System.out.println("3. query methods");
		airlineRepository.findByIata("iata").forEach(System.out::println);
	}
}

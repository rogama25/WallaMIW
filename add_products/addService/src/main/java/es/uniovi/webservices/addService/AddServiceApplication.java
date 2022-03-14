package es.uniovi.webservices.addService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class AddServiceApplication {


	public static void main(String[] args) {
		SpringApplication.run(AddServiceApplication.class, args);
	}



}

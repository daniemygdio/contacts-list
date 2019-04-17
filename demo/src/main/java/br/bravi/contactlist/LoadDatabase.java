package br.bravi.contactlist;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class LoadDatabase {

	@Bean
	CommandLineRunner initDatabase(PersonRepository repository) {
		return args -> {
			log.info("Preloading " + repository.save(new Person("Bilbo Baggins")));
			log.info("Preloading " + repository.save(new Person("Frodo Baggins")));
		};
	}
}
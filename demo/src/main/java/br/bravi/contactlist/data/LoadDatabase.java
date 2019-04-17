package br.bravi.contactlist.data;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.bravi.contactlist.model.Contact;
import br.bravi.contactlist.model.ContactType;
import br.bravi.contactlist.model.Person;

@Configuration
@Slf4j
class LoadDatabase {
	Person personOne = new Person("Hermione");
	Person personTwo = new Person("Ronald");
	@Bean
	CommandLineRunner initDatabase(PersonRepository repository) {
		return args -> {
			log.info("Preloading " + repository.save(personOne));
			log.info("Preloading " + repository.save(personTwo));
		};
	}
}
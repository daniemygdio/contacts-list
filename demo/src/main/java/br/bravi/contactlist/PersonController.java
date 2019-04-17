package br.bravi.contactlist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
	
	@Autowired
	private PersonRepository repository;
	
	@GetMapping("/people")
	List<Person> all() {
		return repository.findAll();
	}

	@PostMapping("/people")
	Person newPerson(@RequestBody Person newPerson) {
		return repository.save(newPerson);
	}
	
	@GetMapping("/person/{id}")
	Person one(@PathVariable Long id) {

		return repository.findById(id)
			.orElseThrow(() -> new PersonNotFoundException(id));
	}

	@PutMapping("/person/{id}")
	Person replacePerson(@RequestBody Person newPerson, @PathVariable Long id) {

		return repository.findById(id)
			.map(person -> {
				person.setName(newPerson.getName());
				return repository.save(person);
			})
			.orElseGet(() -> {
				newPerson.setId(id);
				return repository.save(newPerson);
			});
	}

	@DeleteMapping("/person/{id}")
	void deletePerson(@PathVariable Long id) {
		repository.deleteById(id);
	}
}

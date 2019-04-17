package br.bravi.contactlist.controller;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.bravi.contactlist.data.PersonRepository;
import br.bravi.contactlist.data.PersonResourceAssembler;
import br.bravi.contactlist.exception.PersonNotFoundException;
import br.bravi.contactlist.model.Person;

@RestController
public class PersonController {
	
	private PersonRepository repository;
	
	private PersonResourceAssembler assembler;
	
	public PersonController(PersonRepository repository, PersonResourceAssembler assembler) {
		this.repository = repository;
		this.assembler = assembler;
	}
	
	@GetMapping("/people")
	public Resources<Resource<Person>> all() {

		List<Resource<Person>> people = repository.findAll().stream()
				.map(assembler::toResource)
				.collect(Collectors.toList());

		return new Resources<>(people,
			linkTo(methodOn(PersonController.class).all()).withSelfRel());
	}

	@PostMapping("/people")
	public Person newPerson(@RequestBody Person newPerson) {
		return repository.save(newPerson);
	}
	
	@GetMapping("/people/{id}")
	public Resource<Person> one(@PathVariable Long id) {

		Person person = repository.findById(id)
			.orElseThrow(() -> new PersonNotFoundException(id));

		return assembler.toResource(person);
	}
	
	@PutMapping("/people/{id}")
	public Person replacePerson(@RequestBody Person newPerson, @PathVariable Long id) {

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

	@DeleteMapping("/people/{id}")
	void deletePerson(@PathVariable Long id) {
		repository.deleteById(id);
	}
}

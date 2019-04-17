package br.bravi.contactlist.data;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import br.bravi.contactlist.controller.PersonController;
import br.bravi.contactlist.model.Person;

@Component
public class PersonResourceAssembler implements ResourceAssembler<Person, Resource<Person>> {

	@Override
	public Resource<Person> toResource(Person person) {
		return new Resource<>(person,
				linkTo(methodOn(PersonController.class).one(person.getId())).withSelfRel(),
				linkTo(methodOn(PersonController.class).all()).withRel("people"));
	}

}

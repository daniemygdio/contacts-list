package br.bravi.contactlist;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

@Component
public class PersonResourceAssembler implements ResourceAssembler<Person, Resource<Person>> {

	@Override
	public Resource<Person> toResource(Person person) {
		return new Resource<>(person,
				linkTo(methodOn(PersonController.class).one(person.getId())).withSelfRel(),
				linkTo(methodOn(PersonController.class).all()).withRel("people"));
	}

}

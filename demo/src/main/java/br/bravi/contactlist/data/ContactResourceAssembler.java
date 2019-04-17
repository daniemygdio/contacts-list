package br.bravi.contactlist.data;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import br.bravi.contactlist.controller.ContactController;
import br.bravi.contactlist.model.Contact;

@Component
public class ContactResourceAssembler implements ResourceAssembler<Contact, Resource<Contact>> {

	@Override
	public Resource<Contact> toResource(Contact contact) {
		return new Resource<>(contact,
				//linkTo(methodOn(ContactController.class).one(contact.getId())).withSelfRel(),
				linkTo(methodOn(ContactController.class).all(contact.getId())).withRel("contact"));
	}

}

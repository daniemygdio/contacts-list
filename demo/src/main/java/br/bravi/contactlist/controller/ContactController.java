package br.bravi.contactlist.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.bravi.contactlist.data.ContactRepository;
import br.bravi.contactlist.data.ContactResourceAssembler;
import br.bravi.contactlist.data.PersonRepository;
import br.bravi.contactlist.exception.PersonNotFoundException;
import br.bravi.contactlist.model.Contact;

@RestController
public class ContactController {
	@Autowired
	private ContactRepository contactRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private ContactResourceAssembler assembler;
	
	@GetMapping("/people/{personId}/contacts")
    public Resources<Resource<Contact>> all(@PathVariable (value = "personId") Long personId) {
        List<Resource<Contact>> contacts = contactRepository.findByPersonId(personId).stream()
        		.map(assembler::toResource)
        		.collect(Collectors.toList());
        
        return new Resources<>(contacts, 
        		linkTo(methodOn(ContactController.class).all(personId)).withSelfRel());
    }

	@PostMapping("/people/{personId}/contacts")
    public Contact createComment(@PathVariable (value = "personId") Long personId,
                                 @Valid @RequestBody Contact contact) {
        return personRepository.findById(personId).map(person -> {
            contact.setPerson(person);
            return contactRepository.save(contact);
        }).orElseThrow(() -> new PersonNotFoundException(personId));
    }
	
}

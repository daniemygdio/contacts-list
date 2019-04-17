package br.bravi.contactlist.model;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = "contacts")
@Entity
public class Person {
	private @Id @GeneratedValue @Column(name="personId") Long id;
	private String name;

	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private Set<Contact> contacts;
	
	public Person() {
		
	}
	
	public Person(String name) {
		this.name = name;
	}
	
	public Person(String name, Contact... contacts) {
        this.name = name;
        this.contacts = Stream.of(contacts).collect(Collectors.toSet());
        this.contacts.forEach(x -> { 
        	x.setContact(x.getContact());
        	x.setContactType(x.getContactType());
        	x.setPerson(x.getPerson());
        });
    }
}
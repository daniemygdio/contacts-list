package br.bravi.contactlist.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Contact {
	@Id
	@GeneratedValue 
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="personId")
	private Person person;
	
	private ContactType contactType;
	
	private String contact;
	
	@JsonIgnore
	Person getPerson() {
		return this.person;
	}
	
	
	public Contact() {
		
	}

	public Contact(ContactType contactType, String contact) {
		super();
		this.contactType = contactType;
		this.contact = contact;
	}
	
	public Contact(Person person, ContactType contactType, String contact) {
		super();
		this.person = person;
		this.contactType = contactType;
		this.contact = contact;
	}
}
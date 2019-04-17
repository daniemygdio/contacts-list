package br.bravi.contactlist;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Person {
	private @Id @GeneratedValue Long id;
	private String name;

	public Person(String name) {
		this.name = name;
	}
	
	public Person() {
		
	}
}

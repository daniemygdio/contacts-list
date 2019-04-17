package br.bravi.contactlist.exception;

public class PersonNotFoundException extends RuntimeException {
	public PersonNotFoundException(Long id) {
		super("Could not find employee " + id);
	}
}

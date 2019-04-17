package br.bravi.contactlist.exception;

public class ContactNotFoundException extends RuntimeException {
	public ContactNotFoundException(Long id) {
		super("Could not find employee " + id);
	}
}

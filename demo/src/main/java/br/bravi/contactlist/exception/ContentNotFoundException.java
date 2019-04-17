package br.bravi.contactlist.exception;

public class ContentNotFoundException extends RuntimeException {
	public ContentNotFoundException(Long personId, Long contactId) {
		super("Could not find contact " +contactId+ " of person" + personId);
	}
}

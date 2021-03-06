package br.bravi.contactlist.data;

import java.util.Set;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;

import br.bravi.contactlist.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long>{
	Set<Contact> findByPersonId(Long personId);
	Stream<Contact> findByIdAndPersonId(Long id, Long personId);
}

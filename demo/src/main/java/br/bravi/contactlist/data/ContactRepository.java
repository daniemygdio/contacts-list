package br.bravi.contactlist.data;

import org.springframework.data.jpa.repository.JpaRepository;

import br.bravi.contactlist.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long>{

}

package br.bravi.contactlist.data;

import org.springframework.data.jpa.repository.JpaRepository;

import br.bravi.contactlist.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

}

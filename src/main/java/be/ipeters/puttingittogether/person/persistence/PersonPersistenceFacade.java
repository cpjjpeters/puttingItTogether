package be.ipeters.puttingittogether.person.persistence;

import be.ipeters.puttingittogether.person.model.Gender;
import be.ipeters.puttingittogether.person.model.Person;

import java.time.LocalDate;
import java.util.List;

public interface PersonPersistenceFacade {

    Person save(Person person);
    List<Person> findAll();
    Person findById(Long id);
    void delete(Person person);
    void deleteById(Long id);
    Person update(Person person);
    List<Person> findByFirstName(String firstName);
    List<Person> findByLastName(String lastName);
    List<Person> findByGender(Gender gender);
    List<Person> findByAddressId(Long addressId);
    List<Person> findByEmployerId(Long employerId);
}
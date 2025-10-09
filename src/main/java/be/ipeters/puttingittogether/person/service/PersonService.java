package be.ipeters.puttingittogether.person.service;

import be.ipeters.puttingittogether.person.model.Gender;
import be.ipeters.puttingittogether.person.model.Person;
import be.ipeters.puttingittogether.person.persistence.PersonPersistenceFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PersonService {

    private final PersonPersistenceFacade personPersistenceFacade;

    public PersonService(PersonPersistenceFacade personPersistenceFacade) {
        this.personPersistenceFacade = personPersistenceFacade;
    }

    public List<Person> findAll() {
        return this.personPersistenceFacade.findAll();
    }

    public Person findById(Long id) {
        return this.personPersistenceFacade.findById(id);
    }

    public Person save(Person person) {
        return this.personPersistenceFacade.save(person);
    }

    public Person update(Person person) {
        return this.personPersistenceFacade.update(person);
    }

    public void deleteById(Long id) {
        this.personPersistenceFacade.deleteById(id);
    }

    public void delete(Person person) {
        this.personPersistenceFacade.delete(person);
    }

    public List<Person> findByFirstName(String firstName) {
        return this.personPersistenceFacade.findByFirstName(firstName);
    }

    public List<Person> findByLastName(String lastName) {
        return this.personPersistenceFacade.findByLastName(lastName);
    }

    public List<Person> findByGender(Gender gender) {
        return this.personPersistenceFacade.findByGender(gender);
    }

    public List<Person> findByAddressId(Long addressId) {
        return this.personPersistenceFacade.findByAddressId(addressId);
    }

    public List<Person> findByEmployerId(Long employerId) {
        return this.personPersistenceFacade.findByEmployerId(employerId);
    }
}
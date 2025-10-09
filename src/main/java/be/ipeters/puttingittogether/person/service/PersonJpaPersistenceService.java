package be.ipeters.puttingittogether.person.service;

import be.ipeters.puttingittogether.person.jpa.entity.PersonJpaEntity;
import be.ipeters.puttingittogether.person.jpa.mapper.PersonJpaDaoMapper;
import be.ipeters.puttingittogether.person.jpa.repository.PersonJpaRepository;
import be.ipeters.puttingittogether.person.model.Gender;
import be.ipeters.puttingittogether.person.model.Person;
import be.ipeters.puttingittogether.person.persistence.PersonPersistenceFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PersonJpaPersistenceService implements PersonPersistenceFacade {

    private final PersonJpaRepository personJpaRepository;
    private final PersonJpaDaoMapper personJpaDaoMapper;

    public PersonJpaPersistenceService(PersonJpaRepository personJpaRepository, PersonJpaDaoMapper personJpaDaoMapper) {
        this.personJpaRepository = personJpaRepository;
        this.personJpaDaoMapper = personJpaDaoMapper;
    }

    @Override
    public Person save(Person person) {
        final PersonJpaEntity personJpaEntity = this.personJpaRepository.save(personJpaDaoMapper.modelToJpaEntity(person));
        log.debug("Person JPA = {}", personJpaEntity);
        return this.personJpaDaoMapper.jpaEntityToModel(personJpaEntity);
    }

    @Override
    public List<Person> findAll() {
        return this.personJpaRepository.findAll()
                .stream()
                .map(this.personJpaDaoMapper::jpaEntityToModel)
                .collect(Collectors.toList());
    }

    @Override
    public Person findById(Long id) {
        return this.personJpaRepository.findById(id)
                .map(this.personJpaDaoMapper::jpaEntityToModel)
                .orElseThrow(()
                        -> new NoSuchElementException(
                                "NO PERSON PRESENT WITH ID = " + id));
    }

    @Override
    public void delete(Person person) {
        this.personJpaRepository.delete(this.personJpaDaoMapper.modelToJpaEntity(person));
    }

    @Override
    public void deleteById(Long id) {
        this.personJpaRepository.deleteById(id);
    }

    @Override
    public Person update(Person person) {
        return this.personJpaDaoMapper.jpaEntityToModel(
                this.personJpaRepository.save(this.personJpaDaoMapper.modelToJpaEntity(person)));
    }

    @Override
    public List<Person> findByFirstName(String firstName) {
        return this.personJpaRepository.findByFirstName(firstName)
                .stream()
                .map(this.personJpaDaoMapper::jpaEntityToModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> findByLastName(String lastName) {
        return this.personJpaRepository.findByLastName(lastName)
                .stream()
                .map(this.personJpaDaoMapper::jpaEntityToModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> findByGender(Gender gender) {
        return this.personJpaRepository.findByGender(gender)
                .stream()
                .map(this.personJpaDaoMapper::jpaEntityToModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> findByAddressId(Long addressId) {
        return this.personJpaRepository.findByAddressId(addressId)
                .stream()
                .map(this.personJpaDaoMapper::jpaEntityToModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> findByEmployerId(Long employerId) {
        return this.personJpaRepository.findByEmployerId(employerId)
                .stream()
                .map(this.personJpaDaoMapper::jpaEntityToModel)
                .collect(Collectors.toList());
    }
}
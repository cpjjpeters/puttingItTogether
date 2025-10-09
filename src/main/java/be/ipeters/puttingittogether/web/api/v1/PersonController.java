package be.ipeters.puttingittogether.web.api.v1;

import be.ipeters.puttingittogether.person.model.Gender;
import be.ipeters.puttingittogether.person.model.Person;
import be.ipeters.puttingittogether.person.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/persons")
@CrossOrigin(originPatterns = "*")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> persons = personService.findAll();
        return ResponseEntity.ok(persons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        try {
            Person person = personService.findById(id);
            return ResponseEntity.ok(person);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        Person savedPerson = personService.save(person);
        return ResponseEntity.ok(savedPerson);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person person) {
        try {
            person.setId(id);
            Person updatedPerson = personService.update(person);
            return ResponseEntity.ok(updatedPerson);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        try {
            personService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search/firstName/{firstName}")
    public ResponseEntity<List<Person>> findByFirstName(@PathVariable String firstName) {
        List<Person> persons = personService.findByFirstName(firstName);
        return ResponseEntity.ok(persons);
    }

    @GetMapping("/search/lastName/{lastName}")
    public ResponseEntity<List<Person>> findByLastName(@PathVariable String lastName) {
        List<Person> persons = personService.findByLastName(lastName);
        return ResponseEntity.ok(persons);
    }

    @GetMapping("/search/gender/{gender}")
    public ResponseEntity<List<Person>> findByGender(@PathVariable Gender gender) {
        List<Person> persons = personService.findByGender(gender);
        return ResponseEntity.ok(persons);
    }

    @GetMapping("/search/address/{addressId}")
    public ResponseEntity<List<Person>> findByAddressId(@PathVariable Long addressId) {
        List<Person> persons = personService.findByAddressId(addressId);
        return ResponseEntity.ok(persons);
    }

    @GetMapping("/search/employer/{employerId}")
    public ResponseEntity<List<Person>> findByEmployerId(@PathVariable Long employerId) {
        List<Person> persons = personService.findByEmployerId(employerId);
        return ResponseEntity.ok(persons);
    }
}
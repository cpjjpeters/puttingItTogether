package be.ipeters.puttingittogether.web.mvc;

import be.ipeters.puttingittogether.address.service.AddressService;
import be.ipeters.puttingittogether.employer.service.EmployerService;
import be.ipeters.puttingittogether.person.model.Gender;
import be.ipeters.puttingittogether.person.model.Person;
import be.ipeters.puttingittogether.person.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/persons")
public class PersonMvcController {

    private final PersonService personService;
    private final AddressService addressService;
    private final EmployerService employerService;

    public PersonMvcController(PersonService personService, AddressService addressService, EmployerService employerService) {
        this.personService = personService;
        this.addressService = addressService;
        this.employerService = employerService;
    }

    @GetMapping
    public String listPersons(Model model) {
        List<Person> persons = personService.findAll();
        model.addAttribute("persons", persons);
        return "persons/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("person", new Person());
        model.addAttribute("addresses", addressService.findAll());
        model.addAttribute("employers", employerService.findAll());
        model.addAttribute("genders", Gender.values());
        return "persons/form";
    }

    @GetMapping("/{id}")
    public String showPerson(@PathVariable Long id, Model model) {
        try {
            Person person = personService.findById(id);
            // Load related entities for display
            if (person.getAddressId() != null) {
                person.setAddress(addressService.findById(person.getAddressId()));
            }
            model.addAttribute("person", person);
            if (person.getEmployerId() != null) {
                model.addAttribute("employer", employerService.findById(person.getEmployerId()));
            }
            return "persons/view";
        } catch (Exception e) {
            return "redirect:/persons?error=notfound";
        }
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        try {
            Person person = personService.findById(id);
            model.addAttribute("person", person);
            model.addAttribute("addresses", addressService.findAll());
            model.addAttribute("employers", employerService.findAll());
            model.addAttribute("genders", Gender.values());
            return "persons/form";
        } catch (Exception e) {
            return "redirect:/persons?error=notfound";
        }
    }

    @PostMapping
    public String savePerson(@ModelAttribute Person person, RedirectAttributes redirectAttributes) {
        try {
            if (person.getId() == null) {
                personService.save(person);
                redirectAttributes.addFlashAttribute("success", "Person created successfully!");
            } else {
                personService.update(person);
                redirectAttributes.addFlashAttribute("success", "Person updated successfully!");
            }
            return "redirect:/persons";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error saving person: " + e.getMessage());
            return "redirect:/persons/new";
        }
    }

    @PostMapping("/{id}/delete")
    public String deletePerson(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            personService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Person deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error deleting person: " + e.getMessage());
        }
        return "redirect:/persons";
    }

    @GetMapping("/search")
    public String searchPersons(@RequestParam String term, @RequestParam(required = false) String type, Model model) {
        List<Person> persons;
        
        if ("firstName".equals(type)) {
            persons = personService.findByFirstName(term);
        } else if ("lastName".equals(type)) {
            persons = personService.findByLastName(term);
        } else {
            // Search in both first and last names
            List<Person> firstNameResults = personService.findByFirstName(term);
            List<Person> lastNameResults = personService.findByLastName(term);
            persons = firstNameResults;
            // Add lastName results that aren't already in firstNameResults
            lastNameResults.stream()
                .filter(p -> firstNameResults.stream().noneMatch(fp -> fp.getId().equals(p.getId())))
                .forEach(persons::add);
        }
        
        model.addAttribute("persons", persons);
        model.addAttribute("searchTerm", term);
        model.addAttribute("searchType", type);
        return "persons/list";
    }
}
package be.ipeters.puttingittogether.web.mvc;

import be.ipeters.puttingittogether.address.service.AddressService;
import be.ipeters.puttingittogether.person.service.PersonService;
import be.ipeters.puttingittogether.employer.service.EmployerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final AddressService addressService;
    private final PersonService personService;
    private final EmployerService employerService;

    public HomeController(AddressService addressService, PersonService personService, EmployerService employerService) {
        this.addressService = addressService;
        this.personService = personService;
        this.employerService = employerService;
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("addressCount", addressService.findAll().size());
        model.addAttribute("personCount", personService.findAll().size());
        model.addAttribute("employerCount", employerService.findAll().size());
        return "index";
    }
}

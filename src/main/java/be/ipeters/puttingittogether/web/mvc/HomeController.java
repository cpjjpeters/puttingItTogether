package be.ipeters.puttingittogether.web.mvc;

import be.ipeters.puttingittogether.address.service.AddressService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final AddressService addressService;

    public HomeController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("addressCount", addressService.findAll().size());
        return "index";
    }
}
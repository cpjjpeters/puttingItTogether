package be.ipeters.puttingittogether.web.mvc;

import be.ipeters.puttingittogether.address.model.Address;
import be.ipeters.puttingittogether.address.service.AddressService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/addresses")
public class AddressMvcController {

    private final AddressService addressService;

    public AddressMvcController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public String listAddresses(Model model) {
        List<Address> addresses = addressService.findAll();
        model.addAttribute("addresses", addresses);
        return "addresses/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("address", new Address());
        return "addresses/form";
    }

    @GetMapping("/{id}")
    public String showAddress(@PathVariable Long id, Model model) {
        try {
            Address address = addressService.findById(id);
            model.addAttribute("address", address);
            return "addresses/view";
        } catch (Exception e) {
            return "redirect:/addresses?error=notfound";
        }
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        try {
            Address address = addressService.findById(id);
            model.addAttribute("address", address);
            return "addresses/form";
        } catch (Exception e) {
            return "redirect:/addresses?error=notfound";
        }
    }

    @PostMapping
    public String saveAddress(@ModelAttribute Address address, RedirectAttributes redirectAttributes) {
        try {
            if (address.getId() == null) {
                addressService.save(address);
                redirectAttributes.addFlashAttribute("success", "Address created successfully!");
            } else {
                addressService.update(address);
                redirectAttributes.addFlashAttribute("success", "Address updated successfully!");
            }
            return "redirect:/addresses";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error saving address: " + e.getMessage());
            return "redirect:/addresses/new";
        }
    }

    @PostMapping("/{id}/delete")
    public String deleteAddress(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            addressService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Address deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error deleting address: " + e.getMessage());
        }
        return "redirect:/addresses";
    }

    @GetMapping("/search")
    public String searchAddresses(@RequestParam String term, Model model) {
        List<Address> addresses = addressService.searchByCityOrStreet(term);
        model.addAttribute("addresses", addresses);
        model.addAttribute("searchTerm", term);
        return "addresses/list";
    }
}
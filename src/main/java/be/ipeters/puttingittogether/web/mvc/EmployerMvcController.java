package be.ipeters.puttingittogether.web.mvc;

import be.ipeters.puttingittogether.employer.model.Employer;
import be.ipeters.puttingittogether.employer.service.EmployerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/employers")
public class EmployerMvcController {

    private final EmployerService employerService;

    public EmployerMvcController(EmployerService employerService) {
        this.employerService = employerService;
    }

    @GetMapping
    public String listEmployers(Model model) {
        List<Employer> employers = employerService.findAll();
        model.addAttribute("employers", employers);
        return "employers/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("employer", new Employer());
        return "employers/form";
    }

    @GetMapping("/{id}")
    public String showEmployer(@PathVariable Long id, Model model) {
        try {
            Employer employer = employerService.findById(id);
            model.addAttribute("employer", employer);
            return "employers/view";
        } catch (Exception e) {
            return "redirect:/employers?error=notfound";
        }
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        try {
            Employer employer = employerService.findById(id);
            model.addAttribute("employer", employer);
            return "employers/form";
        } catch (Exception e) {
            return "redirect:/employers?error=notfound";
        }
    }

    @PostMapping
    public String saveEmployer(@ModelAttribute Employer employer, RedirectAttributes redirectAttributes) {
        try {
            if (employer.getId() == null) {
                employerService.save(employer);
                redirectAttributes.addFlashAttribute("success", "Employer created successfully!");
            } else {
                employerService.update(employer);
                redirectAttributes.addFlashAttribute("success", "Employer updated successfully!");
            }
            return "redirect:/employers";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error saving employer: " + e.getMessage());
            return "redirect:/employers/new";
        }
    }

    @PostMapping("/{id}/delete")
    public String deleteEmployer(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            employerService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Employer deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error deleting employer: " + e.getMessage());
        }
        return "redirect:/employers";
    }

    @GetMapping("/search")
    public String searchEmployers(@RequestParam String term, @RequestParam(required = false) String type, Model model) {
        List<Employer> employers;
        
        if ("industry".equals(type)) {
            employers = employerService.findByIndustry(term);
        } else {
            employers = employerService.searchByName(term);
        }
        
        model.addAttribute("employers", employers);
        model.addAttribute("searchTerm", term);
        model.addAttribute("searchType", type);
        return "employers/list";
    }
}
package be.ipeters.puttingittogether.web.api.v1;

import be.ipeters.puttingittogether.employer.model.Employer;
import be.ipeters.puttingittogether.employer.service.EmployerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employers")
@CrossOrigin(originPatterns = "*")
public class EmployerController {

    private final EmployerService employerService;

    public EmployerController(EmployerService employerService) {
        this.employerService = employerService;
    }

    @GetMapping
    public ResponseEntity<List<Employer>> getAllEmployers() {
        List<Employer> employers = employerService.findAll();
        return ResponseEntity.ok(employers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employer> getEmployerById(@PathVariable Long id) {
        try {
            Employer employer = employerService.findById(id);
            return ResponseEntity.ok(employer);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Employer> createEmployer(@RequestBody Employer employer) {
        Employer savedEmployer = employerService.save(employer);
        return ResponseEntity.ok(savedEmployer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employer> updateEmployer(@PathVariable Long id, @RequestBody Employer employer) {
        try {
            employer.setId(id);
            Employer updatedEmployer = employerService.update(employer);
            return ResponseEntity.ok(updatedEmployer);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployer(@PathVariable Long id) {
        try {
            employerService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search/name/{name}")
    public ResponseEntity<List<Employer>> findByName(@PathVariable String name) {
        List<Employer> employers = employerService.findByName(name);
        return ResponseEntity.ok(employers);
    }

    @GetMapping("/search/industry/{industry}")
    public ResponseEntity<List<Employer>> findByIndustry(@PathVariable String industry) {
        List<Employer> employers = employerService.findByIndustry(industry);
        return ResponseEntity.ok(employers);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Employer>> searchByName(@RequestParam String term) {
        List<Employer> employers = employerService.searchByName(term);
        return ResponseEntity.ok(employers);
    }
}
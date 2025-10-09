package be.ipeters.puttingittogether.employer.service;

import be.ipeters.puttingittogether.employer.model.Employer;
import be.ipeters.puttingittogether.employer.persistence.EmployerPersistenceFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EmployerService {

    private final EmployerPersistenceFacade employerPersistenceFacade;

    public EmployerService(EmployerPersistenceFacade employerPersistenceFacade) {
        this.employerPersistenceFacade = employerPersistenceFacade;
    }

    public List<Employer> findAll() {
        return this.employerPersistenceFacade.findAll();
    }

    public Employer findById(Long id) {
        return this.employerPersistenceFacade.findById(id);
    }

    public Employer save(Employer employer) {
        return this.employerPersistenceFacade.save(employer);
    }

    public Employer update(Employer employer) {
        return this.employerPersistenceFacade.update(employer);
    }

    public void deleteById(Long id) {
        this.employerPersistenceFacade.deleteById(id);
    }

    public void delete(Employer employer) {
        this.employerPersistenceFacade.delete(employer);
    }

    public List<Employer> findByName(String name) {
        return this.employerPersistenceFacade.findByName(name);
    }

    public List<Employer> findByIndustry(String industry) {
        return this.employerPersistenceFacade.findByIndustry(industry);
    }

    public List<Employer> searchByName(String name) {
        return this.employerPersistenceFacade.findByNameContainingIgnoreCase(name);
    }
}
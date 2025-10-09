package be.ipeters.puttingittogether.employer.persistence;

import be.ipeters.puttingittogether.employer.model.Employer;

import java.util.List;

public interface EmployerPersistenceFacade {

    Employer save(Employer employer);
    List<Employer> findAll();
    Employer findById(Long id);
    void delete(Employer employer);
    void deleteById(Long id);
    Employer update(Employer employer);
    List<Employer> findByName(String name);
    List<Employer> findByIndustry(String industry);
    List<Employer> findByNameContainingIgnoreCase(String name);
}
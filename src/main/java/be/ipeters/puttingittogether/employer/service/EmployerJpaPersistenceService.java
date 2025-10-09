package be.ipeters.puttingittogether.employer.service;

import be.ipeters.puttingittogether.employer.jpa.entity.EmployerJpaEntity;
import be.ipeters.puttingittogether.employer.jpa.mapper.EmployerJpaDaoMapper;
import be.ipeters.puttingittogether.employer.jpa.repository.EmployerJpaRepository;
import be.ipeters.puttingittogether.employer.model.Employer;
import be.ipeters.puttingittogether.employer.persistence.EmployerPersistenceFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EmployerJpaPersistenceService implements EmployerPersistenceFacade {

    private final EmployerJpaRepository employerJpaRepository;
    private final EmployerJpaDaoMapper employerJpaDaoMapper;

    public EmployerJpaPersistenceService(EmployerJpaRepository employerJpaRepository, EmployerJpaDaoMapper employerJpaDaoMapper) {
        this.employerJpaRepository = employerJpaRepository;
        this.employerJpaDaoMapper = employerJpaDaoMapper;
    }

    @Override
    public Employer save(Employer employer) {
        final EmployerJpaEntity employerJpaEntity = this.employerJpaRepository.save(employerJpaDaoMapper.modelToJpaEntity(employer));
        log.debug("Employer JPA = {}", employerJpaEntity);
        return this.employerJpaDaoMapper.jpaEntityToModel(employerJpaEntity);
    }

    @Override
    public List<Employer> findAll() {
        return this.employerJpaRepository.findAll()
                .stream()
                .map(this.employerJpaDaoMapper::jpaEntityToModel)
                .collect(Collectors.toList());
    }

    @Override
    public Employer findById(Long id) {
        return this.employerJpaRepository.findById(id)
                .map(this.employerJpaDaoMapper::jpaEntityToModel)
                .orElseThrow(()
                        -> new NoSuchElementException(
                                "NO EMPLOYER PRESENT WITH ID = " + id));
    }

    @Override
    public void delete(Employer employer) {
        this.employerJpaRepository.delete(this.employerJpaDaoMapper.modelToJpaEntity(employer));
    }

    @Override
    public void deleteById(Long id) {
        this.employerJpaRepository.deleteById(id);
    }

    @Override
    public Employer update(Employer employer) {
        return this.employerJpaDaoMapper.jpaEntityToModel(
                this.employerJpaRepository.save(this.employerJpaDaoMapper.modelToJpaEntity(employer)));
    }

    @Override
    public List<Employer> findByName(String name) {
        return this.employerJpaRepository.findByName(name)
                .stream()
                .map(this.employerJpaDaoMapper::jpaEntityToModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Employer> findByIndustry(String industry) {
        return this.employerJpaRepository.findByIndustry(industry)
                .stream()
                .map(this.employerJpaDaoMapper::jpaEntityToModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Employer> findByNameContainingIgnoreCase(String name) {
        return this.employerJpaRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(this.employerJpaDaoMapper::jpaEntityToModel)
                .collect(Collectors.toList());
    }
}
package be.ipeters.puttingittogether.employer.jpa.repository;

import be.ipeters.puttingittogether.employer.jpa.entity.EmployerJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployerJpaRepository extends JpaRepository<EmployerJpaEntity, Long> {

    List<EmployerJpaEntity> findByName(String name);

    List<EmployerJpaEntity> findByIndustry(String industry);

    List<EmployerJpaEntity> findByNameContainingIgnoreCase(String name);
}
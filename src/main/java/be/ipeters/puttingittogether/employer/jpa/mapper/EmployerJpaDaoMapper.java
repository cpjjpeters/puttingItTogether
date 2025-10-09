package be.ipeters.puttingittogether.employer.jpa.mapper;

import be.ipeters.puttingittogether.employer.jpa.entity.EmployerJpaEntity;
import be.ipeters.puttingittogether.employer.model.Employer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN)
public interface EmployerJpaDaoMapper {
    EmployerJpaEntity modelToJpaEntity(Employer employer);

    Employer jpaEntityToModel(EmployerJpaEntity employerJpaEntity);
}
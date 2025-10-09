package be.ipeters.puttingittogether.employer.jpa.mapper;

import be.ipeters.puttingittogether.employer.jpa.entity.EmployerJpaEntity;
import be.ipeters.puttingittogether.employer.model.Employer;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-09T16:29:38+0200",
    comments = "version: 1.6.2, compiler: javac, environment: Java 23.0.2 (Azul Systems, Inc.)"
)
@Component
public class EmployerJpaDaoMapperImpl implements EmployerJpaDaoMapper {

    @Override
    public EmployerJpaEntity modelToJpaEntity(Employer employer) {
        if ( employer == null ) {
            return null;
        }

        EmployerJpaEntity employerJpaEntity = new EmployerJpaEntity();

        employerJpaEntity.setId( employer.getId() );
        employerJpaEntity.setName( employer.getName() );
        employerJpaEntity.setIndustry( employer.getIndustry() );
        employerJpaEntity.setWebsite( employer.getWebsite() );
        employerJpaEntity.setEmail( employer.getEmail() );
        employerJpaEntity.setPhone( employer.getPhone() );

        return employerJpaEntity;
    }

    @Override
    public Employer jpaEntityToModel(EmployerJpaEntity employerJpaEntity) {
        if ( employerJpaEntity == null ) {
            return null;
        }

        Employer employer = new Employer();

        employer.setId( employerJpaEntity.getId() );
        employer.setName( employerJpaEntity.getName() );
        employer.setIndustry( employerJpaEntity.getIndustry() );
        employer.setWebsite( employerJpaEntity.getWebsite() );
        employer.setEmail( employerJpaEntity.getEmail() );
        employer.setPhone( employerJpaEntity.getPhone() );

        return employer;
    }
}

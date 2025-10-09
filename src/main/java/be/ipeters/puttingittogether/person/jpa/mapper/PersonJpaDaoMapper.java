package be.ipeters.puttingittogether.person.jpa.mapper;

import be.ipeters.puttingittogether.person.jpa.entity.PersonJpaEntity;
import be.ipeters.puttingittogether.person.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN)
public interface PersonJpaDaoMapper {
    PersonJpaEntity modelToJpaEntity(Person person);

    Person jpaEntityToModel(PersonJpaEntity personJpaEntity);
}
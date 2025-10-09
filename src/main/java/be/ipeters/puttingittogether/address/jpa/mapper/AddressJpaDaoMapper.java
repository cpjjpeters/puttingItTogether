package be.ipeters.puttingittogether.address.jpa.mapper;

import be.ipeters.puttingittogether.address.jpa.entity.AddressJpaEntity;
import be.ipeters.puttingittogether.address.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * MapStruct mapper for converting between Address domain model and JPA entity
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN)
public interface AddressJpaDaoMapper {
    AddressJpaEntity modelToJpaEntity(Address address);

    Address jpaEntityToModel(AddressJpaEntity addressJpaEntity);
}
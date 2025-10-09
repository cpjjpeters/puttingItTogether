package be.ipeters.puttingittogether.address.jpa.mapper;

import be.ipeters.puttingittogether.address.jpa.entity.AddressJpaEntity;
import be.ipeters.puttingittogether.address.model.Address;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-09T13:51:40+0200",
    comments = "version: 1.6.2, compiler: javac, environment: Java 23.0.2 (Azul Systems, Inc.)"
)
@Component
public class AddressJpaDaoMapperImpl implements AddressJpaDaoMapper {

    @Override
    public AddressJpaEntity modelToJpaEntity(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressJpaEntity addressJpaEntity = new AddressJpaEntity();

        addressJpaEntity.setId( address.getId() );
        addressJpaEntity.setStreet( address.getStreet() );
        addressJpaEntity.setNumber( address.getNumber() );
        addressJpaEntity.setZipCode( address.getZipCode() );
        addressJpaEntity.setCity( address.getCity() );
        addressJpaEntity.setProvince( address.getProvince() );
        addressJpaEntity.setCountry( address.getCountry() );

        return addressJpaEntity;
    }

    @Override
    public Address jpaEntityToModel(AddressJpaEntity addressJpaEntity) {
        if ( addressJpaEntity == null ) {
            return null;
        }

        Address address = new Address();

        address.setId( addressJpaEntity.getId() );
        address.setStreet( addressJpaEntity.getStreet() );
        address.setNumber( addressJpaEntity.getNumber() );
        address.setZipCode( addressJpaEntity.getZipCode() );
        address.setCity( addressJpaEntity.getCity() );
        address.setProvince( addressJpaEntity.getProvince() );
        address.setCountry( addressJpaEntity.getCountry() );

        return address;
    }
}

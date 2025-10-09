package be.ipeters.puttingittogether.person.jpa.mapper;

import be.ipeters.puttingittogether.address.jpa.entity.AddressJpaEntity;
import be.ipeters.puttingittogether.address.model.Address;
import be.ipeters.puttingittogether.person.jpa.entity.PersonJpaEntity;
import be.ipeters.puttingittogether.person.model.Person;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-09T13:09:37+0200",
    comments = "version: 1.6.2, compiler: javac, environment: Java 23.0.2 (Azul Systems, Inc.)"
)
@Component
public class PersonJpaDaoMapperImpl implements PersonJpaDaoMapper {

    @Override
    public PersonJpaEntity modelToJpaEntity(Person person) {
        if ( person == null ) {
            return null;
        }

        PersonJpaEntity personJpaEntity = new PersonJpaEntity();

        personJpaEntity.setId( person.getId() );
        personJpaEntity.setFirstName( person.getFirstName() );
        personJpaEntity.setLastName( person.getLastName() );
        personJpaEntity.setDateOfBirth( person.getDateOfBirth() );
        personJpaEntity.setGender( person.getGender() );
        personJpaEntity.setAddressId( person.getAddressId() );
        personJpaEntity.setEmployerId( person.getEmployerId() );
        personJpaEntity.setAddress( addressToAddressJpaEntity( person.getAddress() ) );

        return personJpaEntity;
    }

    @Override
    public Person jpaEntityToModel(PersonJpaEntity personJpaEntity) {
        if ( personJpaEntity == null ) {
            return null;
        }

        Person person = new Person();

        person.setId( personJpaEntity.getId() );
        person.setFirstName( personJpaEntity.getFirstName() );
        person.setLastName( personJpaEntity.getLastName() );
        person.setDateOfBirth( personJpaEntity.getDateOfBirth() );
        person.setGender( personJpaEntity.getGender() );
        person.setAddressId( personJpaEntity.getAddressId() );
        person.setEmployerId( personJpaEntity.getEmployerId() );
        person.setAddress( addressJpaEntityToAddress( personJpaEntity.getAddress() ) );

        return person;
    }

    protected AddressJpaEntity addressToAddressJpaEntity(Address address) {
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

    protected Address addressJpaEntityToAddress(AddressJpaEntity addressJpaEntity) {
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

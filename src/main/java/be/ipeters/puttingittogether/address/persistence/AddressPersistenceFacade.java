package be.ipeters.puttingittogether.address.persistence;

import be.ipeters.puttingittogether.address.model.Address;

import java.util.List;

public interface AddressPersistenceFacade {

    Address save(Address address);

    List<Address> findAll();

    Address findById(Long id);

    void delete(Address address);

    void deleteById(Long id);

    Address update(Address address);

    List<Address> findByCity(String city);

    List<Address> findByZipCode(String zipCode);

    List<Address> findByCountry(String country);

    List<Address> findByProvince(String province);

    List<Address> findByCityOrStreetContaining(String city, String street);
}
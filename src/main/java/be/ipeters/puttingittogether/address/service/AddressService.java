package be.ipeters.puttingittogether.address.service;

import be.ipeters.puttingittogether.address.model.Address;
import be.ipeters.puttingittogether.address.persistence.AddressPersistenceFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AddressService {

    private final AddressPersistenceFacade addressPersistenceFacade;

    public AddressService(AddressPersistenceFacade addressPersistenceFacade) {
        this.addressPersistenceFacade = addressPersistenceFacade;
    }

    public List<Address> findAll() {
        return this.addressPersistenceFacade.findAll();
    }

    public Address findById(Long id) {
        Address address = this.addressPersistenceFacade.findById(id);
        return address;
    }

    public Address save(Address address) {
        return this.addressPersistenceFacade.save(address);
    }

    public Address update(Address address) {
        return this.addressPersistenceFacade.update(address);
    }

    public void deleteById(Long id) {
        this.addressPersistenceFacade.deleteById(id);
    }

    public void delete(Address address) {
        this.addressPersistenceFacade.delete(address);
    }

    public List<Address> findByCity(String city) {
        return this.addressPersistenceFacade.findByCity(city);
    }

    public List<Address> findByZipCode(String zipCode) {
        return this.addressPersistenceFacade.findByZipCode(zipCode);
    }

    public List<Address> findByCountry(String country) {
        return this.addressPersistenceFacade.findByCountry(country);
    }

    public List<Address> findByProvince(String province) {
        return this.addressPersistenceFacade.findByProvince(province);
    }

    public List<Address> searchByCityOrStreet(String searchTerm) {
        return this.addressPersistenceFacade.findByCityOrStreetContaining(searchTerm, searchTerm);
    }
}
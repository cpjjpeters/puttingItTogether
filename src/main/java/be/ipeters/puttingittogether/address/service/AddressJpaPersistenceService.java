package be.ipeters.puttingittogether.address.service;

import be.ipeters.puttingittogether.address.jpa.entity.AddressJpaEntity;
import be.ipeters.puttingittogether.address.jpa.mapper.AddressJpaDaoMapper;
import be.ipeters.puttingittogether.address.jpa.repository.AddressJpaRepository;
import be.ipeters.puttingittogether.address.model.Address;
import be.ipeters.puttingittogether.address.persistence.AddressPersistenceFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AddressJpaPersistenceService implements AddressPersistenceFacade {

    private final AddressJpaRepository addressJpaRepository;
    private final AddressJpaDaoMapper addressJpaDaoMapper;

    public AddressJpaPersistenceService(AddressJpaRepository addressJpaRepository, AddressJpaDaoMapper addressJpaDaoMapper) {
        this.addressJpaRepository = addressJpaRepository;
        this.addressJpaDaoMapper = addressJpaDaoMapper;
    }

    @Override
    public Address save(Address address) {
        final AddressJpaEntity addressJpaEntity = this.addressJpaRepository.save(addressJpaDaoMapper.modelToJpaEntity(address));
        log.debug("Address JPA = {}", addressJpaEntity);
        return this.addressJpaDaoMapper.jpaEntityToModel(addressJpaEntity);
    }

    @Override
    public List<Address> findAll() {
        return this.addressJpaRepository.findAll()
                .stream()
                .map(this.addressJpaDaoMapper::jpaEntityToModel)
                .collect(Collectors.toList());
    }

    @Override
    public Address findById(Long id) {
        return this.addressJpaRepository.findById(id)
                .map(this.addressJpaDaoMapper::jpaEntityToModel)
                .orElseThrow(()
                        -> new NoSuchElementException(
                                "NO ADDRESS PRESENT WITH ID = " + id));
    }

    @Override
    public void delete(Address address) {
        this.addressJpaRepository.delete(this.addressJpaDaoMapper.modelToJpaEntity(address));
    }

    @Override
    public void deleteById(Long id) {
        this.addressJpaRepository.deleteById(id);
    }

    @Override
    public Address update(Address address) {
        return this.addressJpaDaoMapper.jpaEntityToModel(
                this.addressJpaRepository.save(this.addressJpaDaoMapper.modelToJpaEntity(address)));
    }

    @Override
    public List<Address> findByCity(String city) {
        return this.addressJpaRepository.findByCity(city)
                .stream()
                .map(this.addressJpaDaoMapper::jpaEntityToModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Address> findByZipCode(String zipCode) {
        return this.addressJpaRepository.findByZipCode(zipCode)
                .stream()
                .map(this.addressJpaDaoMapper::jpaEntityToModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Address> findByCountry(String country) {
        return this.addressJpaRepository.findByCountry(country)
                .stream()
                .map(this.addressJpaDaoMapper::jpaEntityToModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Address> findByProvince(String province) {
        return this.addressJpaRepository.findByProvince(province)
                .stream()
                .map(this.addressJpaDaoMapper::jpaEntityToModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Address> findByCityOrStreetContaining(String city, String street) {
        return this.addressJpaRepository.findByCityOrStreetContaining(city, street)
                .stream()
                .map(this.addressJpaDaoMapper::jpaEntityToModel)
                .collect(Collectors.toList());
    }
}
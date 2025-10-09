package be.ipeters.puttingittogether.address.jpa.repository;

import be.ipeters.puttingittogether.address.jpa.entity.AddressJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressJpaRepository extends JpaRepository<AddressJpaEntity, Long> {

    List<AddressJpaEntity> findByCity(String city);

    List<AddressJpaEntity> findByZipCode(String zipCode);

    List<AddressJpaEntity> findByCountry(String country);

    List<AddressJpaEntity> findByProvince(String province);

    @Query("SELECT a FROM AddressJpaEntity a WHERE a.city LIKE %:city% OR a.street LIKE %:street%")
    List<AddressJpaEntity> findByCityOrStreetContaining(@Param("city") String city, @Param("street") String street);
}
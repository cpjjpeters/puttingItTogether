package be.ipeters.puttingittogether.person.jpa.repository;

import be.ipeters.puttingittogether.person.jpa.entity.PersonJpaEntity;
import be.ipeters.puttingittogether.person.model.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PersonJpaRepository extends JpaRepository<PersonJpaEntity, Long> {

    List<PersonJpaEntity> findByFirstName(String firstName);

    List<PersonJpaEntity> findByLastName(String lastName);

    List<PersonJpaEntity> findByGender(Gender gender);

    List<PersonJpaEntity> findByAddressId(Long addressId);

    List<PersonJpaEntity> findByEmployerId(Long employerId);

    List<PersonJpaEntity> findByDateOfBirthBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT p FROM PersonJpaEntity p WHERE p.firstName LIKE %:name% OR p.lastName LIKE %:name%")
    List<PersonJpaEntity> findByFirstNameOrLastNameContaining(@Param("name") String name);

    @Query("SELECT p FROM PersonJpaEntity p WHERE p.dateOfBirth >= :birthDate")
    List<PersonJpaEntity> findByAgeYoungerThan(@Param("birthDate") LocalDate birthDate);
}
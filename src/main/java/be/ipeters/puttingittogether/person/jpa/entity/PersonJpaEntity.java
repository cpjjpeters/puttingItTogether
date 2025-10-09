package be.ipeters.puttingittogether.person.jpa.entity;

import be.ipeters.puttingittogether.address.jpa.entity.AddressJpaEntity;
import be.ipeters.puttingittogether.person.model.Gender;
import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Table(name = "PERSON")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class PersonJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "DATE_OF_BIRTH")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER")
    private Gender gender;

    @Column(name = "ADDRESS_ID")
    private Long addressId;

    @Column(name = "EMPLOYER_ID")
    private Long employerId;

    // Lazy loading relationships
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADDRESS_ID", insertable = false, updatable = false)
    private AddressJpaEntity address;

    public PersonJpaEntity() {
    }

    public PersonJpaEntity(String firstName, String lastName, LocalDate dateOfBirth, Gender gender, Long addressId, Long employerId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.addressId = addressId;
        this.employerId = employerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getEmployerId() {
        return employerId;
    }

    public void setEmployerId(Long employerId) {
        this.employerId = employerId;
    }

    public AddressJpaEntity getAddress() {
        return address;
    }

    public void setAddress(AddressJpaEntity address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "PersonJpaEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender=" + gender +
                ", addressId=" + addressId +
                ", employerId=" + employerId +
                '}';
    }
}
package be.ipeters.puttingittogether.address.jpa.entity;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Table(name = "ADDRESS")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class AddressJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "STREET", nullable = false)
    private String street;

    @Column(name = "NUMBER", nullable = false)
    private String number;

    @Column(name = "ZIP_CODE", nullable = false)
    private String zipCode;

    @Column(name = "CITY", nullable = false)
    private String city;

    @Column(name = "PROVINCE")
    private String province;

    @Column(name = "COUNTRY", nullable = false)
    private String country;

    public AddressJpaEntity() {
    }

    public AddressJpaEntity(String street, String number, String zipCode, String city, String province, String country) {
        this.street = street;
        this.number = number;
        this.zipCode = zipCode;
        this.city = city;
        this.province = province;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "AddressJpaEntity{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
package be.ipeters.puttingittogether.address.model;

import java.util.Objects;

public class Address {

    private Long id;
    private String street;
    private String number;
    private String zipCode;
    private String city;
    private String province;
    private String country;

    public Address() {
    }

    public Address(String street, String number, String zipCode, String city, String province, String country) {
        this.street = street;
        this.number = number;
        this.zipCode = zipCode;
        this.city = city;
        this.province = province;
        this.country = country;
    }

    public Address(Long id, String street, String number, String zipCode, String city, String province, String country) {
        this.id = id;
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
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.street);
        hash = 29 * hash + Objects.hashCode(this.number);
        hash = 29 * hash + Objects.hashCode(this.zipCode);
        hash = 29 * hash + Objects.hashCode(this.city);
        hash = 29 * hash + Objects.hashCode(this.province);
        hash = 29 * hash + Objects.hashCode(this.country);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Address other = (Address) obj;
        if (!Objects.equals(this.street, other.street)) {
            return false;
        }
        if (!Objects.equals(this.number, other.number)) {
            return false;
        }
        if (!Objects.equals(this.zipCode, other.zipCode)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.province, other.province)) {
            return false;
        }
        if (!Objects.equals(this.country, other.country)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Address{");
        sb.append("id=").append(id);
        sb.append(", street='").append(street).append('\'');
        sb.append(", number='").append(number).append('\'');
        sb.append(", zipCode='").append(zipCode).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", province='").append(province).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append('}');
        return sb.toString();
    }

    /**
     * Returns a formatted address string for display purposes
     */
    public String getFormattedAddress() {
        StringBuilder sb = new StringBuilder();
        if (street != null && !street.isEmpty()) {
            sb.append(street);
            if (number != null && !number.isEmpty()) {
                sb.append(" ").append(number);
            }
            sb.append(", ");
        }
        if (zipCode != null && !zipCode.isEmpty()) {
            sb.append(zipCode).append(" ");
        }
        if (city != null && !city.isEmpty()) {
            sb.append(city);
        }
        if (province != null && !province.isEmpty()) {
            sb.append(", ").append(province);
        }
        if (country != null && !country.isEmpty()) {
            sb.append(", ").append(country);
        }
        return sb.toString();
    }
}
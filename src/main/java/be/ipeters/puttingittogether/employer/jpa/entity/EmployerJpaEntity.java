package be.ipeters.puttingittogether.employer.jpa.entity;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Table(name = "EMPLOYER")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class EmployerJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "INDUSTRY")
    private String industry;

    @Column(name = "WEBSITE")
    private String website;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE")
    private String phone;

    public EmployerJpaEntity() {
    }

    public EmployerJpaEntity(String name, String industry, String website, String email, String phone) {
        this.name = name;
        this.industry = industry;
        this.website = website;
        this.email = email;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "EmployerJpaEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", industry='" + industry + '\'' +
                ", website='" + website + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
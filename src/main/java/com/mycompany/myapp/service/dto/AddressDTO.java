package com.mycompany.myapp.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mycompany.myapp.domain.Address;
import com.mycompany.myapp.domain.Establishment;
import com.mycompany.myapp.domain.User;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @Size(max = 50)
    private String country;

    @Size(max = 50)
    private String city;

    @Size(max = 256)
    private String address;

    private AdminUserDTO user;

    private EstablishmentDTO establishment;

    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;

    public AddressDTO(Address address) {
        this.id = address.getId();
        this.country = address.getCountry();
        this.city = address.getCity();
        this.createdBy = address.getCreatedBy();
        this.createdDate = address.getCreatedDate();
        this.lastModifiedBy = address.getLastModifiedBy();
        this.lastModifiedDate = address.getLastModifiedDate();
    }

    public AddressDTO(Address address, User user, Establishment establishment) {
        this(address);
        this.user = new AdminUserDTO(user);
        this.establishment = new EstablishmentDTO(establishment);
    }

    public AddressDTO() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public AdminUserDTO getUser() {
        return user;
    }

    public void setUser(AdminUserDTO user) {
        this.user = user;
    }

    public EstablishmentDTO getEstablishment() {
        return establishment;
    }

    public void setEstablishment(EstablishmentDTO establishment) {
        this.establishment = establishment;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressDTO that = (AddressDTO) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(country, that.country) &&
            Objects.equals(city, that.city) &&
            Objects.equals(address, that.address)
        );
    }

    public Address toAddress() {
        Address address = new Address();
        address.setCountry(this.getCountry());
        address.setCity(this.getCity());
        address.setAddress(this.getAddress());
        return address;
    }

    @Override
    public String toString() {
        return (
            "AddressDTO{" +
            "id=" +
            id +
            ", country='" +
            country +
            '\'' +
            ", city='" +
            city +
            '\'' +
            ", address='" +
            address +
            '\'' +
            ", createdBy=" +
            createdBy +
            ", createdDate=" +
            createdDate +
            ", lastModifiedBy='" +
            lastModifiedBy +
            '\'' +
            ", lastModifiedDate=" +
            lastModifiedDate +
            '}'
        );
    }
}

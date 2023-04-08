package com.mycompany.myapp.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mycompany.myapp.domain.Address;
import com.mycompany.myapp.domain.Establishment;
import com.mycompany.myapp.domain.EstablishmentType;
import com.mycompany.myapp.domain.Tapa;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EstablishmentDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String type;

    private AddressDTO address;

    private Set<TapaDTO> tapas;

    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;

    private Long myCreatedBy;

    public EstablishmentDTO(Establishment establishment) {
        this.id = establishment.getId();
        this.name = establishment.getName();
        this.type = establishment.getType().name();
        this.createdBy = establishment.getCreatedBy();
        this.createdDate = establishment.getCreatedDate();
        this.lastModifiedBy = establishment.getLastModifiedBy();
        this.lastModifiedDate = establishment.getLastModifiedDate();
        this.myCreatedBy = establishment.getMyCreatedBy();
    }

    public EstablishmentDTO(Establishment establishment, Address address, Set<Tapa> tapas) {
        this(establishment);
        this.address = address != null ? new AddressDTO(address) : null;
        this.tapas = tapas != null ? tapas.stream().map(TapaDTO::new).collect(Collectors.toSet()) : null;
    }

    public EstablishmentDTO() {}

    public Establishment toEstablishment() {
        Establishment res = new Establishment();
        res.setId(id);
        res.setName(this.name);
        res.setType(EstablishmentType.fromString(this.type));
        res.setCreatedBy(this.createdBy);
        res.setCreatedDate(this.createdDate);
        res.setLastModifiedBy(this.lastModifiedBy);
        res.setLastModifiedDate(this.lastModifiedDate);
        res.setMyCreatedBy(this.myCreatedBy);
        res.setAddress(this.address.toAddress());
        return res;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public Set<TapaDTO> getTapas() {
        return tapas;
    }

    public void setTapas(Set<TapaDTO> tapas) {
        this.tapas = tapas;
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

    public Long getMyCreatedBy() {
        return myCreatedBy;
    }

    public void setMyCreatedBy(Long myCreatedBy) {
        this.myCreatedBy = myCreatedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstablishmentDTO that = (EstablishmentDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(type, that.type);
    }

    @Override
    public String toString() {
        return (
            "EstablishmentDTO{" +
            "id=" +
            id +
            ", name='" +
            name +
            '\'' +
            ", type='" +
            type +
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

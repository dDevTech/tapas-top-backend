package com.mycompany.myapp.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "establishment")
public class Establishment extends AbstractAuditingEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "type")
    private EstablishmentType type;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    @JoinTable(
        name = "address",
        joinColumns = { @JoinColumn(name = "id_establishment", referencedColumnName = "address") },
        inverseJoinColumns = { @JoinColumn(name = "id_address", referencedColumnName = "id") }
    )
    @Column(name = "address")
    private Address address;

    @Override
    public Long getId() {
        return null;
    }

    public String getName() {
        return name;
    }

    public EstablishmentType getType() {
        return type;
    }

    public Address getAddress() {
        return address;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(EstablishmentType type) {
        this.type = type;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}

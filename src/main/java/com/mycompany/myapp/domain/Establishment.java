package com.mycompany.myapp.domain;

import java.io.Serializable;
import java.util.Set;
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
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @NotNull
    @Column(name = "my_created_by")
    private Long myCreatedBy;

    @OneToMany(mappedBy = "establishment")
    private Set<Tapa> tapas;

    @Override
    public Long getId() {
        return id;
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

    public Long getMyCreatedBy() {
        return myCreatedBy;
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

    public void setMyCreatedBy(Long myCreatedBy) {
        this.myCreatedBy = myCreatedBy;
    }

    public String toString() {
        return "Establishment{" + "name='" + name + '\'' + ", type='" + type + '\'' + ", address='" + address + '\'' + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Establishment)) {
            return false;
        }
        return id != null && id.equals(((Establishment) o).id);
    }
}

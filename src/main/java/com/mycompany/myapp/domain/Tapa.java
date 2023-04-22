package com.mycompany.myapp.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tapa")
public class Tapa extends AbstractAuditingEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 50)
    @Column(name = "name", length = 50)
    private String name;

    @Size(max = 256)
    @Column(name = "description", length = 256)
    private String description;

    @Size(max = 20)
    @Column(name = "type", length = 20)
    private String type;

    @Size(max = 50)
    @Column(name = "country", length = 50)
    private String country;

    @Column(name = "photo")
    private byte[] photo;

    @Size(max = 256)
    @Column(name = "image_url", length = 256)
    private String imageUrl;

    @Column(name = "my_created_by")
    private Long myCreatedBy;

    @ManyToOne
    @JoinColumn(name = "establishment_id", nullable = false)
    private Establishment establishment;

    @OneToMany(mappedBy = "tapa")
    private Set<User_Rating> ratings;

    @Max(5)
    @Min(0)
    @Column(name = "dulce")
    private Integer dulce;

    @Max(5)
    @Min(0)
    @Column(name = "amargo")
    private Integer amargo;

    @Max(5)
    @Min(0)
    @Column(name = "acido")
    private Integer acido;

    @Max(5)
    @Min(0)
    @Column(name = "salado")
    private Integer salado;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Establishment getEstablishment() {
        return establishment;
    }

    public void setEstablishment(Establishment establishment) {
        this.establishment = establishment;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getMyCreatedBy() {
        return myCreatedBy;
    }

    public void setMyCreatedBy(Long myCreatedBy) {
        this.myCreatedBy = myCreatedBy;
    }

    public Integer getAcido() { return acido; }

    public Integer getAmargo() { return amargo; }

    public Integer getDulce() { return dulce; }

    public Integer getSalado() { return salado; }

    public void setSalado(Integer salado) { this.salado = salado; }

    public void setDulce(Integer dulce) { this.dulce = dulce; }

    public void setAmargo(Integer amargo) { this.amargo = amargo; }

    public void setAcido(Integer acido) { this.acido = acido; }

    @ManyToMany(mappedBy = "favourites")
    private Set<User> fans = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tapa)) {
            return false;
        }
        return id != null && id.equals(((Tapa) o).id);
    }
}

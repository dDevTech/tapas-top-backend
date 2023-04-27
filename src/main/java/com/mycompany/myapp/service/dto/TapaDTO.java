package com.mycompany.myapp.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mycompany.myapp.domain.Establishment;
import com.mycompany.myapp.domain.Tapa;
import com.mycompany.myapp.domain.User_Rating;
import java.io.Serializable;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TapaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @Size(max = 50)
    private String name;

    @Size(max = 256)
    private String description;

    @Size(max = 20)
    private String type;

    @Size(max = 50)
    private String country;

    private byte[] photo;

    private EstablishmentDTO establishment;

    private Set<User_RatingDTO> ratings;
    private User_RatingDTO rating;

    private Boolean isFavourite;

    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;

    @Max(5)
    @Min(0)
    private Integer dulce;

    @Max(5)
    @Min(0)
    private Integer amargo;

    @Max(5)
    @Min(0)
    private Integer acido;

    @Max(5)
    @Min(0)
    private Integer salado;

    public TapaDTO() {}

    private Long myCreatedBy;

    private String imageUrl;

    public TapaDTO(Tapa tapa) {
        this.id = tapa.getId();
        this.name = tapa.getName();
        this.description = tapa.getDescription();
        this.type = tapa.getType();
        this.country = tapa.getCountry();
        this.photo = tapa.getPhoto();
        this.createdBy = tapa.getCreatedBy();
        this.createdDate = tapa.getCreatedDate();
        this.lastModifiedBy = tapa.getLastModifiedBy();
        this.lastModifiedDate = tapa.getLastModifiedDate();
        this.myCreatedBy = tapa.getMyCreatedBy();
        this.imageUrl = tapa.getImageUrl();
        this.dulce = tapa.getDulce();
        this.acido = tapa.getAcido();
        this.amargo = tapa.getAmargo();
        this.salado = tapa.getSalado();
    }

    public TapaDTO(Tapa tapa, Establishment establishment, Set<User_Rating> ratings) {
        this(tapa);
        this.establishment = establishment != null ? new EstablishmentDTO(establishment, establishment.getAddress(), null) : null;
        this.ratings = ratings.stream().map(User_RatingDTO::new).collect(Collectors.toSet());
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

    public EstablishmentDTO getEstablishment() {
        return establishment;
    }

    public void setEstablishment(EstablishmentDTO establishment) {
        this.establishment = establishment;
    }

    public Set<User_RatingDTO> getRatings() {
        return ratings;
    }

    public void setRatings(Set<User_RatingDTO> ratings) {
        this.ratings = ratings;
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

    public User_RatingDTO getRating() {
        return rating;
    }

    public void setRating(User_RatingDTO rating) {
        this.rating = rating;
    }

    public Boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(Boolean favourite) {
        isFavourite = favourite;
    }

    public Double getAverage() {
        if (ratings == null || ratings.isEmpty()) return 0.0;

        Double average = 0.0;
        for (User_RatingDTO ratingDTO : ratings) {
            average += ratingDTO.getRating();
        }
        return average / ratings.size();
    }

    public Long getMyCreatedBy() {
        return myCreatedBy;
    }

    public void setMyCreatedBy(Long myCreatedBy) {
        this.myCreatedBy = myCreatedBy;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getAcido() {
        return acido;
    }

    public Integer getAmargo() {
        return amargo;
    }

    public Integer getDulce() {
        return dulce;
    }

    public Integer getSalado() {
        return salado;
    }

    public void setAcido(Integer acido) {
        this.acido = acido;
    }

    public void setSalado(Integer salado) {
        this.salado = salado;
    }

    public void setDulce(Integer dulce) {
        this.dulce = dulce;
    }

    public void setAmargo(Integer amargo) {
        this.amargo = amargo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TapaDTO tapaDTO = (TapaDTO) o;
        return (
            id.equals(tapaDTO.id) &&
            name.equals(tapaDTO.name) &&
            description.equals(tapaDTO.description) &&
            type.equals(tapaDTO.type) &&
            country.equals(tapaDTO.country) &&
            Arrays.equals(photo, tapaDTO.photo)
        );
    }

    @Override
    public String toString() {
        return (
            "TapaDTO{" +
            "id=" +
            id +
            ", name='" +
            name +
            '\'' +
            ", description='" +
            description +
            '\'' +
            ", type='" +
            type +
            '\'' +
            ", country='" +
            country +
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

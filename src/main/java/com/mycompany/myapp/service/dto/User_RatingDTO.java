package com.mycompany.myapp.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mycompany.myapp.domain.Tapa;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.domain.User_Rating;
import java.io.Serializable;
import java.sql.Date;
import java.time.Instant;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class User_RatingDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private AdminUserDTO user;

    private TapaDTO tapa;

    private Double rating;

    private Date date;

    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;

    public User_RatingDTO(User_Rating userRating) {
        this.id = userRating.getId();
        this.rating = userRating.getRating();
        this.createdBy = userRating.getCreatedBy();
        this.createdDate = userRating.getCreatedDate();
        this.lastModifiedBy = userRating.getLastModifiedBy();
        this.lastModifiedDate = userRating.getLastModifiedDate();
    }

    public User_RatingDTO(User_Rating userRating, User user, Tapa tapa) {
        this(userRating);
        this.user = user != null ? new AdminUserDTO(user) : null;
        this.tapa = tapa != null ? new TapaDTO(tapa) : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AdminUserDTO getUser() {
        return user;
    }

    public void setUser(AdminUserDTO user) {
        this.user = user;
    }

    public TapaDTO getTapa() {
        return tapa;
    }

    public void setTapa(TapaDTO tapa) {
        this.tapa = tapa;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
        User_RatingDTO that = (User_RatingDTO) o;
        return rating == that.rating && Objects.equals(id, that.id) && Objects.equals(date, that.date);
    }

    @Override
    public String toString() {
        return (
            "User_RatingDTO{" +
            "id=" +
            id +
            ", rating=" +
            rating +
            ", date=" +
            date +
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

package com.mycompany.myapp.service.dto;

import com.mycompany.myapp.domain.User_Rating;
import java.io.Serializable;
import java.sql.Date;
import java.time.Instant;
import java.util.Objects;

public class User_RatingDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private int rating;

    private Date date;

    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;

    public User_RatingDTO(User_Rating userRating) {
        this.id = userRating.getId();
        this.rating = userRating.getRating();
        this.date = userRating.getDate();
        this.createdBy = userRating.getCreatedBy();
        this.createdDate = userRating.getCreatedDate();
        this.lastModifiedBy = userRating.getLastModifiedBy();
        this.lastModifiedDate = userRating.getLastModifiedDate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
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

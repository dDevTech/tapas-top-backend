package com.mycompany.myapp.web.rest.requests;

public class User_RatingRequest {

    private Long userId;

    private Long tapaId;

    private Double rating;

    public User_RatingRequest() {}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTapaId() {
        return tapaId;
    }

    public void setTapaId(Long tapaId) {
        this.tapaId = tapaId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}

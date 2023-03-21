package com.mycompany.myapp.domain;

import java.sql.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * A user rating.
 */
@Entity
@Table(name = "user_rating")
public class User_Rating {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "rating")
    private int rating;

    @NotNull
    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "tapa_id", nullable = false)
    private Tapa tapa;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

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

    public Tapa getTapa() {
        return tapa;
    }

    public void setTapa(Tapa tapa) {
        this.tapa = tapa;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User_Rating)) {
            return false;
        }
        return id != null && id.equals(((User_Rating) o).id);
    }

    @Override
    public String toString() {
        return (
            "UserRating{" +
            "rating='" +
            rating +
            '\'' +
            ", date='" +
            date +
            '\'' +
            ", tapa='" +
            tapa.toString() +
            '\'' +
            ", user='" +
            user.toString() +
            "}"
        );
    }
}

package com.mycompany.myapp.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mycompany.myapp.config.Constants;
import com.mycompany.myapp.domain.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.constraints.*;

/**
 * A DTO representing a user, with his authorities.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdminUserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    private String login;

    @Size(max = 50)
    private String userName;

    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;

    @Size(max = 50)
    private String lastName2;

    @Email
    @Size(min = 5, max = 254)
    private String email;

    private byte[] photo;

    private AddressDTO address;

    private String gender;

    private String description;

    @Size(max = 256)
    private String imageUrl;

    private boolean activated = false;

    @Size(min = 2, max = 10)
    private String langKey;

    private Set<TapaDTO> favorites;

    private Set<User_RatingDTO> ratings;

    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;

    private Set<String> authorities;

    public AdminUserDTO() {
        // Empty constructor needed for Jackson.
    }

    public AdminUserDTO(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.userName = user.getUserName();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.lastName2 = user.getLastName2();
        this.email = user.getEmail();
        this.photo = user.getPhoto();
        this.gender = user.getGender() != null ? user.getGender().name() : null;
        this.description = user.getDescription();
        this.activated = user.isActivated();
        this.imageUrl = user.getImageUrl();
        this.langKey = user.getLangKey();
        this.createdBy = user.getCreatedBy();
        this.createdDate = user.getCreatedDate();
        this.lastModifiedBy = user.getLastModifiedBy();
        this.lastModifiedDate = user.getLastModifiedDate();
        this.authorities = user.getAuthorities().stream().map(Authority::getName).collect(Collectors.toSet());
    }

    public AdminUserDTO(User user, Address address, Set<Tapa> favorites, Set<User_Rating> ratings) {
        this(user);
        this.address = address != null ? new AddressDTO(address) : null;
        this.favorites = favorites != null ? favorites.stream().map(TapaDTO::new).collect(Collectors.toSet()) : null;
        this.ratings = ratings != null ? ratings.stream().map(User_RatingDTO::new).collect(Collectors.toSet()) : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName2() {
        return lastName2;
    }

    public void setLastName2(String lastName2) {
        this.lastName2 = lastName2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getLangKey() {
        return langKey;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
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

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public Set<TapaDTO> getFavorites() {
        return favorites;
    }

    public void setFavorites(Set<TapaDTO> favorites) {
        this.favorites = favorites;
    }

    public Set<User_RatingDTO> getRatings() {
        return ratings;
    }

    public void setRatings(Set<User_RatingDTO> ratings) {
        this.ratings = ratings;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdminUserDTO{" +
            "login='" + login + '\'' +
            ", userName='" + userName + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", lastName2='" + lastName2 + '\'' +
            ", email='" + email + '\'' +
            ", gender='" + gender + '\'' +
            ", description='" + description + '\'' +
            ", imageUrl='" + imageUrl + '\'' +
            ", activated=" + activated +
            ", langKey='" + langKey + '\'' +
            ", createdBy=" + createdBy +
            ", createdDate=" + createdDate +
            ", lastModifiedBy='" + lastModifiedBy + '\'' +
            ", lastModifiedDate=" + lastModifiedDate +
            ", authorities=" + authorities +
            "}";
    }
}

package com.mycompany.myapp.service.dto;

import com.mycompany.myapp.domain.Establishment;
import com.mycompany.myapp.domain.Tapa;

import javax.validation.constraints.Size;
import java.util.Arrays;

public class TapaCreationDTO {
    @Size(max = 50)
    private String name;

    @Size(max = 256)
    private String description;

    @Size(max = 20)
    private String type;

    @Size(max = 50)
    private String country;

    private byte[] photo;

    @Size(max = 256)
    private String imageUrl;

    private Long myCreatedBy;

    private Long establishment;

    public Tapa toTapa() throws IllegalArgumentException {
        Tapa tapa = new Tapa();
        tapa.setCountry(country);
        tapa.setMyCreatedBy(myCreatedBy);
        tapa.setDescription(description);
        tapa.setImageUrl(imageUrl);
        tapa.setPhoto(photo);
        tapa.setType(type);
        tapa.setName(name);
        return tapa;
    }

    public Long getEstablishment() {
        return establishment;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEstablishment(Long establishment) {
        this.establishment = establishment;
    }

    public void setMyCreatedBy(Long myCreatedBy) {
        this.myCreatedBy = myCreatedBy;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "TapaCreationDTO{" +
            "name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", type='" + type + '\'' +
            ", country='" + country + '\'' +
            ", photo=" + Arrays.toString(photo) +
            ", imageUrl='" + imageUrl + '\'' +
            ", myCreatedBy=" + myCreatedBy +
            ", establishment=" + establishment +
            '}';
    }
}

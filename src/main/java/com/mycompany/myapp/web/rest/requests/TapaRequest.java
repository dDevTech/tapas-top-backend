package com.mycompany.myapp.web.rest.requests;

import com.mycompany.myapp.domain.Tapa;
import java.util.Arrays;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class TapaRequest {

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

    public Tapa toTapa() throws IllegalArgumentException {
        Tapa tapa = new Tapa();
        tapa.setCountry(country);
        tapa.setMyCreatedBy(myCreatedBy);
        tapa.setDescription(description);
        tapa.setImageUrl(imageUrl);
        tapa.setPhoto(photo);
        tapa.setType(type);
        tapa.setName(name);
        tapa.setAcido(acido);
        tapa.setAmargo(amargo);
        tapa.setDulce(dulce);
        tapa.setSalado(salado);
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

    public Integer getAcido() { return acido; }

    public Integer getAmargo() { return amargo; }

    public Integer getDulce() { return dulce; }

    public Integer getSalado() { return salado; }

    public void setAcido(Integer acido) { this.acido = acido; }

    public void setSalado(Integer salado) { this.salado = salado; }

    public void setDulce(Integer dulce) { this.dulce = dulce; }

    public void setAmargo(Integer amargo) { this.amargo = amargo; }


    @Override
    public String toString() {
        return (
            "TapaCreationDTO{" +
            "name='" +
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
            ", photo=" +
            Arrays.toString(photo) +
            ", imageUrl='" +
            imageUrl +
            '\'' +
            ", myCreatedBy=" +
            myCreatedBy +
            ", establishment=" +
            establishment +
            '}'
        );
    }
}

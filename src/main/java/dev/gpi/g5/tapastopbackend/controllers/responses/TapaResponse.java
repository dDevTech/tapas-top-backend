package dev.gpi.g5.tapastopbackend.controllers.responses;

import dev.gpi.g5.tapastopbackend.models.Tapa;

public class TapaResponse {

    private String nombre;

    private String descripcion;

    public TapaResponse(Tapa tapa) {
        this.nombre = tapa.getNombre();
        this.descripcion = tapa.getDescripcion();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

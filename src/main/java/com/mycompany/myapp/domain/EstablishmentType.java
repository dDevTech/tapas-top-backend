package com.mycompany.myapp.domain;

public enum EstablishmentType {
    BAR,
    RESTAURANTE;

    public static EstablishmentType fromString(String establishmentString) {
        if (establishmentString != null) {
            for (EstablishmentType establishmentType : EstablishmentType.values()) {
                if (establishmentString.equalsIgnoreCase(establishmentType.name())) {
                    return establishmentType;
                }
            }
        }
        throw new IllegalArgumentException("No establishment type constant with name " + establishmentString);
    }
}

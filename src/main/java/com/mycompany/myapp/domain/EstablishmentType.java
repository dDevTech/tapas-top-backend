package com.mycompany.myapp.domain;

import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;

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
        throw new BadRequestAlertException(
            "No establishment type constant with name " + establishmentString,
            "Invalid establishment type",
            "Invalid establishment type"
        );
    }
}

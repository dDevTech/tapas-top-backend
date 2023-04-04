package com.mycompany.myapp.domain;

import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;

public enum GenderType {
    MALE,
    FEMALE,

    NOTSAY,

    NONE;

    public static GenderType fromString(String genderString) {
        if (genderString != null) {
            for (GenderType genderType : GenderType.values()) {
                if (genderString.equalsIgnoreCase(genderType.name())) {
                    return genderType;
                }
            }
        }
        throw new BadRequestAlertException(
            "No gender type constant with name " + genderString,
            "Invalid establishment type",
            "Invalid establishment type"
        );
    }
}

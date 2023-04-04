package com.mycompany.myapp.domain;

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
        throw new IllegalArgumentException("No gender type constant with name " + genderString);
    }
}

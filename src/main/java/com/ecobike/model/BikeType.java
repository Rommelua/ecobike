package com.ecobike.model;

/**
 * Enum contains all possible bike types.
 */
public enum BikeType {
    FOLDING_BIKE("FOLDING BIKE"),
    E_BIKE("E-BIKE"),
    SPEEDELEC("SPEEDELEC");

    /**
     * String representation of the type.
     */
    private final String type;

    BikeType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}

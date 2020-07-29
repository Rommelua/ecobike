package com.ecobike.model;

public enum BikeType {
    FOLDING_BIKE("FOLDING BIKE"),
    E_BIKE("E-BIKE"),
    SPEEDELEC("SPEEDELEC");

    private String type;

    BikeType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}

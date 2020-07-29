package com.ecobike.model;

public class SpeedelecBike extends AbstractElectroBike {

    public SpeedelecBike(String brand,
                         int maxSpeed,
                         int weight,
                         boolean isLightsPresent,
                         int batteryCapacity,
                         String color,
                         int price) {
        super(brand, maxSpeed, weight, isLightsPresent,
                batteryCapacity, color, price);
    }

    /**
     * Method converts bike to specific String format
     * for writing to file.
     *
     * @return String representation of the bike.
     */
    @Override
    public String toFileWriterString() {
        return "SPEEDELEC " + super.toFileWriterString();
    }

    @Override
    public String toString() {
        return "SPEEDELEC" + super.toString();
    }
}

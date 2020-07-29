package com.ecobike.model;

public class EBike extends AbstractElectroBike {

    public EBike(String brand,
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
        return "E-BIKE " + super.toFileWriterString();
    }

    @Override
    public String toString() {
        return "E-BIKE" + super.toString();
    }
}

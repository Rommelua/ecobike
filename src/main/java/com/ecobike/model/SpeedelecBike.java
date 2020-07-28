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

    @Override
    public String toString() {
        return "SPEEDELEC" + super.toString();
    }
}

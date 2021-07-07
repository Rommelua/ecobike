package com.ecobike.model;

/**
 * Class describes Speedelec bike.
 */
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
        setBikeType(BikeType.SPEEDELEC);
    }
}

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
        setBikeType(BikeType.E_BIKE);
    }
}

package com.ecobike.model;

import java.util.Objects;

/**
 * Common class describes abstract bike.
 */
public abstract class Bike {
    /**
     * Type of the bike.
     */
    private BikeType bikeType;

    private final String brand;
    /**
     * Bike's weight in grams
     */
    private final int weight;
    private final boolean isLightsPresent;
    private final String color;
    /**
     * Price in EUR
     */
    private int price;

    public Bike(String brand, int weight, boolean isLightsPresent, String color, int price) {
        this.brand = brand;
        this.weight = weight;
        this.isLightsPresent = isLightsPresent;
        this.color = color;
        this.price = price;
    }

    /**
     * Method converts bike to specific String format
     * for writing to file.
     * @return String representation of the bike.
     */
    public abstract String toFileWriterString();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bike bike = (Bike) o;
        return weight == bike.weight &&
                isLightsPresent == bike.isLightsPresent &&
                price == bike.price &&
                Objects.equals(brand, bike.brand) &&
                Objects.equals(color, bike.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, weight, isLightsPresent, color, price);
    }

    public BikeType getBikeType() {
        return bikeType;
    }

    void setBikeType(BikeType bikeType) {
        this.bikeType = bikeType;
    }

    public String getBrand() {
        return brand;
    }

    public int getWeight() {
        return weight;
    }

    public boolean isLightsPresent() {
        return isLightsPresent;
    }

    public String getColor() {
        return color;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

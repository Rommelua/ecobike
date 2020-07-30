package com.ecobike;

import com.ecobike.model.BikeType;

/**
 * Class container for transferring searching parameters from user
 * to findBikesByParameter() method in DataHolder.
 */
public class SearchParameterContainer {
    /**
     * Common bike parameters.
     */
    private BikeType bikeType;
    private String brand;
    private int minWeight;
    private int maxWeight;
    private boolean isLightsOptionEntered;
    private boolean isLightsPresent;
    private String color;
    private int minPrice;
    private int maxPrice;
    /**
     * Specific Folding Bike parameters.
     */
    private int minWheelSize;
    private int maxWheelSize;
    private int minNumberOfGears;
    private int maxNumberOfGears;
    /**
     * Specific Electro Bike parameters
     */
    private int minMaxBikeSpeed;
    private int maxMaxBikeSpeed;
    private int minBatteryCapacity;
    private int maxBatteryCapacity;

    public BikeType getBikeType() {
        return bikeType;
    }

    public void setBikeType(BikeType bikeType) {
        this.bikeType = bikeType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getMinWeight() {
        return minWeight;
    }

    public void setMinWeight(int minWeight) {
        this.minWeight = minWeight;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public boolean isLightsOptionEntered() {
        return isLightsOptionEntered;
    }

    public void setLightsOptionEntered(boolean lightsOptionEntered) {
        isLightsOptionEntered = lightsOptionEntered;
    }

    public boolean isLightsPresent() {
        return isLightsPresent;
    }

    public void setLightsPresent(boolean lightsPresent) {
        isLightsPresent = lightsPresent;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getMinWheelSize() {
        return minWheelSize;
    }

    public void setMinWheelSize(int minWheelSize) {
        this.minWheelSize = minWheelSize;
    }

    public int getMaxWheelSize() {
        return maxWheelSize;
    }

    public void setMaxWheelSize(int maxWheelSize) {
        this.maxWheelSize = maxWheelSize;
    }

    public int getMinNumberOfGears() {
        return minNumberOfGears;
    }

    public void setMinNumberOfGears(int minNumberOfGears) {
        this.minNumberOfGears = minNumberOfGears;
    }

    public int getMaxNumberOfGears() {
        return maxNumberOfGears;
    }

    public void setMaxNumberOfGears(int maxNumberOfGears) {
        this.maxNumberOfGears = maxNumberOfGears;
    }

    public int getMinMaxBikeSpeed() {
        return minMaxBikeSpeed;
    }

    public void setMinMaxBikeSpeed(int minMaxBikeSpeed) {
        this.minMaxBikeSpeed = minMaxBikeSpeed;
    }

    public int getMaxMaxBikeSpeed() {
        return maxMaxBikeSpeed;
    }

    public void setMaxMaxBikeSpeed(int maxMaxBikeSpeed) {
        this.maxMaxBikeSpeed = maxMaxBikeSpeed;
    }

    public int getMinBatteryCapacity() {
        return minBatteryCapacity;
    }

    public void setMinBatteryCapacity(int minBatteryCapacity) {
        this.minBatteryCapacity = minBatteryCapacity;
    }

    public int getMaxBatteryCapacity() {
        return maxBatteryCapacity;
    }

    public void setMaxBatteryCapacity(int maxBatteryCapacity) {
        this.maxBatteryCapacity = maxBatteryCapacity;
    }
}

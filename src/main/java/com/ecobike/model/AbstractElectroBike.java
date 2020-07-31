package com.ecobike.model;

import java.util.Objects;

/**
 * Common class describes electro bike.
 */
public abstract class AbstractElectroBike extends Bike {

    /**
     * Max speed in km/h
     */
    private final int maxSpeed;

    /**
     * Battery capacity in mAh
     */
    private final int batteryCapacity;

    public AbstractElectroBike(String brand,
                               int maxSpeed,
                               int weight,
                               boolean isLightsPresent,
                               int batteryCapacity,
                               String color,
                               int price) {
        super(brand, weight, isLightsPresent, color, price);
        this.maxSpeed = maxSpeed;
        this.batteryCapacity = batteryCapacity;
    }

    /**
     * Method converts bike to specific String format
     * for writing to file.
     *
     * @return String representation of the bike.
     */
    @Override
    public String toFileWriterString() {
        return String.format("%s %s; %d; %d; %s; %d; %s; %d", getBikeType(),
                getBrand(), maxSpeed, getWeight(), isLightsPresent() ? "true" : "false",
                batteryCapacity, getColor(), getPrice());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        AbstractElectroBike that = (AbstractElectroBike) o;
        return maxSpeed == that.maxSpeed
                && batteryCapacity == that.batteryCapacity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), maxSpeed, batteryCapacity);
    }

    @Override
    public String toString() {
        return String.format("%s %s with %d mAh battery and%s head/tail light."
                        + "\nPrice: %d euros.", getBikeType(),
                getBrand(), getBatteryCapacity(), isLightsPresent() ? "" : " no", getPrice());
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

}

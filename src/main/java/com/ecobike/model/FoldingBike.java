package com.ecobike.model;

import java.util.Objects;

/**
 * Class describes Folding Bike.
 */
public class FoldingBike extends Bike {

    /**
     * Wheel size in inch.
     */
    private final int wheelSize;

    private final int numberOfGears;

    public FoldingBike(String brand,
                       int wheelSize,
                       int numberOfGears,
                       int weight,
                       boolean isLightsPresent,
                       String color,
                       int price) {
        super(brand, weight, isLightsPresent, color, price);
        this.wheelSize = wheelSize;
        this.numberOfGears = numberOfGears;
        setBikeType(BikeType.FOLDING_BIKE);
    }

    /**
     * Method converts bike to specific String format
     * for writing to file.
     *
     * @return String representation of the bike.
     */
    @Override
    public String toFileWriterString() {
        return String.format("%s %s; %d; %d; %d; %s; %s; %d", getBikeType(),
                getBrand(), wheelSize, numberOfGears, getWeight(),
                isLightsPresent() ? "true" : "false", getColor(), getPrice());
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
        FoldingBike that = (FoldingBike) o;
        return wheelSize == that.wheelSize
                && numberOfGears == that.numberOfGears;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), wheelSize, numberOfGears);
    }

    @Override
    public String toOutputString() {
        return String.format("%s %s with %d gear(s) and%s head/tail light."
                        + "\nPrice: %d euros.", getBikeType(),
                getBrand(), getNumberOfGears(), isLightsPresent() ? "" : " no", getPrice());
    }

    public int getWheelSize() {
        return wheelSize;
    }

    public int getNumberOfGears() {
        return numberOfGears;
    }

}

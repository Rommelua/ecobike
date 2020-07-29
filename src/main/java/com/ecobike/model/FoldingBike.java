package com.ecobike.model;

import java.util.Objects;

public class FoldingBike extends Bike {

    /**
     * Wheel size in inch.
     */
    private int wheelSize;

    private int numberOfGears;

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
    }

    /**
     * Method converts bike to specific String format
     * for writing to file.
     *
     * @return String representation of the bike.
     */
    @Override
    public String toFileWriterString() {
        return String.format("FOLDING BIKE %s; %d; %d; %d; %s; %s; %d",
                getBrand(), wheelSize, numberOfGears, getWeight(),
                isLightsPresent() ? "true" : "false", getColor(), getPrice());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FoldingBike that = (FoldingBike) o;
        return wheelSize == that.wheelSize &&
                numberOfGears == that.numberOfGears;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), wheelSize, numberOfGears);
    }

    @Override
    public String toString() {
        return String.format("FOLDING BIKE %s with %d gear(s) and%s head/tail light." +
                        "\nPrice: %d euros.",
                getBrand(), getNumberOfGears(), isLightsPresent() ? "" : " no", getPrice());
    }

    public int getWheelSize() {
        return wheelSize;
    }

    public void setWheelSize(int wheelSize) {
        this.wheelSize = wheelSize;
    }

    public int getNumberOfGears() {
        return numberOfGears;
    }

    public void setNumberOfGears(int numberOfGears) {
        this.numberOfGears = numberOfGears;
    }
}

package com.ecobike;

import com.ecobike.model.AbstractElectroBike;
import com.ecobike.model.Bike;
import com.ecobike.model.BikeType;
import com.ecobike.model.FoldingBike;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Singleton class responsible for holding and operating with Bikes objects
 * loaded from data source.
 */
public class DataHolder {
    private static final DataHolder instance = new DataHolder();
    /**
     * Bikes container.
     */
    private final List<Bike> bikes = new ArrayList<>();

    /**
     * We need this Latch to be sure that our new Thread in addBikes() method,
     * that sorts Bike storage, will take monitor before any other thread.
     */
    private CountDownLatch countDownLatch = new CountDownLatch(0);

    /**
     * Variable for keeping information was data changed
     * since last writing to file or not.
     */
    private boolean isDataChanged;

    private DataHolder() {
    }

    public static DataHolder getInstance() {
        return instance;
    }

    /**
     * Method adds Bikes from collection to Bikes container.
     *
     * @param bikesToAdd collection with Bikes for adding.
     */
    public synchronized void init(Collection<Bike> bikesToAdd) {
        bikes.clear();
        bikes.addAll(bikesToAdd);
        countDownLatch = new CountDownLatch(1);
        new Thread(() -> {
            sort();
            countDownLatch.countDown();
        }).start();
        isDataChanged = false;
    }

    /**
     * Method adds single Bike object to Bikes container.
     *
     * @param bike to be added.
     */
    public void addBike(Bike bike) {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (this) {
            bikes.add(bike);
            sort();
        }
        isDataChanged = true;
    }

    /**
     * Method returns unmodifiable list represents our Bikes container.
     *
     * @return unmodifiable list of Bikes.
     */
    public List<Bike> getUnmodifiableBikeList() {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (this) {
            return Collections.unmodifiableList(bikes);
        }
    }

    /**
     * Method finds Bikes in container by parameters from
     * SearchParameterContainer object.
     *
     * @param parameters object with parameters for searching.
     * @return list with properly Bikes objects.
     */
    public List<Bike> findBikesByParameter(SearchParameterContainer parameters) {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        synchronized (this) {
            Stream<Bike> bikeStream = bikes.parallelStream()
                    .filter(bike -> bike.getBikeType() == parameters.getBikeType()
                            && bike.getBrand().equalsIgnoreCase(parameters.getBrand())
                            && bike.getWeight() >= parameters.getMinWeight()
                            && (parameters.getMaxWeight() == 0
                                || bike.getWeight() <= parameters.getMaxWeight())
                            && (!parameters.isLightsOptionEntered()
                                || parameters.isLightsPresent() == bike.isLightsPresent())
                            && (parameters.getColor().isEmpty()
                                || parameters.getColor().equalsIgnoreCase(bike.getColor()))
                            && bike.getPrice() >= parameters.getMinPrice()
                            && (parameters.getMaxPrice() == 0
                                || bike.getPrice() <= parameters.getMaxPrice()));

            if (parameters.getBikeType() == BikeType.FOLDING_BIKE) {
                return bikeStream.map(bike -> (FoldingBike) bike)
                        .filter(bike -> bike.getWheelSize() >= parameters.getMinWheelSize()
                                && (parameters.getMaxWheelSize() == 0
                                    || bike.getWheelSize() <= parameters.getMaxWheelSize())
                                && bike.getNumberOfGears() >= parameters.getMinNumberOfGears()
                                && (parameters.getMaxNumberOfGears() == 0
                                    || bike.getNumberOfGears() <= parameters.getMaxNumberOfGears()))
                        .collect(Collectors.toList());
            }
            return bikeStream.map(bike -> (AbstractElectroBike) bike)
                    .filter(bike -> bike.getMaxSpeed() >= parameters.getMinMaxBikeSpeed()
                            && (parameters.getMaxMaxBikeSpeed() == 0
                                || bike.getMaxSpeed() <= parameters.getMaxMaxBikeSpeed())
                            && bike.getBatteryCapacity() >= parameters.getMinBatteryCapacity()
                            && (parameters.getMaxBatteryCapacity() == 0
                                || bike.getBatteryCapacity() <= parameters.getMaxBatteryCapacity()))
                    .collect(Collectors.toList());
        }
    }

    /**
     * Method returns information was data changed
     * since last saving or not.
     *
     * @return was data changed
     * since last saving or not.
     */
    public boolean isDataChanged() {
        return isDataChanged;
    }

    /**
     * Method sets parameter showing was data changed
     * since last saving or not.
     *
     * @param dataChanged boolean value.
     */
    public void setDataChanged(boolean dataChanged) {
        isDataChanged = dataChanged;
    }

    /**
     * Method sorts Bikes in container.
     */
    private synchronized void sort() {
        Collections.sort(bikes);
    }
}

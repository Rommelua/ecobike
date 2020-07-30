package com.ecobike;

import com.ecobike.model.AbstractElectroBike;
import com.ecobike.model.Bike;
import com.ecobike.model.BikeType;
import com.ecobike.model.FoldingBike;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Singleton class responsible for holding and operating with Bikes objects
 * loaded from data source.
 */
public class DataHolder {
    private final static DataHolder INSTANCE = new DataHolder();
    /**
     * Bikes container.
     */
    private final List<Bike> bikes = new ArrayList<>();

    private DataHolder() {
    }

    public static DataHolder getInstance() {
        return INSTANCE;
    }

    /**
     * Method adds Bikes from collection to Bikes container.
     *
     * @param bikesToAdd collection with Bikes for adding.
     */
    public void addBikes(Collection<Bike> bikesToAdd) {
        bikes.addAll(bikesToAdd);
        EcoBikeApplication.isDataChanged = true;
    }

    /**
     * Method adds single Bike object to Bikes container.
     *
     * @param bike to be added.
     */
    public void addBike(Bike bike) {
        bikes.add(bike);
        EcoBikeApplication.isDataChanged = true;
    }

    /**
     * Method returns unmodifiable list represents our Bikes container.
     *
     * @return unmodifiable list of Bikes.
     */
    public List<Bike> getUnmodifiableBikeList() {
        return Collections.unmodifiableList(bikes);
    }

    /**
     * Method finds Bikes in container by parameters from
     * SearchParameterContainer object.
     *
     * @param parCont object with parameters for searching.
     * @return list with properly Bikes objects.
     */
    public List<Bike> findBikesByParameter(SearchParameterContainer parCont) {
        Stream<Bike> bikeStream = bikes.parallelStream()
                .filter(bike -> bike.getBikeType() == parCont.getBikeType()
                        && bike.getBrand().equalsIgnoreCase(parCont.getBrand())
                        && bike.getWeight() >= parCont.getMinWeight()
                        && (parCont.getMaxWeight() == 0 || bike.getWeight() <= parCont.getMaxWeight())
                        && (!parCont.isLightsOptionEntered() || parCont.isLightsPresent() == bike.isLightsPresent())
                        && (parCont.getColor().isEmpty() || parCont.getColor().equalsIgnoreCase(bike.getColor()))
                        && bike.getPrice() >= parCont.getMinPrice()
                        && (parCont.getMaxPrice() == 0 || bike.getPrice() <= parCont.getMaxPrice()));

        if (parCont.getBikeType() == BikeType.FOLDING_BIKE) {
            return bikeStream.map(bike -> (FoldingBike) bike)
                    .filter(bike -> bike.getWheelSize() >= parCont.getMinWheelSize()
                            && (parCont.getMaxWheelSize() == 0 || bike.getWheelSize() <= parCont.getMaxWheelSize())
                            && bike.getNumberOfGears() >= parCont.getMinNumberOfGears()
                            && (parCont.getMaxNumberOfGears() == 0 || bike.getNumberOfGears() <= parCont.getMaxNumberOfGears()))
                    .collect(Collectors.toList());
        }
        return bikeStream.map(bike -> (AbstractElectroBike) bike)
                .filter(bike -> bike.getMaxSpeed() >= parCont.getMinMaxBikeSpeed()
                        && (parCont.getMaxMaxBikeSpeed() == 0 || bike.getMaxSpeed() <= parCont.getMaxMaxBikeSpeed())
                        && bike.getBatteryCapacity() >= parCont.getMinBatteryCapacity()
                        && (parCont.getMaxBatteryCapacity() == 0 || bike.getBatteryCapacity() <= parCont.getMaxBatteryCapacity()))
                .collect(Collectors.toList());
    }
}

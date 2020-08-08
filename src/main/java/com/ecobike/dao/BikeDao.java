package com.ecobike.dao;

import com.ecobike.exception.IllegalDataSourceException;
import com.ecobike.model.Bike;
import java.util.List;

/**
 * Interface should be implemented by class
 * responsible for access to data source with Bike objects.
 */
public interface BikeDao {

    /**
     * Method reads data from data source.
     *
     * @throws IllegalDataSourceException if no one Bike object parsed from file.
     */
    List<Bike> readBikes() throws IllegalDataSourceException;

    /**
     * Method writes to data source Bike objects.
     * Old data must be replaced with new one.
     */
    void saveBikes(List<Bike> bikes);
}

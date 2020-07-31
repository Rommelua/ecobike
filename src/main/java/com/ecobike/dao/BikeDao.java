package com.ecobike.dao;

import com.ecobike.exception.IllegalDataSourceException;

/**
 * Interface should be implemented by class
 * responsible for access to data source with Bike objects.
 */
public interface BikeDao {

    /**
     * Method sets data source.
     *
     * @param address string with parameters needed
     *                for connection to data source.
     */
    void setSource(String address);

    /**
     * Method reads data from data source and
     * loads parsed Bike objects to DataHolder.
     *
     * @throws IllegalDataSourceException if no one Bike object parsed from file.
     */
    void loadBikes() throws IllegalDataSourceException;

    /**
     * Method writes to data source Bike objects.
     * Old data must be replaced with new one.
     */
    void writeBikes();
}

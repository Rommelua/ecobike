package com.ecobike.parser;

import com.ecobike.model.Bike;

public interface BikeParser<T extends Bike> {
    /**
     * Method parses single String line to Bike object.
     *
     * @param line string to be parsed.
     * @return parsed Bike object
     */
    T parseBike(String line);
}

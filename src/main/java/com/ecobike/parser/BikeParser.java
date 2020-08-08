package com.ecobike.parser;

import com.ecobike.model.Bike;

public interface BikeParser {
    /**
     * Method parses single String line to Bike object.
     *
     * @param line string to be parsed.
     * @return parsed Bike object
     */
    Bike parseBike(String line);
}

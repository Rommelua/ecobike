package com.ecobike.parser;

import com.ecobike.model.Bike;
import com.ecobike.model.EBike;
import org.junit.Test;

import static org.junit.Assert.*;

public class EbikeFileParserTest {

    private static final String BIKE_STR = "E-BIKE Porshe Design; 45; 28800; true; 13000; dark gray; 2789";
    private static final EBike BIKE = new EBike("Porshe Design", 45, 28800,
            true, 13000, "dark gray", 2789);

    @Test
    public void parseBike() {
        EbikeFileParser parser = new EbikeFileParser();
        Bike parseBike = parser.parseBike(BIKE_STR);
        assertEquals(BIKE, parseBike);
    }
}
package com.ecobike.parser;

import com.ecobike.model.Bike;
import com.ecobike.model.SpeedelecBike;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SpedelecFileParserTest {

    private static final String BIKE_STR = "SPEEDELEC Segway; 10; 11700; false; 12000; pink; 1365";
    private static final SpeedelecBike BIKE = new SpeedelecBike("Segway", 10, 11700,
            false, 12000, "pink", 1365);

    @Test
    public void parseBike() {
        SpedelecFileParser parser = new SpedelecFileParser();
        Bike parseBike = parser.parseBike(BIKE_STR);
        assertEquals(BIKE, parseBike);
    }
}

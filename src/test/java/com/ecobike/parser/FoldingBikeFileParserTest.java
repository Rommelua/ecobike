package com.ecobike.parser;

import com.ecobike.model.Bike;
import com.ecobike.model.EBike;
import com.ecobike.model.FoldingBike;
import org.junit.Test;

import static org.junit.Assert.*;

public class FoldingBikeFileParserTest {

    private static final String BIKE_STR = "FOLDING BIKE Author; 20; 9; 11900; true; brown; 209";
    private static final FoldingBike BIKE = new FoldingBike("Author", 20, 9,
            11900, true, "brown", 209);

    @Test
    public void parseBike() {
        FoldingBikeFileParser parser = new FoldingBikeFileParser();
        Bike parsedBike = parser.parseBike(BIKE_STR);
        assertEquals(BIKE, parsedBike);
    }
}

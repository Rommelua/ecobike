package com.ecobike.parser;

import com.ecobike.exception.IllegalDataSourceException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class FileBikeParserTest {

    private static final String E_BIKE = "E-BIKE Porshe Design; 45; 28800; true; 13000; dark gray; 2789";
    private static final String FOLDING_BIKE = "FOLDING BIKE Dahon; 14; 21; 12200; true; golden; 1169";
    private static final String SPEEDELEC = "SPEEDELEC Peugeot; 60; 19800; true; 12200; yellow; 885";
    private static final String WRONG = "WRONG";

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void getBikeParser() {
        BikeParser bikeParser = FileBikeParser.getBikeParser(E_BIKE);
        assertEquals(EbikeFileParser.class, bikeParser.getClass());
        bikeParser = FileBikeParser.getBikeParser(FOLDING_BIKE);
        assertEquals(FoldingBikeFileParser.class, bikeParser.getClass());
        bikeParser = FileBikeParser.getBikeParser(SPEEDELEC);
        assertEquals(SpedelecFileParser.class, bikeParser.getClass());
        expectedEx.expect(IllegalArgumentException.class);
        bikeParser = FileBikeParser.getBikeParser(WRONG);
    }
}

package com.ecobike;

import com.ecobike.dao.BikeDAO;
import com.ecobike.dao.FileBikeDAO;
import com.ecobike.exception.IllegalDataSourceException;
import com.ecobike.model.Bike;
import com.ecobike.model.BikeType;
import com.ecobike.model.EBike;
import com.ecobike.model.FoldingBike;
import com.ecobike.model.SpeedelecBike;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DataHolderTest {
    private static final BikeDAO BIKE_DAO = FileBikeDAO.getInstance();
    private static final DataHolder DATA_HOLDER = DataHolder.getInstance();
    private static final int START_EXPECTED_LIST_SIZE = 5;
    private static final int FINISH_EXPECTED_LIST_SIZE = 7;
    private static final Bike FIRST_BIKE_TO_ADD = new EBike("AAAA",50,
            8400, false, 8600, "brown", 1609);
    private static final Bike LAST_BIKE_TO_ADD = new SpeedelecBike("ZZZZ",50,
            8400, false, 8600, "brown", 1609);
    private static final SearchParameterContainer PARAMETER_CONTAINER =
            new SearchParameterContainer();
    static {
        PARAMETER_CONTAINER.setBikeType(BikeType.FOLDING_BIKE);
        PARAMETER_CONTAINER.setBrand("bmW");
        PARAMETER_CONTAINER.setMinWheelSize(16);
        PARAMETER_CONTAINER.setMaxWheelSize(25);
        PARAMETER_CONTAINER.setColor("");
    }

    private static final Bike FIRST_FOUND_BIKE = new FoldingBike("BMW", 20, 7,
            14400, false, "lemon", 1085);
    private static final Bike SECOND_FOUND_BIKE = new FoldingBike("BMW", 16, 9,
            11000, true, "emerald", 1459);

    @Before
    public void before() throws IllegalDataSourceException {
        BIKE_DAO.setSource("src/main/resources/test/fileWithFiveTrueBikes.txt");
        BIKE_DAO.loadBikes();
    }

    @Test
    public void getUnmodifiableBikeList() {
        List<Bike> loadedBikes = DATA_HOLDER.getUnmodifiableBikeList();
        assertEquals(START_EXPECTED_LIST_SIZE, loadedBikes.size());
    }

    @Test
    public void addBikes() {
        List<Bike> bikesToAdd = Arrays.asList(FIRST_BIKE_TO_ADD, LAST_BIKE_TO_ADD);
        DATA_HOLDER.addBikes(bikesToAdd);
        List<Bike> loadedBikes = DATA_HOLDER.getUnmodifiableBikeList();

        assertEquals(FINISH_EXPECTED_LIST_SIZE, loadedBikes.size());
        assertEquals(FIRST_BIKE_TO_ADD, loadedBikes.get(0));
        assertEquals(LAST_BIKE_TO_ADD, loadedBikes.get(6));
    }

    @Test
    public void addBike() {
        DATA_HOLDER.addBike(FIRST_BIKE_TO_ADD);
        DATA_HOLDER.addBike(LAST_BIKE_TO_ADD);
        List<Bike> loadedBikes = DATA_HOLDER.getUnmodifiableBikeList();

        assertEquals(FINISH_EXPECTED_LIST_SIZE, loadedBikes.size());
        assertEquals(FIRST_BIKE_TO_ADD, loadedBikes.get(0));
        assertEquals(LAST_BIKE_TO_ADD, loadedBikes.get(6));
    }

    @Test
    public void findBikesByParameter() {
        List<Bike> foundBikes = DATA_HOLDER.findBikesByParameter(PARAMETER_CONTAINER);
        assertEquals(2, foundBikes.size());
        assertTrue(foundBikes.contains(FIRST_FOUND_BIKE));
        assertTrue(foundBikes.contains(SECOND_FOUND_BIKE));
    }
}
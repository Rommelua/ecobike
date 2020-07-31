package com.ecobike;

import com.ecobike.dao.BikeDAO;
import com.ecobike.dao.FileBikeDAO;
import com.ecobike.exception.IllegalDataSourceException;
import com.ecobike.model.Bike;
import com.ecobike.model.SpeedelecBike;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileBikeDAOTest {
    private static final BikeDAO BIKE_DAO = FileBikeDAO.getInstance();
    private static final int EXPECTED_LIST_SIZE = 5;
    private static final Bike LAST_BIKE = new SpeedelecBike("EcoRide",50,
            8400, false, 8600, "brown", 1609);
    private static final Path NEW_FILE = Path.of("src/main/resources/test/newFile.txt");

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void loadBikesTest() throws InterruptedException, IllegalDataSourceException, ClassNotFoundException {
        BIKE_DAO.setSource("src/main/resources/test/fileWithFiveTrueBikes.txt");
        BIKE_DAO.loadBikes();
        Thread.sleep(500);
        DataHolder dataHolder = DataHolder.getInstance();
        List<Bike> loadedBikes = dataHolder.getUnmodifiableBikeList();

        Assert.assertEquals(EXPECTED_LIST_SIZE, loadedBikes.size());
        Assert.assertEquals(loadedBikes.size() - 1, loadedBikes.indexOf(LAST_BIKE));

        expectedEx.expect(IllegalDataSourceException.class);
        BIKE_DAO.setSource("src/main/resources/test/wrongBikeFormat.txt");
        BIKE_DAO.loadBikes();
    }

    @Test
    public void writeBikesTest() throws IOException, IllegalDataSourceException {
        Files.deleteIfExists(NEW_FILE);
        Files.createFile(NEW_FILE);
        Files.writeString(NEW_FILE, "FOLDING BIKE BMW; 20; 7; 14400; false; lemon; 1085");
        BIKE_DAO.setSource("src/main/resources/test/newFile.txt");
        BIKE_DAO.loadBikes();
        DataHolder dataHolder = DataHolder.getInstance();
        dataHolder.addBike(LAST_BIKE);
        BIKE_DAO.writeBikes();
        List<String> fileLines = Files.readAllLines(NEW_FILE);
        Assert.assertEquals(2, fileLines.size());
        Assert.assertEquals("FOLDING BIKE BMW; 20; 7; 14400; false; lemon; 1085", fileLines.get(0));
        Assert.assertEquals("SPEEDELEC EcoRide; 50; 8400; false; 8600; brown; 1609", fileLines.get(1));
    }
}
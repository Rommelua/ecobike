package com.ecobike;

import com.ecobike.dao.BikeDao;
import com.ecobike.dao.FileBikeDao;
import com.ecobike.exception.IllegalDataSourceException;
import com.ecobike.model.Bike;
import com.ecobike.model.SpeedelecBike;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileBikeParserDaoTest {
    private static final int EXPECTED_LIST_SIZE = 5;
    private static final Bike LAST_BIKE = new SpeedelecBike("EcoRide", 50,
            8400, false, 8600, "brown", 1609);
    private static final Path NEW_FILE = Path.of("src/main/resources/test/newFile.txt");
    private BikeDao bikeDao;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @BeforeClass
    public static void beforeClass() {

    }

    @Test
    public void writeBikesTest() throws IOException, IllegalDataSourceException, InterruptedException {
        Files.deleteIfExists(NEW_FILE);
        Files.createFile(NEW_FILE);
        Files.writeString(NEW_FILE, "FOLDING BIKE BMW; 20; 7; 14400; false; lemon; 1085");
        bikeDao = new FileBikeDao(Path.of("src/main/resources/test/newFile.txt"));
        bikeDao.loadBikes();
        DataHolder dataHolder = DataHolder.getInstance();
        dataHolder.addBike(LAST_BIKE);
        bikeDao.saveBikes();
        List<String> fileLines = Files.readAllLines(NEW_FILE);
        Assert.assertEquals(2, fileLines.size());
        Assert.assertEquals("FOLDING BIKE BMW; 20; 7; 14400; false; lemon; 1085", fileLines.get(0));
        Assert.assertEquals("SPEEDELEC EcoRide; 50; 8400; false; 8600; brown; 1609", fileLines.get(1));
    }

    @Test
    public void loadBikesTest() throws IllegalDataSourceException {
        bikeDao = new FileBikeDao(Path.of("src/main/resources/test/fileWithFiveTrueBikes.txt"));
        bikeDao.loadBikes();
        DataHolder dataHolder = DataHolder.getInstance();
        List<Bike> loadedBikes = dataHolder.getUnmodifiableBikeList();

        Assert.assertEquals(EXPECTED_LIST_SIZE, loadedBikes.size());
        Assert.assertEquals(loadedBikes.size() - 1, loadedBikes.indexOf(LAST_BIKE));

        expectedEx.expect(IllegalDataSourceException.class);
        bikeDao = new FileBikeDao(Path.of("src/main/resources/test/wrongBikeFormat.txt"));
        bikeDao.loadBikes();
    }
}
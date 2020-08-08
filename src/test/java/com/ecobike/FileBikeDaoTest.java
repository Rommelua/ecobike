package com.ecobike;

import com.ecobike.dao.BikeDao;
import com.ecobike.dao.FileBikeDao;
import com.ecobike.exception.IllegalDataSourceException;
import com.ecobike.model.Bike;
import com.ecobike.model.FoldingBike;
import com.ecobike.model.SpeedelecBike;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileBikeDaoTest {
    private static final int EXPECTED_LIST_SIZE = 5;
    private static final Bike FIRST_BIKE = new SpeedelecBike("EcoRide", 50, 8400,
            false, 8600, "brown", 1609);
    private static final List<Bike> listToSave = new ArrayList<>();
    private static final Path NEW_FILE = Path.of("src/main/resources/test/newFile.txt");
    private BikeDao bikeDao;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @BeforeClass
    public static void beforeClass() {
        listToSave.add(FIRST_BIKE);
        listToSave.add(new FoldingBike("EcoRide", 50, 8400,
                8600, false, "brown", 1609));
    }

    @Test
    public void saveBikesTest() throws IOException, IllegalDataSourceException, InterruptedException {
        Files.deleteIfExists(NEW_FILE);
        Files.createFile(NEW_FILE);
        bikeDao = new FileBikeDao(Path.of("src/main/resources/test/newFile.txt"));
        bikeDao.saveBikes(listToSave);

        List<String> fileLines = Files.readAllLines(NEW_FILE);
        Assert.assertEquals(2, fileLines.size());
        Assert.assertEquals("SPEEDELEC EcoRide; 50; 8400; false; 8600; brown; 1609", fileLines.get(0));
        Assert.assertEquals("FOLDING BIKE EcoRide; 50; 8400; 8600; false; brown; 1609", fileLines.get(1));
    }

    @Test
    public void readBikesTest() throws IllegalDataSourceException {
        bikeDao = new FileBikeDao(Path.of("src/main/resources/test/fileWithFiveTrueBikes.txt"));
        List<Bike> loadedBikes = bikeDao.readBikes();

        Assert.assertEquals(EXPECTED_LIST_SIZE, loadedBikes.size());
        Assert.assertEquals(0, loadedBikes.indexOf(FIRST_BIKE));

        expectedEx.expect(IllegalDataSourceException.class);
        bikeDao = new FileBikeDao(Path.of("src/main/resources/test/wrongBikeFormat.txt"));
        bikeDao.readBikes();

        expectedEx.expect(IllegalDataSourceException.class);
        bikeDao = new FileBikeDao(Path.of("wrongFile"));
        bikeDao.readBikes();
    }
}
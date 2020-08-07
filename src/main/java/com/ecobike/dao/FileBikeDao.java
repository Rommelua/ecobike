package com.ecobike.dao;

import com.ecobike.Communicator;
import com.ecobike.DataHolder;
import com.ecobike.EcoBikeApplication;
import com.ecobike.exception.IllegalDataSourceException;
import com.ecobike.model.Bike;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class responsible for reading data from file and parsing text to Bike objects,
 * and for writing to file Bike objects in text format.
 * Old data in file will be replaced by new one.
 */
public class FileBikeDao implements BikeDao {

    private static final DataHolder dataHolder = DataHolder.getInstance();
    private static final Communicator communicator = EcoBikeApplication.communicator;
    private static final FileBikeParser parser = new FileBikeParser();
    /**
     * Path to data file.
     */
    private Path file;

    public FileBikeDao(Path file) {
        this.file = file;
    }

    /**
     * Method reads text from file and loads parsed Bike objects to DataHolder.
     *
     * @throws IllegalDataSourceException if no one Bike object parsed from file.
     */
    @Override
    public void loadBikes() throws IllegalDataSourceException {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(file);
        } catch (IOException e) {
            throw new IllegalDataSourceException(e);
        }
        List<Bike> bikes = new ArrayList<>(lines.size());
        List<String> wrongLinesInfo = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            try {
                bikes.add(parser.parseBike(lines.get(i)));
            } catch (IllegalArgumentException e) {
                wrongLinesInfo.add("Line No. " + (i + 1) + " has wrong format");
            }
        }
        if (bikes.isEmpty()) {
            throw new IllegalDataSourceException();
        }
        wrongLinesInfo.forEach(communicator::writeMessage);
        dataHolder.init(bikes);
        communicator.writeMessage(bikes.size() + " bike items has been read from the file");
    }

    /**
     * Method writes to file Bike objects in text format.
     * Old data in file will be replaced with new one.
     */
    @Override
    public void saveBikes() {
        List<String> dataToWrite = dataHolder.getUnmodifiableBikeList().stream()
                .map(Bike::toFileWriterString)
                .collect(Collectors.toList());
        try {
            Files.write(file, dataToWrite);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

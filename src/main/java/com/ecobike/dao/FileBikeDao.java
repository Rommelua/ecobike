package com.ecobike.dao;

import com.ecobike.Communicator;
import com.ecobike.EcoBikeApplication;
import com.ecobike.exception.IllegalDataSourceException;
import com.ecobike.model.Bike;
import com.ecobike.parser.BikeParser;
import com.ecobike.parser.FileBikeParser;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class responsible for reading data from file and parsing text to Bike objects,
 * and for writing to file Bike objects in text format.
 * Old data in file will be replaced by new one.
 */
public class FileBikeDao implements BikeDao {

    private static final Communicator communicator = EcoBikeApplication.getCommunicator();
    /**
     * Path to data file.
     */
    private final Path file;

    public FileBikeDao(Path file) {
        this.file = file;
    }

    /**
     * Method reads data from data source.
     *
     * @throws IllegalDataSourceException if no one Bike object parsed from file.
     */
    @Override
    public List<Bike> readBikes() throws IllegalDataSourceException {
        List<String> lines;
        try {
            lines = Files.readAllLines(file);
        } catch (IOException e) {
            throw new IllegalDataSourceException(e);
        }
        List<Bike> bikes = new ArrayList<>(lines.size());
        List<String> wrongLinesInfo = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            try {
                String line = lines.get(i);
                BikeParser parser = FileBikeParser.getBikeParser(line);
                bikes.add(parser.parseBike(line));
            } catch (RuntimeException e) {
                wrongLinesInfo.add("Line No. " + (i + 1) + " has wrong format");
            }
        }
        if (bikes.isEmpty()) {
            throw new IllegalDataSourceException();
        }
        wrongLinesInfo.forEach(communicator::writeMessage);
        communicator.writeMessage(bikes.size() + " bike items has been read from the file");
        return bikes;
    }

    /**
     * Method writes to file Bike objects in text format.
     * Old data in file will be replaced with new one.
     */
    @Override
    public void saveBikes(List<? extends Bike> bikes) {
        List<String> dataToWrite = bikes.stream()
                .map(Bike::toFileWriterString)
                .collect(Collectors.toList());
        try {
            Files.write(file, dataToWrite);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

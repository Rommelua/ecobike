package com.ecobike;

import com.ecobike.exception.IllegalFileFormatException;
import com.ecobike.model.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class responsible for reading data from file and parsing text to Bike objects.
 */
public class BikeFileReader {
    /**
     * Path to data file.
     */
    private Path file;
    private final DataHolder dataHolder = DataHolder.getInstance();
    private final Communicator communicator = EcoBikeApplication.COMMUNICATOR;

    public void setPath(Path file) {
        this.file = file;
    }

    /**
     * Method reads text from file and loads parsed Bike objects to DataHolder.
     *
     * @throws IllegalFileFormatException if no one Bike object parsed from file.
     */
    public void loadData() throws IllegalFileFormatException {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.lines(file).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Bike> bikes = new ArrayList<>(lines.size());
        for (int i = 0; i < lines.size(); i++) {
            try {
                bikes.add(parseBike(lines.get(i)));
            } catch (IllegalArgumentException e) {
                communicator.writeMessage("Line No. " + (i + 1) + "has wrong format");
            }
        }
        if (bikes.isEmpty()) {
            throw new IllegalFileFormatException();
        }
        dataHolder.addBikes(bikes);
        communicator.writeMessage(bikes.size() + " bike items has been read from the file");
    }

    /**
     * Method parses single String line to Bike object.
     *
     * @param line string to be parsed.
     * @return parsed Bike object
     */
    private Bike parseBike(String line) {
        try {
            if (line.startsWith(BikeType.SPEEDELEC.toString())) {
                String[] toConstructor = line.replace(BikeType.SPEEDELEC + " ", "").split("; ");
                return new SpeedelecBike(toConstructor[0], Integer.parseInt(toConstructor[1]),
                        Integer.parseInt(toConstructor[2]), Boolean.parseBoolean(toConstructor[3]),
                        Integer.parseInt(toConstructor[4]), toConstructor[5],
                        Integer.parseInt(toConstructor[6]));
            }
            if (line.startsWith(BikeType.E_BIKE.toString())) {
                String[] toConstructor = line.replace(BikeType.E_BIKE + " ", "").split("; ");
                return new EBike(toConstructor[0], Integer.parseInt(toConstructor[1]),
                        Integer.parseInt(toConstructor[2]), Boolean.parseBoolean(toConstructor[3]),
                        Integer.parseInt(toConstructor[4]), toConstructor[5],
                        Integer.parseInt(toConstructor[6]));
            }
            if (line.startsWith(BikeType.FOLDING_BIKE.toString())) {
                String[] toConstructor = line.replace(BikeType.FOLDING_BIKE + " ", "").split("; ");
                return new FoldingBike(toConstructor[0], Integer.parseInt(toConstructor[1]),
                        Integer.parseInt(toConstructor[2]), Integer.parseInt(toConstructor[3]),
                        Boolean.parseBoolean(toConstructor[4]), toConstructor[5],
                        Integer.parseInt(toConstructor[6]));
            }
        } catch (RuntimeException e) {
            throw new IllegalArgumentException();
        }
        throw new IllegalArgumentException();
    }
}

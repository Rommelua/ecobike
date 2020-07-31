package com.ecobike.dao;

import static com.ecobike.EcoBikeApplication.COMMUNICATOR;

import com.ecobike.DataHolder;
import com.ecobike.exception.IllegalDataSourceException;
import com.ecobike.model.Bike;
import com.ecobike.model.BikeType;
import com.ecobike.model.EBike;
import com.ecobike.model.FoldingBike;
import com.ecobike.model.SpeedelecBike;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Singleton class responsible for reading data from file and parsing text to Bike objects,
 * and for writing to file Bike objects in text format.
 * Old data in file will be replaced by new one.
 */
public class FileBikeDao implements BikeDao {

    private static final FileBikeDao INSTANCE = new FileBikeDao();

    private static final DataHolder DATA_HOLDER = DataHolder.getInstance();

    /**
     * Path to data file.
     */
    private Path file;

    private FileBikeDao() {
    }

    public static FileBikeDao getInstance() {
        return INSTANCE;
    }

    /**
     * Method sets data file.
     *
     * @param address string with path to data source.
     */
    @Override
    public void setSource(String address) {
        file = Paths.get(address);
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
            e.printStackTrace();
        }
        List<Bike> bikes = new ArrayList<>(lines.size());
        List<String> wrongLinesInfo = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            try {
                bikes.add(parseBike(lines.get(i)));
            } catch (IllegalArgumentException e) {
                wrongLinesInfo.add("Line No. " + (i + 1) + " has wrong format");
            }
        }
        if (bikes.isEmpty()) {
            throw new IllegalDataSourceException();
        }
        wrongLinesInfo.forEach(COMMUNICATOR::writeMessage);
        DATA_HOLDER.init(bikes);
        COMMUNICATOR.writeMessage(bikes.size() + " bike items has been read from the file");
    }

    /**
     * Method writes to file Bike objects in text format.
     * Old data in file will be replaced with new one.
     */
    @Override
    public void writeBikes() {
        List<String> dataToWrite = DATA_HOLDER.getUnmodifiableBikeList().stream()
                .map(Bike::toFileWriterString)
                .collect(Collectors.toList());
        try {
            Files.write(file, dataToWrite);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                String[] parameters = line.replace(BikeType.SPEEDELEC + " ", "").split("; ");
                checkParametersForEmpties(parameters);
                return new SpeedelecBike(parameters[0], Integer.parseInt(parameters[1]),
                        Integer.parseInt(parameters[2]), parseBoolean(parameters[3]),
                        Integer.parseInt(parameters[4]), parameters[5],
                        Integer.parseInt(parameters[6]));
            }
            if (line.startsWith(BikeType.E_BIKE.toString())) {
                String[] parameters = line.replace(BikeType.E_BIKE + " ", "").split("; ");
                checkParametersForEmpties(parameters);
                return new EBike(parameters[0], Integer.parseInt(parameters[1]),
                        Integer.parseInt(parameters[2]), parseBoolean(parameters[3]),
                        Integer.parseInt(parameters[4]), parameters[5],
                        Integer.parseInt(parameters[6]));
            }
            if (line.startsWith(BikeType.FOLDING_BIKE.toString())) {
                String[] parameters = line.replace(BikeType.FOLDING_BIKE + " ", "").split("; ");
                checkParametersForEmpties(parameters);
                return new FoldingBike(parameters[0], Integer.parseInt(parameters[1]),
                        Integer.parseInt(parameters[2]), Integer.parseInt(parameters[3]),
                        parseBoolean(parameters[4]), parameters[5],
                        Integer.parseInt(parameters[6]));
            }
        } catch (RuntimeException e) {
            throw new IllegalArgumentException();
        }
        throw new IllegalArgumentException();
    }

    /**
     * Method parses string to boolean value.
     *
     * @param value string to be parsed
     * @return TRUE if value equals ignore case "true"
     * or FALSE if value equals ignore case "false".
     * Otherwise throws IllegalArgumentException.
     */
    private boolean parseBoolean(String value) {
        if ("true".equalsIgnoreCase(value)) {
            return true;
        }
        if ("false".equalsIgnoreCase(value)) {
            return false;
        }
        throw new IllegalArgumentException();
    }

    /**
     * Method checks string array for presence at least one
     * empty string. If present - throws IllegalArgumentException.
     *
     * @param parameters string array.
     */
    private void checkParametersForEmpties(String[] parameters) {
        boolean isEmptyPresent = Stream.of(parameters)
                .anyMatch(String::isEmpty);
        if (isEmptyPresent) {
            throw new IllegalArgumentException();
        }
    }
}

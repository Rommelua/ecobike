package com.ecobike.dao;

import com.ecobike.model.Bike;
import com.ecobike.model.BikeType;
import com.ecobike.model.EBike;
import com.ecobike.model.FoldingBike;
import com.ecobike.model.SpeedelecBike;
import java.util.stream.Stream;

public class FileBikeParser {
    public FileBikeParser() {
    }

    /**
     * Method parses single String line to Bike object.
     *
     * @param line string to be parsed.
     * @return parsed Bike object
     */
    public Bike parseBike(String line) {
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
    public boolean parseBoolean(String value) {
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
    public void checkParametersForEmpties(String[] parameters) {
        boolean isEmptyPresent = Stream.of(parameters)
                .anyMatch(String::isEmpty);
        if (isEmptyPresent) {
            throw new IllegalArgumentException();
        }
    }
}

package com.ecobike.parser;

import com.ecobike.model.Bike;
import com.ecobike.model.BikeType;
import java.util.stream.Stream;

public abstract class FileBikeParser<T extends Bike> implements BikeParser<T> {

    public static BikeParser getBikeParser(String line) {
        if (line.startsWith(BikeType.E_BIKE.toString())) {
            return new EbikeFileParser();
        }
        if (line.startsWith(BikeType.FOLDING_BIKE.toString())) {
            return new FoldingBikeFileParser();
        }
        if (line.startsWith(BikeType.SPEEDELEC.toString())) {
            return new SpedelecFileParser();
        }
        throw new IllegalArgumentException("Don't such parser exist");
    }

    /**
     * Method parses string to boolean value.
     *
     * @param value string to be parsed
     * @return TRUE if value equals ignore case "true"
     * or FALSE if value equals ignore case "false".
     * Otherwise throws IllegalArgumentException.
     */
    protected boolean parseBoolean(String value) {
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
    protected void checkParametersForEmpties(String[] parameters) {
        boolean isEmptyPresent = Stream.of(parameters)
                .anyMatch(String::isEmpty);
        if (isEmptyPresent) {
            throw new IllegalArgumentException();
        }
    }
}

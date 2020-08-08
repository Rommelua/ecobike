package com.ecobike.parser;

import com.ecobike.model.Bike;
import com.ecobike.model.BikeType;
import com.ecobike.model.FoldingBike;

public class FoldingBikeFileParser extends FileBikeParser {

    @Override
    public Bike parseBike(String line) {
        String[] parameters = line.replace(BikeType.FOLDING_BIKE + " ", "").split("; ");
        checkParametersForEmpties(parameters);
        return new FoldingBike(parameters[0], Integer.parseInt(parameters[1]),
                Integer.parseInt(parameters[2]), Integer.parseInt(parameters[3]),
                parseBoolean(parameters[4]), parameters[5],
                Integer.parseInt(parameters[6]));
    }
}

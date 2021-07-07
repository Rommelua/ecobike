package com.ecobike.parser;

import com.ecobike.model.BikeType;
import com.ecobike.model.SpeedelecBike;

public class SpedelecFileParser extends FileBikeParser<SpeedelecBike> {

    @Override
    public SpeedelecBike parseBike(String line) {
        String[] parameters = line.replace(BikeType.SPEEDELEC + " ", "").split("; ");
        checkParametersForEmpties(parameters);
        return new SpeedelecBike(parameters[0], Integer.parseInt(parameters[1]),
                Integer.parseInt(parameters[2]), parseBoolean(parameters[3]),
                Integer.parseInt(parameters[4]), parameters[5],
                Integer.parseInt(parameters[6]));
    }
}

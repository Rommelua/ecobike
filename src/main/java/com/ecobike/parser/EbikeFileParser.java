package com.ecobike.parser;

import com.ecobike.model.BikeType;
import com.ecobike.model.EBike;

public class EbikeFileParser extends FileBikeParser<EBike> {

    @Override
    public EBike parseBike(String line) {
        String[] parameters = line.replace(BikeType.E_BIKE + " ", "").split("; ");
        checkParametersForEmpties(parameters);
        return new EBike(parameters[0], Integer.parseInt(parameters[1]),
                Integer.parseInt(parameters[2]), parseBoolean(parameters[3]),
                Integer.parseInt(parameters[4]), parameters[5],
                Integer.parseInt(parameters[6]));
    }
}

package com.ecobike;

import com.ecobike.model.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DataHolder {
    private final static DataHolder INSTANCE = new DataHolder();
    private List<Bike> bikes;

    private DataHolder() {
    }

    public static DataHolder getInstance() {
        return INSTANCE;
    }

    public void loadData(Path file) {
        try {
            bikes = Files.lines(file)
                    .map(line -> parseBike(line))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addBike(Bike bike) {
        bikes.add(bike);
    }

    public List<Bike> getUnmodifiableBikeList() {
        return Collections.unmodifiableList(bikes);
    }

    private Bike parseBike(String line) {
        if (line.startsWith(BikeType.SPEEDELEC.toString())) {
            String[] toConstructor = line.replace(BikeType.SPEEDELEC + " ", "").split("; ");
            return new SpeedelecBike(toConstructor[0], Integer.parseInt(toConstructor[1]),
                    Integer.parseInt(toConstructor[2]), Boolean.parseBoolean(toConstructor[3]),
                    Integer.parseInt(toConstructor[4]), toConstructor[5],
                    Integer.parseInt(toConstructor[6]));
        }
        if(line.startsWith(BikeType.E_BIKE.toString())) {
            String[] toConstructor = line.replace(BikeType.E_BIKE + " ", "").split("; ");
            return new EBike(toConstructor[0], Integer.parseInt(toConstructor[1]),
                    Integer.parseInt(toConstructor[2]), Boolean.parseBoolean(toConstructor[3]),
                    Integer.parseInt(toConstructor[4]), toConstructor[5],
                    Integer.parseInt(toConstructor[6]));
        }
        if(line.startsWith(BikeType.FOLDING_BIKE.toString())) {
            String[] toConstructor = line.replace(BikeType.FOLDING_BIKE + " ", "").split("; ");
            return new FoldingBike(toConstructor[0], Integer.parseInt(toConstructor[1]),
                    Integer.parseInt(toConstructor[2]), Integer.parseInt(toConstructor[3]),
                    Boolean.parseBoolean(toConstructor[4]), toConstructor[5],
                    Integer.parseInt(toConstructor[6]));
        }
        return null;
    }
}

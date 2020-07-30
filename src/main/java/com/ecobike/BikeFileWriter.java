package com.ecobike;

import com.ecobike.model.Bike;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class responsible for writing to file Bike objects in text format.
 * Old data in file will be replaced by new one.
 */
public class BikeFileWriter {
    /**
     * Path to data file.
     */
    private Path file;
    private final DataHolder dataHolder = DataHolder.getInstance();

    public void setFile(Path file) {
        this.file = file;
    }

    /**
     * Method writes to file Bike objects in text format.
     * Old data in file will be replaced with new one.
     */
    public void writeData() {
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

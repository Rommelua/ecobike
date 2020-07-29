package com.ecobike;

import com.ecobike.model.Bike;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class FileWriter {
    private Path file;
    private DataHolder dataHolder = DataHolder.getInstance();

    public void setFile(Path file) {
        this.file = file;
    }

    public void writeData() {
        List<String> dataToWrite = dataHolder.getBikes().stream()
                .map(Bike::toFileWriterString)
                .collect(Collectors.toList());
        try {
            Files.write(file, dataToWrite);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

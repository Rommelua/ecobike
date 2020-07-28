package com.ecobike;

import com.ecobike.model.Bike;
import com.ecobike.model.EBike;
import com.ecobike.model.FoldingBike;
import com.ecobike.model.SpeedelecBike;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class EcoBikeApplication {
    public static final Communicator COMMUNICATOR = new ConsoleCommunicator();
    private static Path bikeDataFile;
    static {
        do {
            COMMUNICATOR.writeMessage("Enter path to ecobike.txt :");
            bikeDataFile = Paths.get(COMMUNICATOR.readString());
        } while (Files.isRegularFile(bikeDataFile));
    }
    private static List<Bike> bikeList;

    public static void main(String[] args) throws IOException {
        bikeList = Files.lines(bikeDataFile)
                .map(line -> parseBike(line))
                .collect(Collectors.toList());
        Operation operation = null;
        do {
            try {
                operation = askOperation();
                CommandExecutor.execute(operation);
            } catch (Exception e) {
                e.printStackTrace();
                COMMUNICATOR.writeMessage("Error. Check entered data.");
            }
        } while (operation != Operation.STOP_PROGRAM);
    }

    private static Operation askOperation() throws IOException {
        COMMUNICATOR.writeMessage("");
        COMMUNICATOR.writeMessage("Please make your choice:");
        COMMUNICATOR.writeMessage("\t 1 - Show the entire EcoBike catalog");
        COMMUNICATOR.writeMessage("\t 2 – Add a new folding bike");
        COMMUNICATOR.writeMessage("\t 3 – Add a new speedelec");
        COMMUNICATOR.writeMessage("\t 4 – Add a new e-bike");
        COMMUNICATOR.writeMessage("\t 5 – Find the first item of a particular brand");
        COMMUNICATOR.writeMessage("\t 6 – Write to file");
        COMMUNICATOR.writeMessage("\t 7 – Stop the program");

        return Operation.values()[COMMUNICATOR.readInt() - 1];
    }

    private static Bike parseBike(String line) {
        String[] toConstructor = line.split(" ", 2)[1].split("; ");
        switch (line.split(" ")[0]) {
            case "SPEEDELEC":
                return new SpeedelecBike(toConstructor[0], Integer.parseInt(toConstructor[1]),
                        Integer.parseInt(toConstructor[2]), Boolean.parseBoolean(toConstructor[3]),
                        Integer.parseInt(toConstructor[4]), toConstructor[5],
                        Integer.parseInt(toConstructor[6]));
            case "E-BIKE":
                return new EBike(toConstructor[0], Integer.parseInt(toConstructor[1]),
                        Integer.parseInt(toConstructor[2]), Boolean.parseBoolean(toConstructor[3]),
                        Integer.parseInt(toConstructor[4]), toConstructor[5],
                        Integer.parseInt(toConstructor[6]));
            case "FOLDING BIKE":
                return new FoldingBike(toConstructor[0], Integer.parseInt(toConstructor[1]),
                        Integer.parseInt(toConstructor[2]), Integer.parseInt(toConstructor[3]),
                        Boolean.parseBoolean(toConstructor[4]), toConstructor[5],
                        Integer.parseInt(toConstructor[6]));
        }
        return null;
    }
}

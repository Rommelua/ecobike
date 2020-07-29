package com.ecobike;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EcoBikeApplication {
    public static final Communicator COMMUNICATOR = new ConsoleCommunicator();
    private static final DataHolder DATA_HOLDER = DataHolder.getInstance();
    public static final FileWriter FILE_WRITER = new FileWriter();

    public static void main(String[] args) throws IOException {
        Path bikeDataFile;
        do {
            COMMUNICATOR.writeMessage("Enter path to ecobike.txt :");
            bikeDataFile = Paths.get(COMMUNICATOR.readString());
        } while (!Files.isRegularFile(bikeDataFile));
        DATA_HOLDER.loadData(bikeDataFile);
        FILE_WRITER.setFile(bikeDataFile);

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
}

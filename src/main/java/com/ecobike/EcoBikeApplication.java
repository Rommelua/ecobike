package com.ecobike;

import com.ecobike.dao.BikeDAO;
import com.ecobike.dao.FileBikeDAO;
import com.ecobike.exception.IllegalDataSourceException;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Main class of the EcoBike application.
 */
public class EcoBikeApplication {
    /**
     * Communicator for communication with user.
     */
    public static final Communicator COMMUNICATOR = new ConsoleCommunicator();
    /**
     * Object for loading data from and writing data to file.
     */
    public static final BikeDAO BIKE_DAO = FileBikeDAO.getInstance();
    /**
     * Variable for keeping information was data changed
     * since last writing to file or not.
     */
    public static boolean isDataChanged = false;

    /**
     * Main method of the application.
     * @param args system arguments.
     */
    public static void main(String[] args) {
        boolean isFileParsed = false;
        while (!isFileParsed) {
            String bikeDataFile;
            do {
                COMMUNICATOR.writeMessage("Enter path to Bikes data file :");
                bikeDataFile = COMMUNICATOR.readNotEmptyString();
            } while (!Files.isRegularFile(Path.of(bikeDataFile)));
            BIKE_DAO.setSource(bikeDataFile);
            try {
                BIKE_DAO.loadBikes();
                isFileParsed = true;
            } catch (IllegalDataSourceException e) {
                COMMUNICATOR.writeMessage("File has wrong format or empty");
                COMMUNICATOR.writeMessage("");
            }
        }
        isDataChanged = false;
        while (true){
            Operation operation = askOperation();
            if (isDataChanged && operation == Operation.STOP_PROGRAM) {
                COMMUNICATOR.writeMessage("You are going to exit without saving changed data.\n" +
                        "Do you wont to save data?");
                if (COMMUNICATOR.readBoolean()) {
                    CommandExecutor.execute(Operation.WRITE_TO_FILE);
                }
            }
            if (!isDataChanged && operation == Operation.WRITE_TO_FILE) {
                COMMUNICATOR.writeMessage("Data has not been changed or already has saved");
                continue;
            }
            if (operation == Operation.STOP_PROGRAM) {
                if (COMMUNICATOR.confirmAction("EXIT from program")) {
                    COMMUNICATOR.writeMessage("Good bay!");
                    return;
                }
                continue;
            }
            CommandExecutor.execute(operation);
            isDataChanged = operation != Operation.WRITE_TO_FILE && isDataChanged;
        }
    }

    /**
     * Method asks user to choose operation.
     * @return chosen operation.
     */
    private static Operation askOperation() {
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

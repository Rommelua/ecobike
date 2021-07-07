package com.ecobike;

import com.ecobike.dao.BikeDao;
import com.ecobike.dao.FileBikeDao;
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
    private static final Communicator communicator = new ConsoleCommunicator();
    /**
     * Object for loading data from and writing data to file.
     */
    private static BikeDao bikeDao;

    /**
     * Main method of the application.
     *
     * @param args system arguments.
     */
    public static void main(String[] args) {
        initFileBikeDao();
        while (true) {
            try {
                Operation operation = askOperation();
                CommandExecutor.execute(operation);
                if (operation == Operation.STOP_PROGRAM
                        && communicator.confirmAction("EXIT from program")) {
                    communicator.writeMessage("Good bay!");
                    break;
                }
            } catch (Exception e) {
                communicator.writeMessage("Error occurred. Repeat action.");
            }
        }
    }

    public static Communicator getCommunicator() {
        return communicator;
    }

    public static BikeDao getBikeDao() {
        return bikeDao;
    }

    private static void initFileBikeDao() {
        boolean isFileParsed = false;
        while (!isFileParsed) {
            Path bikeDataFile;
            do {
                communicator.writeMessage("Enter path to Bikes data file :");
                bikeDataFile = Path.of(communicator.readNotEmptyString());
            } while (!Files.isRegularFile(bikeDataFile));
            bikeDao = new FileBikeDao(bikeDataFile);
            try {
                DataHolder dataHolder = DataHolder.getInstance(bikeDao);
                dataHolder.init();
                isFileParsed = true;
            } catch (IllegalDataSourceException e) {
                communicator.writeMessage("File has wrong format or empty");
                communicator.writeMessage("");
            }
        }
    }

    /**
     * Method asks user to choose operation.
     *
     * @return chosen operation.
     */
    private static Operation askOperation() {
        communicator.writeMessage("");
        communicator.writeMessage("Please make your choice:");
        communicator.writeMessage("\t 1 - Show the entire EcoBike catalog");
        communicator.writeMessage("\t 2 – Add a new folding bike");
        communicator.writeMessage("\t 3 – Add a new speedelec");
        communicator.writeMessage("\t 4 – Add a new e-bike");
        communicator.writeMessage("\t 5 – Find the first item of a particular brand");
        communicator.writeMessage("\t 6 – Write to file");
        communicator.writeMessage("\t 7 – Stop the program");

        return Operation.values()[communicator.readInt() - 1];
    }
}

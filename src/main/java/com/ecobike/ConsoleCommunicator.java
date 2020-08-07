package com.ecobike;

import com.ecobike.model.Bike;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Class responsible for communication with user with console.
 */
public class ConsoleCommunicator implements Communicator {

    private static final BufferedReader reader
            = new BufferedReader(new InputStreamReader(System.in));
    /**
     * Number of bikes to be shown on one page.
     */
    private static final int BIKES_ON_PAGE_QUANTITY = 50;

    /**
     * Method writs message to user.
     */
    @Override
    public void writeMessage(String message) {
        System.out.println(message);
    }

    /**
     * Method reads string from user.
     *
     * @return entered string
     */
    @Override
    public String readString() {
        String entry = null;
        while (entry == null) {
            try {
                entry = reader.readLine();
            } catch (IOException e) {
                writeMessage("Repeat your entry:");
            }
        }
        return entry;
    }

    /**
     * Method reads not empty string from user.
     *
     * @return entered string.
     */
    @Override
    public String readNotEmptyString() {
        String entry;
        while ((entry = readString()).isEmpty()) {
            writeMessage("Empty entry is not allowed. Repeat, please.");
        }
        return entry;
    }

    /**
     * Method reads integer from user.
     * For empty entry returns 0.
     *
     * @return 0 or positive int value.
     */
    @Override
    public int readInt() {
        while (true) {
            String entry = readString();
            if (entry.isEmpty()) {
                return 0;
            }
            int intValue;
            try {
                if ((intValue = Integer.parseInt(entry)) >= 0) {
                    return intValue;
                }
            } catch (NumberFormatException e) {
                writeMessage("Repeat your entry (only positive number):");
            }
        }
    }

    /**
     * Method reads integer from user.
     * Only positive values are allowed.
     *
     * @return positive int value.
     */
    @Override
    public int readPositiveInt() {
        int entry;
        while ((entry = readInt()) == 0) {
            writeMessage("Empty entry or zero is not allowed. Repeat, please.");
        }
        return entry;
    }

    /**
     * Method reads boolean from user.
     *
     * @return boolean value from user.
     */
    @Override
    public boolean readBoolean() {
        writeMessage("Type 1 for TRUE or 2 for FALSE");
        while (true) {
            String entry = readString();
            if (entry.equals("1")) {
                return true;
            }
            if (entry.equals("2")) {
                return false;
            }
            writeMessage("Wrong entry. Type 1 for TRUE or 2 for FALSE");
        }
    }

    /**
     * Method prints info about bikes from the list
     * page by page (max 50 on one page by default).
     *
     * @param bikes list of bikes for printing
     */
    @Override
    public void printBikes(List<Bike> bikes) {
        for (int i = 0; i < bikes.size(); i++) {
            writeMessage(i + 1 + ". " + bikes.get(i).toOutputString());
            if ((i + 1) % BIKES_ON_PAGE_QUANTITY == 0) {
                writeMessage("");
                do {
                    writeMessage("For showing next page press \"Enter\"");
                } while (!readString().isEmpty());
            }
        }
    }

    /**
     * Ask user to confirm operation.
     *
     * @param message to be shown for user.
     * @return true if confirms or false otherwise.
     */
    @Override
    public boolean confirmAction(String message) {
        writeMessage("You are going to:");
        writeMessage(message);
        writeMessage("");
        do {
            writeMessage("Enter \"y\" for \"yes\" or \"n\" for \"no\"");
            String entry = readString();
            if (entry.equalsIgnoreCase("y")) {
                return true;
            }
            if (entry.equalsIgnoreCase("n")) {
                return false;
            }
        } while (true);
    }
}

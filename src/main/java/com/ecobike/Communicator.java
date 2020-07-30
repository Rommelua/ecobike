package com.ecobike;

import com.ecobike.model.Bike;

import java.util.List;

/**
 * Interface should be implemented by class responsible for communication with user.
 */
public interface Communicator {
    /**
     * Method writs message to user.
     */
    void writeMessage(String message);

    /**
     * Method reads string from user.
     *
     * @return entered string.
     */
    String readString();

    /**
     * Method reads not empty string from user.
     * @return entered string.
     */
    String readNotEmptyString();

    /**
     * Method reads integer from user.
     * For empty entry returns 0.
     *
     * @return 0 or positive int value.
     */
    int readInt();

    /**
     * Method reads integer from user.
     * Only positive values are allowed.
     *
     * @return positive int value.
     */
    int readPositiveInt();

    /**
     * Method reads boolean from user.
     * @return boolean value from user.
     */
    boolean readBoolean();

    /**
     * Method prints info about bikes from the list
     * page by page.
     *
     * @param bikes list of bikes for printing
     */
    void printBikes(List<Bike> bikes);

    /**
     * Ask user to confirm operation.
     *
     * @param message to be shown for user.
     * @return true if confirms or false otherwise.
     */
    boolean confirmAction(String message);
}

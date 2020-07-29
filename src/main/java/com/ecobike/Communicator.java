package com.ecobike;

import java.io.IOException;

public interface Communicator {
    /**
     * Method writs message to user.
     */
    void writeMessage(String message);

    /**
     * Method reads string from user.
     */
    String readString();

    /**
     * Method reads integer from user.
     */
    int readInt();
    /**
     * Method reads boolean from user.
     */
    boolean readBoolean();
}

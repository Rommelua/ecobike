package com.ecobike;

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
     * For empty entry returns 0.
     */
    int readInt();
    /**
     * Method reads boolean from user.
     */
    boolean readBoolean();
}

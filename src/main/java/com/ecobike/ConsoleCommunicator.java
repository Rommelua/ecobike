package com.ecobike;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleCommunicator implements Communicator {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public void writeMessage(String message) {
        System.out.println(message);
    }

    @Override
    public String readString() {
        String entry = null;
        while (entry == null) {
            try {
                entry = reader.readLine();
            } catch (IOException e) {
                System.out.println("Repeat your entry:");
            }
        }
        return entry;
    }

    @Override
    public int readInt() {
        int result = -1;
        while (result < 0) {
            try {
                result = Integer.parseInt(readString());
            } catch (NumberFormatException e) {
                System.out.println("Repeat your entry (only number):");
            }
        }
        return result;
    }
}

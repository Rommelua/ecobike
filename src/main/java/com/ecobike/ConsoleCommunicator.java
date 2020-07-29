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
                System.out.println("Repeat your entry (only positive number):");
            }
        }
    }

    @Override
    public boolean readBoolean() {
        System.out.println("Type 1 for TRUE or 2 for FALSE");
        while (true) {
            String entry = readString();
            if (entry.equals("1")) {
                return true;
            }
            if (entry.equals("2")) {
                return false;
            }
            System.out.println("Wrong entry. Type 1 for TRUE or 2 for FALSE");
        }
    }
}

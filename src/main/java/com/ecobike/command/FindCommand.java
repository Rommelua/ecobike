package com.ecobike.command;

import com.ecobike.model.BikeType;

public class FindCommand implements Command {

    @Override
    public void execute() {
        BikeType bikeType;
        while (true) {
            COMMUNICATOR.writeMessage("Select bike type you wont to find");
            COMMUNICATOR.writeMessage("\t 1 - " + BikeType.FOLDING_BIKE);
            COMMUNICATOR.writeMessage("\t 2 - " + BikeType.E_BIKE);
            COMMUNICATOR.writeMessage("\t 3 - " + BikeType.SPEEDELEC);
            int bikeTypeNumber;
            if ((bikeTypeNumber = COMMUNICATOR.readInt()) >= 1 && bikeTypeNumber <= 3) {
                bikeType = BikeType.values()[bikeTypeNumber - 1];
                break;
            }
            COMMUNICATOR.writeMessage("=== Wrong entry ===");
            COMMUNICATOR.writeMessage("");
        }

        COMMUNICATOR.writeMessage("Enter bike brand:");
        String brand = "";
        while ((brand = COMMUNICATOR.readString()).isEmpty()) {
            COMMUNICATOR.writeMessage("Can't skip. Enter bike brand:");
        }

        COMMUNICATOR.writeMessage("You may choose next parameters (for skipping parameter press \"Enter\"):");
        COMMUNICATOR.writeMessage("Enter min weight:");
        int minWeight = COMMUNICATOR.readInt();

        COMMUNICATOR.writeMessage("Enter max weight:");
        int maxWeight = COMMUNICATOR.readInt();

        COMMUNICATOR.writeMessage("Enter lights presence (Type 1 for TRUE or 2 for FALSE):");
        boolean isLightsOptionEntered = false;
        boolean isLightsPresent;
        while (true) {
            String entry = COMMUNICATOR.readString();
            if (entry.isEmpty()) {
                break;
            }
            if (entry.equals("1")) {
                isLightsPresent = true;
                isLightsOptionEntered = true;
                break;
            }
            if (entry.equals("2")) {
                isLightsPresent = false;
                isLightsOptionEntered = true;
                break;
            }
            System.out.println("Wrong entry. Type 1 for TRUE, 2 for FALSE or \"Enter\" for skipping");
        }

        COMMUNICATOR.writeMessage("Enter color:");
        String color = COMMUNICATOR.readString();

        COMMUNICATOR.writeMessage("Enter min price:");
        int minPrice = COMMUNICATOR.readInt();

        COMMUNICATOR.writeMessage("Enter max price:");
        int maxPrice = COMMUNICATOR.readInt();

        int minWheelSize;
        int maxWheelSize;
        int minNumberOfGears;
        int maxNumberOfGears;
        if (bikeType == BikeType.FOLDING_BIKE) {
            COMMUNICATOR.writeMessage("Enter min wheel size:");
            minWheelSize = COMMUNICATOR.readInt();
            COMMUNICATOR.writeMessage("Enter max wheel size:");
            maxWheelSize = COMMUNICATOR.readInt();
            COMMUNICATOR.writeMessage("Enter min number of gears:");
            minNumberOfGears = COMMUNICATOR.readInt();
            COMMUNICATOR.writeMessage("Enter max number of gears:");
            maxNumberOfGears = COMMUNICATOR.readInt();
        }

        int minMaxBikeSpeed;
        int maxMaxBikeSpeed;
        int minBatteryCapacity;
        int maxBatteryCapacity;
        if (bikeType == BikeType.E_BIKE || bikeType == BikeType.SPEEDELEC) {
            COMMUNICATOR.writeMessage("Enter minimum max bike speed:");
            minMaxBikeSpeed = COMMUNICATOR.readInt();
            COMMUNICATOR.writeMessage("Enter maximum max bike speed:");
            maxMaxBikeSpeed = COMMUNICATOR.readInt();
            COMMUNICATOR.writeMessage("Enter min battery capacity:");
            minBatteryCapacity = COMMUNICATOR.readInt();
            COMMUNICATOR.writeMessage("Enter max battery capacity:");
            maxBatteryCapacity = COMMUNICATOR.readInt();
        }
    }
}

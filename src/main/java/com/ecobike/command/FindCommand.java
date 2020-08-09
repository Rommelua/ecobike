package com.ecobike.command;

import com.ecobike.SearchParameterContainer;
import com.ecobike.model.Bike;
import com.ecobike.model.BikeType;
import java.util.List;

/**
 * Class responsible for executing Find operation
 */
public class FindCommand implements Command {

    @Override
    public void execute() {
        SearchParameterContainer paramContainer = new SearchParameterContainer();
        while (true) {
            communicator.writeMessage("Select bike type you wont to find");
            int numberOfBikeTypes = BikeType.values().length;
            for (int i = 1; i <= numberOfBikeTypes; i++) {
                communicator.writeMessage("\t " + i + " - " + BikeType.values()[i - 1]);
            }
            int bikeTypeNumber;
            if ((bikeTypeNumber = communicator.readInt()) >= 1
                    && bikeTypeNumber <= numberOfBikeTypes) {
                paramContainer.setBikeType(BikeType.values()[bikeTypeNumber - 1]);
                break;
            }
            communicator.writeMessage("=== Wrong entry ===");
            communicator.writeMessage("");
        }

        communicator.writeMessage("Enter bike brand:");
        String brand;
        while ((brand = communicator.readString()).isEmpty()) {
            communicator.writeMessage("Can't skip. Enter bike brand:");
        }
        paramContainer.setBrand(brand);

        communicator.writeMessage("You may choose next parameters "
                                  + "(for skipping parameter press \"Enter\"):");
        communicator.writeMessage("Enter min weight:");
        paramContainer.setMinWeight(communicator.readInt());

        communicator.writeMessage("Enter max weight:");
        paramContainer.setMaxWeight(communicator.readInt());

        communicator.writeMessage("Enter lights presence (Type 1 for TRUE or 2 for FALSE):");
        while (true) {
            String entry = communicator.readString();
            if (entry.isEmpty()) {
                break;
            }
            if (entry.equals("1")) {
                paramContainer.setLightsPresent(true);
                paramContainer.setLightsOptionEntered(true);
                break;
            }
            if (entry.equals("2")) {
                paramContainer.setLightsPresent(false);
                paramContainer.setLightsOptionEntered(true);
                break;
            }
            communicator.writeMessage("Wrong entry. "
                                      + "Type 1 for TRUE, 2 for FALSE or \"Enter\" for skipping");
        }

        communicator.writeMessage("Enter color:");
        paramContainer.setColor(communicator.readString());

        communicator.writeMessage("Enter min price:");
        paramContainer.setMinPrice(communicator.readInt());

        communicator.writeMessage("Enter max price:");
        paramContainer.setMaxPrice(communicator.readInt());

        if (paramContainer.getBikeType() == BikeType.FOLDING_BIKE) {
            communicator.writeMessage("Enter min wheel size:");
            paramContainer.setMinWheelSize(communicator.readInt());
            communicator.writeMessage("Enter max wheel size:");
            paramContainer.setMaxWheelSize(communicator.readInt());
            communicator.writeMessage("Enter min number of gears:");
            paramContainer.setMinNumberOfGears(communicator.readInt());
            communicator.writeMessage("Enter max number of gears:");
            paramContainer.setMaxNumberOfGears(communicator.readInt());
        }

        if (paramContainer.getBikeType() == BikeType.E_BIKE
                || paramContainer.getBikeType() == BikeType.SPEEDELEC) {
            communicator.writeMessage("Enter minimum max bike speed:");
            paramContainer.setMinMaxBikeSpeed(communicator.readInt());
            communicator.writeMessage("Enter maximum max bike speed:");
            paramContainer.setMaxMaxBikeSpeed(communicator.readInt());
            communicator.writeMessage("Enter min battery capacity:");
            paramContainer.setMinBatteryCapacity(communicator.readInt());
            communicator.writeMessage("Enter max battery capacity:");
            paramContainer.setMaxBatteryCapacity(communicator.readInt());
        }

        List<Bike> bikes = dataHolder.findBikesByParameter(paramContainer);
        if (bikes.isEmpty()) {
            communicator.writeMessage("No bikes matches your query.");
        } else {
            communicator.writeMessage(bikes.size() + " bikes matches your query:");
            communicator.writeMessage("");
            communicator.printBikes(bikes);
        }
    }
}

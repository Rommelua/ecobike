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
            COMMUNICATOR.writeMessage("Select bike type you wont to find");
            COMMUNICATOR.writeMessage("\t 1 - " + BikeType.FOLDING_BIKE);
            COMMUNICATOR.writeMessage("\t 2 - " + BikeType.E_BIKE);
            COMMUNICATOR.writeMessage("\t 3 - " + BikeType.SPEEDELEC);
            int bikeTypeNumber;
            if ((bikeTypeNumber = COMMUNICATOR.readInt()) >= 1 && bikeTypeNumber <= 3) {
                paramContainer.setBikeType(BikeType.values()[bikeTypeNumber - 1]);
                break;
            }
            COMMUNICATOR.writeMessage("=== Wrong entry ===");
            COMMUNICATOR.writeMessage("");
        }

        COMMUNICATOR.writeMessage("Enter bike brand:");
        String brand;
        while ((brand = COMMUNICATOR.readString()).isEmpty()) {
            COMMUNICATOR.writeMessage("Can't skip. Enter bike brand:");
        }
        paramContainer.setBrand(brand);

        COMMUNICATOR.writeMessage("You may choose next parameters "
                + "(for skipping parameter press \"Enter\"):");
        COMMUNICATOR.writeMessage("Enter min weight:");
        paramContainer.setMinWeight(COMMUNICATOR.readInt());

        COMMUNICATOR.writeMessage("Enter max weight:");
        paramContainer.setMaxWeight(COMMUNICATOR.readInt());

        COMMUNICATOR.writeMessage("Enter lights presence (Type 1 for TRUE or 2 for FALSE):");
        while (true) {
            String entry = COMMUNICATOR.readString();
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
            COMMUNICATOR.writeMessage("Wrong entry. "
                    + "Type 1 for TRUE, 2 for FALSE or \"Enter\" for skipping");
        }

        COMMUNICATOR.writeMessage("Enter color:");
        paramContainer.setColor(COMMUNICATOR.readString());

        COMMUNICATOR.writeMessage("Enter min price:");
        paramContainer.setMinPrice(COMMUNICATOR.readInt());

        COMMUNICATOR.writeMessage("Enter max price:");
        paramContainer.setMaxPrice(COMMUNICATOR.readInt());

        if (paramContainer.getBikeType() == BikeType.FOLDING_BIKE) {
            COMMUNICATOR.writeMessage("Enter min wheel size:");
            paramContainer.setMinWheelSize(COMMUNICATOR.readInt());
            COMMUNICATOR.writeMessage("Enter max wheel size:");
            paramContainer.setMaxWheelSize(COMMUNICATOR.readInt());
            COMMUNICATOR.writeMessage("Enter min number of gears:");
            paramContainer.setMinNumberOfGears(COMMUNICATOR.readInt());
            COMMUNICATOR.writeMessage("Enter max number of gears:");
            paramContainer.setMaxNumberOfGears(COMMUNICATOR.readInt());
        }

        if (paramContainer.getBikeType() == BikeType.E_BIKE
                || paramContainer.getBikeType() == BikeType.SPEEDELEC) {
            COMMUNICATOR.writeMessage("Enter minimum max bike speed:");
            paramContainer.setMinMaxBikeSpeed(COMMUNICATOR.readInt());
            COMMUNICATOR.writeMessage("Enter maximum max bike speed:");
            paramContainer.setMaxMaxBikeSpeed(COMMUNICATOR.readInt());
            COMMUNICATOR.writeMessage("Enter min battery capacity:");
            paramContainer.setMinBatteryCapacity(COMMUNICATOR.readInt());
            COMMUNICATOR.writeMessage("Enter max battery capacity:");
            paramContainer.setMaxBatteryCapacity(COMMUNICATOR.readInt());
        }

        List<Bike> bikes = DATA_HOLDER.findBikesByParameter(paramContainer);
        if (bikes.isEmpty()) {
            COMMUNICATOR.writeMessage("No bikes matches your query.");
        } else {
            COMMUNICATOR.writeMessage(bikes.size() + " bikes matches your query:");
            COMMUNICATOR.writeMessage("");
            COMMUNICATOR.printBikes(bikes);
        }
    }
}

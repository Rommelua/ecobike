package com.ecobike.command;

import com.ecobike.model.BikeType;
import com.ecobike.model.FoldingBike;

/**
 * Class responsible for adding a new Folding Bike to DataHolder
 */
public class AddFoldingBikeCommand implements Command {
    @Override
    public void execute() {
        communicator.writeMessage("You are adding new FOLDING BIKE");
        communicator.writeMessage("Enter brand:");
        String brand = communicator.readNotEmptyString();
        communicator.writeMessage("Enter wheel size:");
        int wheelSize = communicator.readPositiveInt();
        communicator.writeMessage("Enter number of gears:");
        int numberOfGears = communicator.readPositiveInt();
        communicator.writeMessage("Enter weight:");
        int weight = communicator.readPositiveInt();
        communicator.writeMessage("Enter presence of lights:");
        boolean isLightsPresent = communicator.readBoolean();
        communicator.writeMessage("Enter color:");
        String color = communicator.readNotEmptyString();
        communicator.writeMessage("Enter price:");
        int price = communicator.readPositiveInt();

        String confirmMessage = String.format("add new %s with next parametrs:\n"
                        + "brand: %s\n"
                        + "wheelSize: %d\n"
                        + "numberOfGears: %d\n"
                        + "weight: %d\n"
                        + "lights presents: %s\n"
                        + "color: %s\n"
                        + "price: %d\n",
                BikeType.FOLDING_BIKE, brand, wheelSize, numberOfGears, weight,
                isLightsPresent, color, price);

        if (communicator.confirmAction(confirmMessage)) {
            dataHolder.addBike(new FoldingBike(brand, wheelSize, numberOfGears,
                    weight, isLightsPresent, color, price));
            communicator.writeMessage("New FOLDING BIKE added.");
        }
    }
}

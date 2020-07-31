package com.ecobike.command;

import com.ecobike.model.BikeType;
import com.ecobike.model.FoldingBike;

/**
 * Class responsible for adding a new Folding Bike to DataHolder
 */
public class AddFoldingBikeCommand implements Command {
    @Override
    public void execute() {
        COMMUNICATOR.writeMessage("You are adding new FOLDING BIKE");
        COMMUNICATOR.writeMessage("Enter brand:");
        String brand = COMMUNICATOR.readNotEmptyString();
        COMMUNICATOR.writeMessage("Enter wheel size:");
        int wheelSize = COMMUNICATOR.readPositiveInt();
        COMMUNICATOR.writeMessage("Enter number of gears:");
        int numberOfGears = COMMUNICATOR.readPositiveInt();
        COMMUNICATOR.writeMessage("Enter weight:");
        int weight = COMMUNICATOR.readPositiveInt();
        COMMUNICATOR.writeMessage("Enter presence of lights:");
        boolean isLightsPresent = COMMUNICATOR.readBoolean();
        COMMUNICATOR.writeMessage("Enter color:");
        String color = COMMUNICATOR.readNotEmptyString();
        COMMUNICATOR.writeMessage("Enter price:");
        int price = COMMUNICATOR.readPositiveInt();

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

        if (COMMUNICATOR.confirmAction(confirmMessage)) {
            DATA_HOLDER.addBike(new FoldingBike(brand, wheelSize, numberOfGears,
                    weight, isLightsPresent, color, price));
            COMMUNICATOR.writeMessage("New FOLDING BIKE added.");
        }
    }
}

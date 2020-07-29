package com.ecobike.command;

import com.ecobike.model.FoldingBike;

public class AddFoldingBikeCommand implements Command {
    @Override
    public void execute() {
        COMMUNICATOR.writeMessage("You are adding new FOLDING BIKE");
        COMMUNICATOR.writeMessage("Enter brand:");
        String brand = COMMUNICATOR.readString();
        COMMUNICATOR.writeMessage("Enter wheel size:");
        int wheelSize = COMMUNICATOR.readInt();
        COMMUNICATOR.writeMessage("Enter number of gears:");
        int numberOfGears = COMMUNICATOR.readInt();
        COMMUNICATOR.writeMessage("Enter weight:");
        int weight = COMMUNICATOR.readInt();
        COMMUNICATOR.writeMessage("Enter presence of lights:");
        boolean isLightsPresent = COMMUNICATOR.readBoolean();
        COMMUNICATOR.writeMessage("Enter color:");
        String color = COMMUNICATOR.readString();
        COMMUNICATOR.writeMessage("Enter price:");
        int price = COMMUNICATOR.readInt();
        DATA_HOLDER.addBike(new FoldingBike(brand, wheelSize, numberOfGears,
                weight, isLightsPresent, color, price));
        COMMUNICATOR.writeMessage("New FOLDING BIKE added.");
    }
}

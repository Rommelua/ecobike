package com.ecobike.command;

import com.ecobike.model.SpeedelecBike;

public class AddSpeedelecBikeCommand implements Command {
    @Override
    public void execute() {
        COMMUNICATOR.writeMessage("You are adding new SPEEDELEC BIKE");
        COMMUNICATOR.writeMessage("Enter brand:");
        String brand = COMMUNICATOR.readString();
        COMMUNICATOR.writeMessage("Enter max speed:");
        int maxSpeed = COMMUNICATOR.readInt();
        COMMUNICATOR.writeMessage("Enter weight:");
        int weight = COMMUNICATOR.readInt();
        COMMUNICATOR.writeMessage("Enter presence of lights:");
        boolean isLightsPresent = COMMUNICATOR.readBoolean();
        COMMUNICATOR.writeMessage("Enter battery capacity:");
        int batteryCapacity = COMMUNICATOR.readInt();
        COMMUNICATOR.writeMessage("Enter color:");
        String color = COMMUNICATOR.readString();
        COMMUNICATOR.writeMessage("Enter price:");
        int price = COMMUNICATOR.readInt();
        DATA_HOLDER.getBikes().add(new SpeedelecBike(brand, maxSpeed, weight,
                isLightsPresent, batteryCapacity, color, price));
        COMMUNICATOR.writeMessage("New SPEEDELEC BIKE added.");
    }
}

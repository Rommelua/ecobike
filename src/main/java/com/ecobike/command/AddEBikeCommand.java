package com.ecobike.command;

import com.ecobike.model.BikeType;
import com.ecobike.model.EBike;

/**
 * Class responsible for adding a new E-Bike to DataHolder
 */
public class AddEBikeCommand implements Command {
    @Override
    public void execute() {
        communicator.writeMessage("You are adding new E-BIKE");
        communicator.writeMessage("Enter brand:");
        String brand = communicator.readNotEmptyString();
        communicator.writeMessage("Enter max speed:");
        int maxSpeed = communicator.readPositiveInt();
        communicator.writeMessage("Enter weight:");
        int weight = communicator.readPositiveInt();
        communicator.writeMessage("Enter presence of lights:");
        boolean isLightsPresent = communicator.readBoolean();
        communicator.writeMessage("Enter battery capacity:");
        int batteryCapacity = communicator.readPositiveInt();
        communicator.writeMessage("Enter color:");
        String color = communicator.readNotEmptyString();
        communicator.writeMessage("Enter price:");
        int price = communicator.readPositiveInt();

        String confirmMessage = String.format("add new %s with next parametrs:\n"
                        + "brand: %s\n"
                        + "maxSpeed: %d\n"
                        + "weight: %d\n"
                        + "lights presents: %s\n"
                        + "battery capacity: %d\n"
                        + "color: %s\n"
                        + "price: %d\n",
                BikeType.E_BIKE, brand, maxSpeed, weight,
                isLightsPresent, batteryCapacity, color, price);

        if (communicator.confirmAction(confirmMessage)) {
            dataHolder.addBike(new EBike(brand, maxSpeed, weight,
                    isLightsPresent, batteryCapacity, color, price));
            communicator.writeMessage("New E-BIKE BIKE added.");
        }
    }
}

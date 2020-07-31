package com.ecobike.command;

import com.ecobike.model.BikeType;
import com.ecobike.model.EBike;

/**
 * Class responsible for adding a new E-Bike to DataHolder
 */
public class AddEBikeCommand implements Command {
    @Override
    public void execute() {
        COMMUNICATOR.writeMessage("You are adding new E-BIKE");
        COMMUNICATOR.writeMessage("Enter brand:");
        String brand = COMMUNICATOR.readNotEmptyString();
        COMMUNICATOR.writeMessage("Enter max speed:");
        int maxSpeed = COMMUNICATOR.readPositiveInt();
        COMMUNICATOR.writeMessage("Enter weight:");
        int weight = COMMUNICATOR.readPositiveInt();
        COMMUNICATOR.writeMessage("Enter presence of lights:");
        boolean isLightsPresent = COMMUNICATOR.readBoolean();
        COMMUNICATOR.writeMessage("Enter battery capacity:");
        int batteryCapacity = COMMUNICATOR.readPositiveInt();
        COMMUNICATOR.writeMessage("Enter color:");
        String color = COMMUNICATOR.readNotEmptyString();
        COMMUNICATOR.writeMessage("Enter price:");
        int price = COMMUNICATOR.readPositiveInt();

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

        if (COMMUNICATOR.confirmAction(confirmMessage)) {
            DATA_HOLDER.addBike(new EBike(brand, maxSpeed, weight,
                    isLightsPresent, batteryCapacity, color, price));
            COMMUNICATOR.writeMessage("New E-BIKE BIKE added.");
        }
    }
}

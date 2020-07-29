package com.ecobike.command;

public class ShowCommand implements Command {

    @Override
    public void execute() {
        DATA_HOLDER.getBikes()
                .forEach(bike -> COMMUNICATOR.writeMessage(bike.toString()));
    }
}

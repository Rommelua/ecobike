package com.ecobike.command;

import com.ecobike.CommandExecutor;
import com.ecobike.Operation;

public class StopProgramCommand implements Command {
    @Override
    public void execute() {
        if (dataHolder.isDataChanged()) {
            communicator.writeMessage("You are going to exit without saving changed data.\n"
                                      + "Do you wont to save data?");
            if (communicator.readBoolean()) {
                CommandExecutor.execute(Operation.WRITE_TO_FILE);
            }
        }

    }
}

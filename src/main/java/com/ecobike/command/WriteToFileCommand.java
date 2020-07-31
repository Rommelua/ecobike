package com.ecobike.command;

import com.ecobike.EcoBikeApplication;

/**
 * Class responsible for executing writing data to file operation.
 */
public class WriteToFileCommand implements Command {

    @Override
    public void execute() {
        String confirmMessage = "write data to file";
        if (COMMUNICATOR.confirmAction(confirmMessage)) {
            EcoBikeApplication.BIKE_DAO.writeBikes();
            COMMUNICATOR.writeMessage("Data has been written successfully.");
        }
    }
}

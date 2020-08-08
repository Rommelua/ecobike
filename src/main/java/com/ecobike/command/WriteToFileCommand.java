package com.ecobike.command;

import com.ecobike.EcoBikeApplication;

/**
 * Class responsible for executing writing data to file operation.
 */
public class WriteToFileCommand implements Command {

    @Override
    public void execute() {
        if (!dataHolder.isDataChanged()) {
            communicator.writeMessage("Data has not been changed or already has saved");
        } else {
            String confirmMessage = "write data to file";
            if (communicator.confirmAction(confirmMessage)) {
                EcoBikeApplication.bikeDao.saveBikes();
                dataHolder.setDataChanged(true);
                communicator.writeMessage("Data has been written successfully.");
            }
        }
    }
}

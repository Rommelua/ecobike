package com.ecobike.command;

import com.ecobike.EcoBikeApplication;

/**
 * Class responsible for executing writing data to file operation.
 */
public class WriteToFileCommand implements Command {

    @Override
    public void execute() {
        if (!DATA_HOLDER.isDataChanged()) {
            COMMUNICATOR.writeMessage("Data has not been changed or already has saved");
        } else {
            String confirmMessage = "write data to file";
            if (COMMUNICATOR.confirmAction(confirmMessage)) {
                EcoBikeApplication.bikeDao.saveBikes();
                DATA_HOLDER.setDataChanged(true);
                COMMUNICATOR.writeMessage("Data has been written successfully.");
            }
        }

    }
}

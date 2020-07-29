package com.ecobike.command;

import com.ecobike.EcoBikeApplication;

public class WriteToFileCommand implements Command {

    @Override
    public void execute() {
        EcoBikeApplication.FILE_WRITER.writeData();
        COMMUNICATOR.writeMessage("Data has been written successfully.");
    }
}

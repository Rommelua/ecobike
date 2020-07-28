package com.ecobike.command;

import com.ecobike.EcoBikeApplication;

import java.io.IOException;

public class ExitCommand implements Command {
    @Override
    public void execute() throws IOException {
        EcoBikeApplication.COMMUNICATOR.writeMessage("Good bay!");
    }
}

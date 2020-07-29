package com.ecobike.command;

public class ExitCommand implements Command {
    @Override
    public void execute() {
        COMMUNICATOR.writeMessage("Good bay!");
    }
}

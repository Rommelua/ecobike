package com.ecobike.command;

/**
 * Class responsible for executing Show operation.
 */
public class ShowCommand implements Command {

    @Override
    public void execute() {
        COMMUNICATOR.printBikes(DATA_HOLDER.getUnmodifiableBikeList());
    }
}

package com.ecobike.command;

import com.ecobike.Communicator;
import com.ecobike.DataHolder;
import com.ecobike.EcoBikeApplication;

/**
 * Interface should be implemented by class responsible for executing
 * operation specified by user.
 */
public interface Command {
    Communicator communicator = EcoBikeApplication.communicator;
    DataHolder dataHolder = DataHolder.getInstance(EcoBikeApplication.bikeDao);

    void execute();
}

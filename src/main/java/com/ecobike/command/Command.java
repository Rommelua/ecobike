package com.ecobike.command;

import com.ecobike.Communicator;
import com.ecobike.DataHolder;
import com.ecobike.EcoBikeApplication;

/**
 * Interface should be implemented by class responsible for executing
 * operation specified by user.
 */
public interface Command {
    Communicator COMMUNICATOR = EcoBikeApplication.COMMUNICATOR;
    DataHolder DATA_HOLDER = DataHolder.getInstance();

    void execute();
}

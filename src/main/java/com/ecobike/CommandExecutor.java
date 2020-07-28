package com.ecobike;

import com.ecobike.command.Command;
import com.ecobike.command.ExitCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    private static final Map<Operation, Command> allKnownCommandsMap = new HashMap<>();

    static {
        allKnownCommandsMap.put(Operation.STOP_PROGRAM, new ExitCommand());
    }

    private CommandExecutor() {
    }

    public static void execute(Operation operation) throws Exception {
        allKnownCommandsMap.get(operation).execute();
    }
}

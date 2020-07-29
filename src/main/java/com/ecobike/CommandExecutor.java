package com.ecobike;

import com.ecobike.command.*;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    private static final Map<Operation, Command> allKnownCommandsMap = new HashMap<>();

    static {
        allKnownCommandsMap.put(Operation.SHOW_CATALOG, new ShowCommand());
        allKnownCommandsMap.put(Operation.ADD_FOLDING_BIKE, new AddFoldingBikeCommand());
        allKnownCommandsMap.put(Operation.ADD_SPEEDELEC_BIKE, new AddSpeedelecBikeCommand());
        allKnownCommandsMap.put(Operation.ADD_E_BIKE, new AddEBikeCommand());
        allKnownCommandsMap.put(Operation.FIND_FIRST_ITEM_BY_BRAND, new FindCommand());
        allKnownCommandsMap.put(Operation.WRITE_TO_FILE, new WriteToFileCommand());
        allKnownCommandsMap.put(Operation.STOP_PROGRAM, new ExitCommand());
    }

    private CommandExecutor() {
    }

    public static void execute(Operation operation) throws Exception {
        allKnownCommandsMap.get(operation).execute();
    }
}

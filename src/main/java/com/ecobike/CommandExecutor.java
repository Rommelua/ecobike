package com.ecobike;

import com.ecobike.command.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class responsible for execution properly command for operation chosen by user.
 */
public class CommandExecutor {
    /**
     * Map contains properly command for all operations.
     */
    private static final Map<Operation, Command> allKnownCommandsMap = new HashMap<>();

    static {
        allKnownCommandsMap.put(Operation.SHOW_CATALOG, new ShowCommand());
        allKnownCommandsMap.put(Operation.ADD_FOLDING_BIKE, new AddFoldingBikeCommand());
        allKnownCommandsMap.put(Operation.ADD_SPEEDELEC_BIKE, new AddSpeedelecBikeCommand());
        allKnownCommandsMap.put(Operation.ADD_E_BIKE, new AddEBikeCommand());
        allKnownCommandsMap.put(Operation.FIND_ITEMS_BY_BRAND, new FindCommand());
        allKnownCommandsMap.put(Operation.WRITE_TO_FILE, new WriteToFileCommand());
    }

    private CommandExecutor() {
    }

    /**
     * Method execute command for specified operation.
     * @param operation chosen by user.
     */
    public static void execute(Operation operation) {
        allKnownCommandsMap.get(operation).execute();
    }
}

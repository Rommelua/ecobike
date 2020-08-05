package com.ecobike;

import com.ecobike.command.AddEBikeCommand;
import com.ecobike.command.AddFoldingBikeCommand;
import com.ecobike.command.AddSpeedelecBikeCommand;
import com.ecobike.command.Command;
import com.ecobike.command.FindCommand;
import com.ecobike.command.ShowCommand;
import com.ecobike.command.StopProgramCommand;
import com.ecobike.command.WriteToFileCommand;
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
        allKnownCommandsMap.put(Operation.STOP_PROGRAM, new StopProgramCommand());
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

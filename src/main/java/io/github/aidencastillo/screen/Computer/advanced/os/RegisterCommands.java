package io.github.aidencastillo.screen.Computer.advanced.os;

import io.github.aidencastillo.screen.Computer.advanced.TerminalWidget;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class RegisterCommands {
    private static final Map<String, Consumer<TerminalWidget>> COMMANDS = new HashMap<>();

    // Automatically register commands
    static {
        registerCommand("help", Commands::helpCommand);
        registerCommand("clear", Commands::clearCommand);
        registerCommand("exit", Commands::exitCommand);
        registerCommand("test", Commands::testCommand);
        registerCommand("save", Commands::saveCommand);
        registerCommand("mkdir", Commands::mkdirCommand);
        registerCommand("ls", Commands::lsCommand);
        registerCommand("touch", Commands::touchCommand);
    }


    public static void checkForCommand(TerminalWidget terminalWidget, String enteredText) {
        COMMANDS.getOrDefault(enteredText, Commands::invalidCommand).accept(terminalWidget);
    }

    private static void registerCommand(String commandName, Consumer<TerminalWidget> action) {
        COMMANDS.put(commandName, action);
    }


}

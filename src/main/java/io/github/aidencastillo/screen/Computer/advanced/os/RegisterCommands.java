package io.github.aidencastillo.screen.Computer.advanced.os;

import io.github.aidencastillo.screen.Computer.advanced.TerminalWidget;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class RegisterCommands {
    private static final Map<String, Consumer<TerminalWidget>> COMMANDS = new HashMap<>();

    // Automatically register commands
    static {
//        registerCommands(Commands.class);
        registerCommand("help", Commands::helpCommand);
        registerCommand("clear", Commands::clearCommand);
        registerCommand("exit", Commands::exitCommand);
        registerCommand("test", Commands::testCommand);
        registerCommand("save", terminalWidget -> {
            try {
                Commands.saveCommand(terminalWidget);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        registerCommand("load", terminalWidget -> {
            try {
                Commands.loadCommand(terminalWidget);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        registerCommand("mkdir", Commands::mkdirCommand);
        registerCommand("cd", Commands::cdCommand);
        registerCommand("ls", Commands::lsCommand);
        registerCommand("touch", Commands::touchCommand);
    }

    static void registerCommands(Class<?> commandClass) {
        Method[] methods = commandClass.getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Command.class)) {
                String commandName = method.getAnnotation(Command.class).value();
                registerCommand(commandName, createCommandConsumer(method));
            }
        }
    }

    private static Consumer<TerminalWidget> createCommandConsumer(Method method) {
        return terminalWidget -> {
            try {
                method.invoke(null, terminalWidget);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }


    public static void checkForCommand(TerminalWidget terminalWidget, String enteredText) {
        COMMANDS.getOrDefault(enteredText, Commands::invalidCommand).accept(terminalWidget);
    }

    static void registerCommand(String commandName, Consumer<TerminalWidget> action) {
        COMMANDS.put(commandName, action);
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface Command {
        String value();
    }

}

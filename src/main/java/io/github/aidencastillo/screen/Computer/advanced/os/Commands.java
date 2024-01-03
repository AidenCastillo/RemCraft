package io.github.aidencastillo.screen.Computer.advanced.os;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.aidencastillo.block.entity.AdvancedComputerBlockEntity;
import io.github.aidencastillo.screen.Computer.advanced.AdvancedComputerMenu;
import io.github.aidencastillo.screen.Computer.advanced.TerminalWidget;
import io.github.aidencastillo.screen.Computer.advanced.os.fileSystem.Directory;
import io.github.aidencastillo.screen.Computer.advanced.os.fileSystem.File;
import net.minecraft.network.chat.Component;

import java.io.IOException;

public class Commands {
    static void helpCommand(TerminalWidget terminalWidget) {
        terminalWidget.addHistoryEntry(Component.nullToEmpty("help - Displays all commands"), 0x03AC13);
        terminalWidget.addHistoryEntry(Component.nullToEmpty("clear - Clears the terminal"), 0x03AC13);
        terminalWidget.addHistoryEntry(Component.nullToEmpty("exit - Exits the terminal"), 0x03AC13);
    }

    static void clearCommand(TerminalWidget terminalWidget) {
        terminalWidget.history.clear();
    }

    static void exitCommand(TerminalWidget terminalWidget) {
        // kill the terminal
        terminalWidget.addHistoryEntry(Component.nullToEmpty("Exiting terminal..."), 0x03AC13);
        terminalWidget.addHistoryEntry(Component.nullToEmpty("Terminal exited."), 0x03AC13);
    }

    static void invalidCommand(TerminalWidget terminalWidget) {
        terminalWidget.addHistoryEntry(Component.nullToEmpty("Invalid command. Type 'help' for a list of commands."), 0x03AC13);
    }

    static void testCommand(TerminalWidget terminalWidget) {
//        AdvancedComputerBlockEntity.fileSystem.getEntry("test").getName();

    }

//    static void saveCommand(TerminalWidget terminalWidget, AdvancedComputerBlockEntity computer) {
//        terminalWidget.addHistoryEntry(Component.nullToEmpty("Saving..."), 0x03AC13);
//        terminalWidget.saveFileSystem();
//        terminalWidget.addHistoryEntry(Component.nullToEmpty("Saved."), 0x03AC13);
//    }

    public static void saveCommand(TerminalWidget terminalWidget) {
        terminalWidget.addHistoryEntry(Component.nullToEmpty("Saving..."), 0x03AC13);

//        System.out.println(AdvancedComputerBlockEntity.fileSystem.getRoot().getChildren());

        terminalWidget.addHistoryEntry(Component.nullToEmpty("Saved."), 0x03AC13);
    }

    public static void mkdirCommand(TerminalWidget terminalWidget) {
        terminalWidget.addHistoryEntry(Component.nullToEmpty("Creating directory..."), 0x03AC13);

        AdvancedComputerBlockEntity.fileSystem.getRoot().addChild(new Directory("testDirectory", null));

        terminalWidget.addHistoryEntry(Component.nullToEmpty("Directory created."), 0x03AC13);

    }

    public static void lsCommand(TerminalWidget terminalWidget) {
        terminalWidget.addHistoryEntry(Component.nullToEmpty("Listing files..."), 0x03AC13);

        for (int i = 0; i < AdvancedComputerBlockEntity.fileSystem.getRoot().getChildren().size(); i++) {
            terminalWidget.addHistoryEntry(Component.nullToEmpty(AdvancedComputerBlockEntity.fileSystem.getRoot().getChildren().get(i).getName()), 0x03AC13);
        }

//        terminalWidget.addHistoryEntry(Component.nullToEmpty(AdvancedComputerBlockEntity.fileSystem.getRoot().getChildren().toString()), 0x03AC13);
        terminalWidget.addHistoryEntry(Component.nullToEmpty("Files listed."), 0x03AC13);
    }

    public static void touchCommand(TerminalWidget terminalWidget) {
        terminalWidget.addHistoryEntry(Component.nullToEmpty("Creating file..."), 0x03AC13);

        AdvancedComputerBlockEntity.fileSystem.getRoot().addChild(new File("test", "test"));

        terminalWidget.addHistoryEntry(Component.nullToEmpty("File created."), 0x03AC13);
    }

}

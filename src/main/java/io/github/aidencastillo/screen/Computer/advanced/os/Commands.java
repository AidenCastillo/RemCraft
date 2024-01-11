package io.github.aidencastillo.screen.Computer.advanced.os;

import io.github.aidencastillo.block.entity.AdvancedComputerBlockEntity;
import io.github.aidencastillo.screen.Computer.advanced.TerminalWidget;
import io.github.aidencastillo.screen.Computer.advanced.os.fileSystem.Directory;
import io.github.aidencastillo.screen.Computer.advanced.os.fileSystem.File;
import io.github.aidencastillo.screen.Computer.advanced.os.fileSystem.FileSystem;
import net.minecraft.network.chat.Component;

import java.io.IOException;

public class Commands {
    @RegisterCommands.Command("help")
    static void helpCommand(TerminalWidget terminalWidget) {
        terminalWidget.addHistoryEntry(Component.nullToEmpty("help - Displays all commands"), 0x03AC13);
        terminalWidget.addHistoryEntry(Component.nullToEmpty("clear - Clears the terminal"), 0x03AC13);
        terminalWidget.addHistoryEntry(Component.nullToEmpty("exit - Exits the terminal"), 0x03AC13);
    }

    @RegisterCommands.Command("clear")
    static void clearCommand(TerminalWidget terminalWidget) {
        terminalWidget.history.clear();
    }

    @RegisterCommands.Command("exit")
    static void exitCommand(TerminalWidget terminalWidget) {
        // kill the terminal
        terminalWidget.addHistoryEntry(Component.nullToEmpty("Exiting terminal..."), 0x03AC13);
        terminalWidget.addHistoryEntry(Component.nullToEmpty("Terminal exited."), 0x03AC13);
    }

    static void invalidCommand(TerminalWidget terminalWidget) {
        terminalWidget.addHistoryEntry(Component.nullToEmpty("Invalid command. Type 'help' for a list of commands."), 0x03AC13);
    }

    @RegisterCommands.Command("test")
    static void testCommand(TerminalWidget terminalWidget) {
//        AdvancedComputerBlockEntity.fileSystem.getEntry("test").getName();

    }

//    static void saveCommand(TerminalWidget terminalWidget, AdvancedComputerBlockEntity computer) {
//        terminalWidget.addHistoryEntry(Component.nullToEmpty("Saving..."), 0x03AC13);
//        terminalWidget.saveFileSystem();
//        terminalWidget.addHistoryEntry(Component.nullToEmpty("Saved."), 0x03AC13);
//    }

    @RegisterCommands.Command("save")
    public static void saveCommand(TerminalWidget terminalWidget) throws IOException {
        terminalWidget.addHistoryEntry(Component.nullToEmpty("Saving..."), 0x03AC13);

        AdvancedComputerBlockEntity.fileSystem.serialize("output.fsd");

        terminalWidget.addHistoryEntry(Component.nullToEmpty("Saved."), 0x03AC13);
    }

    @RegisterCommands.Command("load")
    public static void loadCommand(TerminalWidget terminalWidget) throws IOException {
        terminalWidget.addHistoryEntry(Component.nullToEmpty("Loading..."), 0x03AC13);

        AdvancedComputerBlockEntity.fileSystem = FileSystem.deserialize("output.fsd");

        terminalWidget.addHistoryEntry(Component.nullToEmpty("Loaded."), 0x03AC13);
    }

    @RegisterCommands.Command("mkdir")
    public static void mkdirCommand(TerminalWidget terminalWidget) {
        terminalWidget.addHistoryEntry(Component.nullToEmpty("Creating directory..."), 0x03AC13);

        AdvancedComputerBlockEntity.fileSystem.getRoot().addChild(new Directory("testDirectory", null));

        terminalWidget.addHistoryEntry(Component.nullToEmpty("Directory created."), 0x03AC13);

    }

    @RegisterCommands.Command("ls")
    public static void lsCommand(TerminalWidget terminalWidget) {
        terminalWidget.addHistoryEntry(Component.nullToEmpty("Listing files..."), 0x03AC13);

        for (int i = 0; i < AdvancedComputerBlockEntity.fileSystem.getRoot().getChildren().size(); i++) {
            terminalWidget.addHistoryEntry(Component.nullToEmpty(AdvancedComputerBlockEntity.fileSystem.getRoot().getChildren().get(i).getName()), 0x03AC13);
        }

//        terminalWidget.addHistoryEntry(Component.nullToEmpty(AdvancedComputerBlockEntity.fileSystem.getRoot().getChildren().toString()), 0x03AC13);
        terminalWidget.addHistoryEntry(Component.nullToEmpty("Files listed."), 0x03AC13);
    }

    @RegisterCommands.Command("touch")
    public static void touchCommand(TerminalWidget terminalWidget) {
        terminalWidget.addHistoryEntry(Component.nullToEmpty("Creating file..."), 0x03AC13);

        AdvancedComputerBlockEntity.fileSystem.getRoot().addChild(new File("test", "test"));

        terminalWidget.addHistoryEntry(Component.nullToEmpty("File created."), 0x03AC13);
    }

    @RegisterCommands.Command("cat")
    public static void catCommand(TerminalWidget terminalWidget) {
        terminalWidget.addHistoryEntry(Component.nullToEmpty("Reading file..."), 0x03AC13);

        terminalWidget.addHistoryEntry(Component.nullToEmpty(AdvancedComputerBlockEntity.fileSystem.getRoot().getChildren().get(0).getContent()), 0x03AC13);

        terminalWidget.addHistoryEntry(Component.nullToEmpty("File read."), 0x03AC13);
    }

    public static void cdCommand(TerminalWidget terminalWidget) {
    }
}

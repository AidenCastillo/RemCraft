package io.github.aidencastillo.screen.Computer.advanced.os;

import io.github.aidencastillo.block.entity.AdvancedComputerBlockEntity;
import io.github.aidencastillo.screen.Computer.advanced.AdvancedComputerScreen;
import io.github.aidencastillo.screen.Computer.advanced.TerminalWidget;
import io.github.aidencastillo.screen.Computer.advanced.os.fileSystem.FileSystem;
import org.junit.Test;

import static io.github.aidencastillo.screen.Computer.advanced.os.RegisterCommands.*;
import static org.junit.Assert.assertEquals;

public class RegisterCommandsTest {

    
    public RegisterCommandsTest() {
        
    }

    @Test
    public void testRegister() {
        registerCommand("help", Commands::helpCommand);
    }
    @Test
    public void testCommands() {
        FileSystem testFileSystem = FileSystem.deserialize("src/test/resources/output.fsd");

        registerCommands(Commands.class);

        TerminalWidget terminalWidget = new TerminalWidget(null, 0, 0, 0, 0, "", null, 0, testFileSystem);
        checkForCommand(terminalWidget, "help");
        checkForCommand(terminalWidget, "clear");


//        assertEquals("help - Displays all commands", terminalWidget.history.get(0).getText());


    }
}

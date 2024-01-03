package io.github.aidencastillo.screen.Computer.advanced;

import io.github.aidencastillo.block.entity.AdvancedComputerBlockEntity;
import io.github.aidencastillo.screen.Computer.advanced.os.HistoryEntry;
import io.github.aidencastillo.screen.Computer.advanced.os.fileSystem.FileSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import org.jline.reader.History;

import java.util.ArrayList;
import java.util.List;

import static io.github.aidencastillo.screen.Computer.advanced.os.RegisterCommands.checkForCommand;

public class TerminalWidget extends AbstractWidget {
    private FileSystem fileSystem;


    public EditBox editBox;
    public Button doneButton;

    private int visibleLines;
    public List<HistoryEntry> history;
    private List<HistoryEntry> visibleHistory;
    private int startingIndex;

    public TerminalWidget(Font fontRenderer, int pX, int pY, int pWidth, int pHeight, String enterCommand, List<String> initialLines, int visibleLines) {
        super(pX, pY, pWidth, pHeight, Component.empty());
        this.history = new ArrayList<>();
        this.visibleLines = visibleLines;
        this.visibleHistory = new ArrayList<>();
        this.startingIndex = 0;
        this.fileSystem = AdvancedComputerBlockEntity.fileSystem;

    }

    @Override
    protected void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {

    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput pNarrationElementOutput) {

    }

    public void addHistoryEntry(Component text, int color) {
        history.add(new HistoryEntry(text, color));
    }

    public List<HistoryEntry> getFullHistory() {
        return history;
    }

    public String getText() {
        return editBox.getValue();
    }

    public void onDone() {
        String enteredText = getText();
        System.out.println(enteredText);

        history.add(new HistoryEntry(Component.nullToEmpty(enteredText), 0xFFFFFF));
        editBox.setValue("");

        for (int i = 0; i < history.size(); i++) {
            System.out.println(history.get(i));
            history.set(i, history.get(i));
        }
        System.out.println("History size: " + history.size());

        checkForCommand(this, enteredText);
    }



    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);

        // Calculate the start index based on the visibleLines value
        int startIndex = Math.max(0, history.size() - visibleLines);

        // Render the visible lines
        for (int i = startIndex; i < history.size(); i++) {
            pGuiGraphics.drawString(Minecraft.getInstance().font, history.get(i).getText(), this.getX(), this.getY() + ((i - startIndex) * 10), history.get(i).getColor());
        }

        // Render the current input line
//        pGuiGraphics.drawString(Minecraft.getInstance().font, "$" + editBox.getValue(), this.getX(), this.getY() + ((history.size() - startIndex) * 10), 0xFFFFFF);
    }
    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        boolean result = super.mouseClicked(pMouseX, pMouseY, pButton);
        this.editBox.setFocused(true);

        return result;
    }
}

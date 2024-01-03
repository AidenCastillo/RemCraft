package io.github.aidencastillo.screen.Computer.normal;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class NormalTerminalWidget extends AbstractWidget {
    protected EditBox editBox;
    protected Button doneButton;
    protected Button scrollUpButton;
    protected Button scrollDownButton;

    private int visibleLines;
    private List<String> history;
    private List<String> visibleHistory;
    private int startingIndex;

    public NormalTerminalWidget(Font fontRenderer, int pX, int pY, int pWidth, int pHeight, String enterCommand, List<String> initialLines, int visibleLines) {
        super(pX, pY, pWidth, pHeight, Component.empty());
        this.history = new ArrayList<>(initialLines);
        this.visibleLines = visibleLines;
        this.visibleHistory = new ArrayList<>(initialLines);
        this.startingIndex = 0;
//        this.editBox = new EditBox(fontRenderer, pX, pY, pWidth, pHeight, Component.nullToEmpty(enterCommand));
    }


    @Override
    protected void updateWidgetNarration(NarrationElementOutput pNarrationElementOutput) {

    }

    @Override
    protected void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {

    }


    public String getText() {
        return editBox.getValue();
    }

    public void onDone() {
        String enteredText = getText();
        System.out.println(enteredText);

        history.add("> " + enteredText);
        editBox.setValue("");
        editBox.setY(editBox.getY() + 10);

        for (int i = 0; i < history.size(); i++) {
            System.out.println(history.get(i));
            history.set(i, history.get(i));
        }

    }

//    @Override
//    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
//
//        for (int i = scrollOffset; i < Math.min(history.size(), scrollOffset + visibleLines); i++) {
//            int lineIndex = i - scrollOffset;
//            pGuiGraphics.drawString(Minecraft.getInstance().font, history.get(i), this.getX(), this.getY() + (lineIndex * 10), 0xFFFFFF);
//        }
//
//
//        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
//    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);

        // Calculate the start index based on the visibleLines value
        int startIndex = Math.max(0, history.size() - visibleLines);

        // Render the visible lines
        for (int i = startIndex; i < history.size(); i++) {
            pGuiGraphics.drawString(Minecraft.getInstance().font, history.get(i), this.getX(), this.getY() + ((i - startIndex) * 10), 0xFFFFFF);
        }

        // Render the current input line
        pGuiGraphics.drawString(Minecraft.getInstance().font, editBox.getValue(), this.getX(), this.getY() + ((history.size() - startIndex) * 10), 0xFFFFFF);
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        boolean result = super.mouseClicked(pMouseX, pMouseY, pButton);
        this.editBox.setFocused(true);

        return result;
    }

    public Collection<Object> getHistory() {
        return Collections.singleton(history);
    }

    public void scrollUp() {
        if (startingIndex > 0) {
            startingIndex--;
        }
    }

    public void scrollDown() {
        if (startingIndex < history.size() - visibleLines) {
            startingIndex++;
        }

    }

    public void setVisibleHistory(List<String> history, int visibleLines, int startIndex) {
        for (int i = startIndex; i < Math.min(history.size(), startIndex + visibleLines); i++) {
            int lineIndex = i - startIndex;
            this.visibleHistory.set(lineIndex, history.get(i));
        }
    }
}

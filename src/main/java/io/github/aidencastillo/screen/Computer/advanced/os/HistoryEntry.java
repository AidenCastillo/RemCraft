package io.github.aidencastillo.screen.Computer.advanced.os;

import net.minecraft.network.chat.Component;

public class HistoryEntry {
    private Component text;
    private int color;

    public HistoryEntry(Component text, int color) {
        this.text = text;
        this.color = color;
    }

    public Component getText() {
        return text;
    }

    public int getColor() {
        return color;
    }
}

package io.github.aidencastillo.screen.Computer.normal;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import org.lwjgl.glfw.GLFW;

import java.util.Arrays;
import java.util.List;

import static java.lang.Math.round;

public class ComputerScreen extends AbstractContainerScreen<ComputerMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("remcraft", "textures/gui/terminal/terminal.png");

    private NormalTerminalWidget widget;
    private final int visibleLines = 5;

    public ComputerScreen(ComputerMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }


    @Override
    protected void init() {
        super.init();
        this.inventoryLabelY = 10000;
        this.titleLabelY = 10000;

        //create empty list of strings
        List<String> lines = Arrays.asList();

        widget = new NormalTerminalWidget(font, leftPos + 10, topPos + 10, 100, 20,"Enter Command", lines, visibleLines);

        EditBox editBox = new EditBox(font, leftPos + 10, topPos + 10, 156, 10, Component.empty());
        editBox.setTextColor(0xFFFFFF);
        editBox.setBordered(false);


//        widget.editBox = this.addRenderableWidget(new EditBox(font, leftPos + 11, topPos + 10, 156, 20, Component.empty()));
        widget.editBox = this.addRenderableWidget(editBox);
        widget.doneButton = this.addRenderableWidget(Button.builder(CommonComponents.GUI_DONE, (p_97691_) -> {
            widget.onDone();
        }).build());
        // move the done button to the right of the edit box
        widget.doneButton.setWidth(20);
        widget.doneButton.setY(widget.editBox.getY());

        widget.scrollUpButton = this.addRenderableWidget(Button.builder(Component.nullToEmpty("Scroll Up"), (p_97691_) -> {
            widget.scrollUp();
        }).build());
      widget.scrollUpButton.setWidth(20);
        widget.scrollUpButton.setY(widget.editBox.getY() + 20);

        widget.scrollDownButton = this.addRenderableWidget(Button.builder(Component.nullToEmpty("Scroll Down"), (p_97691_) -> {
            widget.scrollDown();
        }).build());
        widget.scrollDownButton.setWidth(20);
        widget.scrollDownButton.setY(widget.editBox.getY() + 40);

        this.addWidget(widget);

    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        if (this.widget.mouseClicked(pMouseX, pMouseY, pButton)) {
            this.setFocused(this.widget);
        } else {
            this.setFocused(false);
        }

        return super.mouseClicked(pMouseX, pMouseY, pButton);
    }

    @Override
    public boolean mouseScrolled(double pMouseX, double pMouseY, double pDelta) {
        System.out.println("mouseScrolled");




        return super.mouseScrolled(pMouseX, pMouseY, pDelta);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        // If the 'E' key or number keys are pressed, do not close the screen
        if (keyCode == GLFW.GLFW_KEY_E || (keyCode >= GLFW.GLFW_KEY_1 && keyCode <= GLFW.GLFW_KEY_9)) {
            return true;
        }
        if (keyCode == GLFW.GLFW_KEY_ENTER) {
            widget.onDone();
        }

        // For all other keys, call the super method to handle them as usual
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean charTyped(char pCodePoint, int pModifiers) {
        if (pCodePoint == 'a') {
            System.out.println("a was pressed");
        }

        return super.charTyped(pCodePoint, pModifiers);
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;


        pGuiGraphics.blit(TEXTURE, x, y, 0, 0, 232, imageHeight);
    }

    // render stretched texture for the background of the GUI
    // given width and height of the background image, and the width and height of the GUI

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float delta) {
        renderBackground(pGuiGraphics);

        widget.render(pGuiGraphics, pMouseX, pMouseY, delta);

        super.render(pGuiGraphics, pMouseX, pMouseY, delta);
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }

}

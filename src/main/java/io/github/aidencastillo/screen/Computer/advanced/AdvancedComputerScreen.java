package io.github.aidencastillo.screen.Computer.advanced;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.aidencastillo.block.entity.AdvancedComputerBlockEntity;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import org.lwjgl.glfw.GLFW;

import java.util.Arrays;
import java.util.List;

public class AdvancedComputerScreen extends AbstractContainerScreen<AdvancedComputerMenu> {


    private static final ResourceLocation TEXTURE =
            new ResourceLocation("remcraft", "textures/gui/terminal/basic-terminal.png");
    private TerminalWidget widget;
    private final int visibleLines = 14;

    public AdvancedComputerScreen(AdvancedComputerMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    public void init() {
        super.init();
        this.inventoryLabelY = 10000;
        this.titleLabelY = 10000;

        int xPos = leftPos - 34;
        int yPos = topPos + 10;

        //create empty list of strings
        List<String> lines = Arrays.asList();

        widget = new TerminalWidget(font, xPos, yPos, 100, 20,"Enter Command", lines, visibleLines, AdvancedComputerBlockEntity.fileSystem);

        EditBox editBox = new EditBox(font, xPos, topPos + 150, 156, 10, Component.empty());
        editBox.setTextColor(0x03AC13);
        editBox.setBordered(false);

        widget.editBox = this.addRenderableWidget(editBox);
        widget.doneButton = this.addRenderableWidget(Button.builder(CommonComponents.GUI_DONE, (p_97691_) -> {
            widget.onDone();
        }).build());

        this.addWidget(widget);
        setFocused(widget.editBox);
    }

    @Override
    public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
        // If the 'E' key or number keys are pressed, do not close the screen
        if (pKeyCode == GLFW.GLFW_KEY_E || (pKeyCode >= GLFW.GLFW_KEY_1 && pKeyCode <= GLFW.GLFW_KEY_9)) {
            return true;
        }
        if (pKeyCode == GLFW.GLFW_KEY_ENTER) {
            widget.onDone();
        }

        return super.keyPressed(pKeyCode, pScanCode, pModifiers);
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (this.width - 300) / 2;
        int y = (this.height - 170) / 2;

        pGuiGraphics.blit(TEXTURE, x, y, 0, 0, 300, 170, 300, 170);
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pDelta) {
        renderBackground(pGuiGraphics);

        widget.render(pGuiGraphics, pMouseX, pMouseY, pDelta);

        super.render(pGuiGraphics, pMouseX, pMouseY, pDelta);
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }
}

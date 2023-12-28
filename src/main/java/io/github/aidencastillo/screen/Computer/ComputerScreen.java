package io.github.aidencastillo.screen.Computer;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.client.gui.components.MultilineTextField;
import org.lwjgl.glfw.GLFW;

public class ComputerScreen extends AbstractContainerScreen<ComputerMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("remcraft", "textures/gui/terminal/terminal.png");

    public ComputerScreen(ComputerMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }


    @Override
    protected void init() {
        super.init();
        this.inventoryLabelY = 10000;
        this.titleLabelY = 10000;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        // If the 'E' key or number keys are pressed, do not close the screen
        if (keyCode == GLFW.GLFW_KEY_E || (keyCode >= GLFW.GLFW_KEY_1 && keyCode <= GLFW.GLFW_KEY_9)) {
            return true;
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
        super.render(pGuiGraphics, pMouseX, pMouseY, delta);
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }

}

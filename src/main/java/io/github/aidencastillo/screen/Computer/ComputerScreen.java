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

public class ComputerScreen extends AbstractContainerScreen<ComputerMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("remcraft", "textures/gui/terminal/terminal.png");

    public ComputerScreen(ComputerMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
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

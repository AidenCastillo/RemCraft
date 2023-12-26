package io.github.aidencastillo.entity.client.narwhal;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.aidencastillo.RemCraft;
import io.github.aidencastillo.entity.client.ModModelLayers;
import io.github.aidencastillo.entity.custom.NarwhalEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class NarwhalRenderer extends MobRenderer<NarwhalEntity, NarwhalModel<NarwhalEntity>> {

    public NarwhalRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new NarwhalModel<>(pContext.bakeLayer(ModModelLayers.NARWHAL_LAYER)), 2f);
    }

    @Override
    public ResourceLocation getTextureLocation(NarwhalEntity pEntity) {
        return new ResourceLocation(RemCraft.MODID, "textures/entity/narwhal.png");
    }

    @Override
    public void render(NarwhalEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {

        if (pEntity.isBaby()) {
            pMatrixStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

}

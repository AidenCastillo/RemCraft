package io.github.aidencastillo.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.aidencastillo.RemCraft;
import io.github.aidencastillo.entity.client.ModModelLayers;
import io.github.aidencastillo.entity.client.RhinoModel;
import io.github.aidencastillo.entity.custom.RhinoEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RhinoRenderer extends MobRenderer<RhinoEntity, RhinoModel<RhinoEntity>> {

    public RhinoRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new RhinoModel<>(pContext.bakeLayer(ModModelLayers.RHINO_LAYER)), 2f);
    }

    @Override
    public ResourceLocation getTextureLocation(RhinoEntity pEntity) {
        return new ResourceLocation(RemCraft.MODID, "textures/entity/rhino.png");
    }

    @Override
    public void render(RhinoEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {

        if (pEntity.isBaby()) {
            pMatrixStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}

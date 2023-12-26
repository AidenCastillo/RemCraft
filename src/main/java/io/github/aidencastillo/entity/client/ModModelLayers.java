package io.github.aidencastillo.entity.client;

import io.github.aidencastillo.RemCraft;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {
    public static final ModelLayerLocation RHINO_LAYER = new ModelLayerLocation(
            new ResourceLocation(RemCraft.MODID, "rhino_layer"), "main");

    public static final ModelLayerLocation NARWHAL_LAYER = new ModelLayerLocation(
            new ResourceLocation(RemCraft.MODID, "narwhal_layer"), "main");
}

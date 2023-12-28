package io.github.aidencastillo.event;

import io.github.aidencastillo.RemCraft;
import io.github.aidencastillo.entity.client.ModModelLayers;
import io.github.aidencastillo.entity.client.NarwhalModel;
import io.github.aidencastillo.entity.client.RhinoModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RemCraft.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.RHINO_LAYER, RhinoModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.NARWHAL_LAYER, NarwhalModel::createBodyLayer);
    }

}

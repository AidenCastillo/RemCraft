package io.github.aidencastillo.event;

import io.github.aidencastillo.RemCraft;
import io.github.aidencastillo.entity.ModEntities;
import io.github.aidencastillo.entity.custom.RhinoEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RemCraft.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
         event.put(ModEntities.RHINO.get(), RhinoEntity.createAttributes().build());
         event.put(ModEntities.NARWHAL.get(), RhinoEntity.createAttributes().build());
    }
}

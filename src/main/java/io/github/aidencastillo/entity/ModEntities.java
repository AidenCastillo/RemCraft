package io.github.aidencastillo.entity;

import io.github.aidencastillo.RemCraft;
import io.github.aidencastillo.entity.custom.NarwhalEntity;
import io.github.aidencastillo.entity.custom.RhinoEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, RemCraft.MODID);

    public static final RegistryObject<EntityType<RhinoEntity>> RHINO =
            ENTITY_TYPES.register("rhino", () -> EntityType.Builder.of(RhinoEntity::new, MobCategory.CREATURE)
                    .sized(2.5f, 2.5f).build("rhino"));

    public static final RegistryObject<EntityType<NarwhalEntity>> NARWHAL =
            ENTITY_TYPES.register("narwhal", () -> EntityType.Builder.of(NarwhalEntity::new, MobCategory.WATER_CREATURE)
                    .sized(2.5f, 2.5f).build("narwhal"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}

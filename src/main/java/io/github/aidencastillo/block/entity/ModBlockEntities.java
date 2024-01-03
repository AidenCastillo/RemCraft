package io.github.aidencastillo.block.entity;

import io.github.aidencastillo.RemCraft;
import io.github.aidencastillo.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, RemCraft.MODID);

    public static final RegistryObject<BlockEntityType<GemPolishingStationBlockEntity>> GEM_POLISHING_BE =
            BLOCK_ENTITIES.register("gem_polishing_be",
                    () -> BlockEntityType.Builder.of(GemPolishingStationBlockEntity::new,
                            ModBlocks.GEM_POLISHING_STATION.get()).build(null));
    public static final RegistryObject<BlockEntityType<ComputerBlockEntity>> COMPUTER_BE =
            BLOCK_ENTITIES.register("computer_be",
                    () -> BlockEntityType.Builder.of(ComputerBlockEntity::new,
                            ModBlocks.COMPUTER.get()).build(null));
    public static final RegistryObject<BlockEntityType<AdvancedComputerBlockEntity>> ADVANCED_COMPUTER_BE =
            BLOCK_ENTITIES.register("advanced_computer_be",
                    () -> BlockEntityType.Builder.of(AdvancedComputerBlockEntity::new,
                            ModBlocks.ADVANCED_COMPUTER.get()).build(null));
    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}

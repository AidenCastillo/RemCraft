package io.github.aidencastillo.item;

import io.github.aidencastillo.RemCraft;
import io.github.aidencastillo.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RemCraft.MODID);

    public static final RegistryObject<CreativeModeTab> REMCRAFT_TAB = CREATIVE_MODE_TABS.register("remcraft_tab",
        () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.SAPPHIRE.get()))
            .title(Component.translatable("creativetab.remcraft_tab"))
            .displayItems((pParameters, pOutput) -> {
                pOutput.accept(new ItemStack(ModItems.SAPPHIRE.get()));
                pOutput.accept(new ItemStack(ModItems.RAW_SAPPHIRE.get()));

                pOutput.accept(new ItemStack(ModItems.METAL_DETECTOR.get()));
                pOutput.accept(ModItems.RHINO_SPAWN_EGG.get());
                pOutput.accept(ModItems.NARWHAL_SPAWN_EGG.get());

                pOutput.accept(ModBlocks.SAPPHIRE_BLOCK.get());
                pOutput.accept(ModBlocks.RAW_SAPPHIRE_BLOCK.get());
                pOutput.accept(ModBlocks.SAPPHIRE_ORE.get());
                pOutput.accept(ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get());
                pOutput.accept(ModBlocks.NETHER_SAPPHIRE_ORE.get());
                pOutput.accept(ModBlocks.END_STONE_SAPPHIRE_ORE.get());

                pOutput.accept(ModBlocks.GEM_POLISHING_STATION.get());
                pOutput.accept(ModBlocks.COMPUTER.get());

            })
            .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}

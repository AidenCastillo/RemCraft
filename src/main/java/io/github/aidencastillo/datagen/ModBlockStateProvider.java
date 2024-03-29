package io.github.aidencastillo.datagen;

import io.github.aidencastillo.RemCraft;
import io.github.aidencastillo.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, RemCraft.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.SAPPHIRE_BLOCK);
        blockWithItem(ModBlocks.RAW_SAPPHIRE_BLOCK);

        blockWithItem(ModBlocks.SAPPHIRE_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_SAPPHIRE_ORE);
        blockWithItem(ModBlocks.NETHER_SAPPHIRE_ORE);
        blockWithItem(ModBlocks.END_STONE_SAPPHIRE_ORE);

        simpleBlockWithItem(ModBlocks.GEM_POLISHING_STATION.get(),
            new ModelFile.UncheckedModelFile(modLoc("block/gem_polishing_station")));
        simpleBlockWithItem(ModBlocks.COMPUTER.get(),
            new ModelFile.UncheckedModelFile(modLoc("block/computer")));
        simpleBlockWithItem(ModBlocks.ADVANCED_COMPUTER.get(),
            new ModelFile.UncheckedModelFile(modLoc("block/advanced_computer")));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}

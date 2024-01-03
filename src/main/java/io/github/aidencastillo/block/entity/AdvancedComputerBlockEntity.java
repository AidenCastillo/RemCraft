package io.github.aidencastillo.block.entity;

import io.github.aidencastillo.screen.Computer.advanced.AdvancedComputerMenu;
import io.github.aidencastillo.screen.Computer.advanced.os.fileSystem.Directory;
import io.github.aidencastillo.screen.Computer.advanced.os.fileSystem.FileSystem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class AdvancedComputerBlockEntity extends BlockEntity implements MenuProvider {
    private static final Logger LOGGER = LogManager.getLogger();
//    private final ItemStackHandler itemHandler = new ItemStackHandler(1);
    private static final int INPUT_SLOT_0 = 0;
    protected final ContainerData data;

    public String user = "admin";
    private int securityLevel = 0;
    public static FileSystem fileSystem = new FileSystem();
    public static Directory root = fileSystem.getRoot();
    public static Directory home = new Directory("home", null);

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    public AdvancedComputerBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.ADVANCED_COMPUTER_BE.get(), pPos, pBlockState);
//        LOGGER.info("IItemHandler initialized with " + itemHandler.getSlots() + " slots.");
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> AdvancedComputerBlockEntity.this.securityLevel;
                    case 1 -> AdvancedComputerBlockEntity.this.user.hashCode();
                    case 2 -> AdvancedComputerBlockEntity.this.fileSystem.hashCode();
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> AdvancedComputerBlockEntity.this.securityLevel = pValue;
                    case 1 -> AdvancedComputerBlockEntity.this.user = AdvancedComputerBlockEntity.this.user;
                    case 2 -> AdvancedComputerBlockEntity.this.fileSystem = AdvancedComputerBlockEntity.this.fileSystem;
                }
            }

            @Override
            public int getCount() {
                return 3;
            }
        };
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

//    @Override
//    public void onLoad() {
//        super.onLoad();
//        lazyItemHandler = LazyOptional.of(() -> itemHandler);
//    }
//
//    @Override
//    public void invalidateCaps() {
//        super.invalidateCaps();
//        lazyItemHandler.invalidate();
//    }
//    public void drops() {
//        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
//        for (int i = 0; i < itemHandler.getSlots(); i++) {
//            inventory.setItem(i, itemHandler.getStackInSlot(i));
//        }
//        Containers.dropContents(this.level, this.worldPosition, inventory);
//    }

        @Override
    public Component getDisplayName() {
        return Component.translatable("block.remcraft.advanced_computer");
    }

    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new AdvancedComputerMenu(pContainerId, pInventory, this, this.data);
    }

//    @Override
//    protected void saveAdditional(CompoundTag pTag) {
////        pTag.put("inventory", itemHandler.serializeNBT());
//        pTag.put("fileSystem", (Tag) fileSystem);
//        super.saveAdditional(pTag);
//    }
//
//    @Override
//    public void load(CompoundTag pTag) {
//        super.load(pTag);
////        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
//        fileSystem = (FileSystem) pTag.get("fileSystem");
//    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
    }
}

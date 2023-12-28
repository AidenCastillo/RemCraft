package io.github.aidencastillo.block.entity;

import io.github.aidencastillo.item.ModItems;
import io.github.aidencastillo.screen.Computer.ComputerMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

public class ComputerBlockEntity extends BlockEntity implements MenuProvider {
    private static final Logger LOGGER = LogManager.getLogger();
    private final ItemStackHandler itemHandler = new ItemStackHandler(9);
    private static final int INPUT_SLOT_0 = 0;
    private static final int INPUT_SLOT_1 = 1;
        private static final int INPUT_SLOT_2 = 2;
        private static final int INPUT_SLOT_3 = 3;
        private static final int INPUT_SLOT_4 = 4;
        private static final int INPUT_SLOT_5 = 5;
        private static final int INPUT_SLOT_6 = 6;
        private static final int INPUT_SLOT_7 = 7;
        private static final int INPUT_SLOT_8 = 8;
    protected final ContainerData data;

    private String user = "admin";
    private int securityLevel = 0;

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();



    public ComputerBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.COMPUTER_BE.get(), pPos, pBlockState);
        LOGGER.info("IItemHandler initialized with " + itemHandler.getSlots() + " slots.");

        this.data = new ContainerData() {

            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> ComputerBlockEntity.this.securityLevel;
                    case 1 -> ComputerBlockEntity.this.user.hashCode();
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch(pIndex) {
                    case 0 -> ComputerBlockEntity.this.securityLevel = pValue;
                    case 1 -> ComputerBlockEntity.this.user = String.valueOf(pValue);
                }
            }



            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if ( cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }
    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.remcraft.computer");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new ComputerMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());

        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));

    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
    }
}

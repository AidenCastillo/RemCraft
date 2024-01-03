package io.github.aidencastillo.screen.Computer.advanced;

import io.github.aidencastillo.block.ModBlocks;
import io.github.aidencastillo.block.entity.AdvancedComputerBlockEntity;
import io.github.aidencastillo.screen.ModMenuTypes;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

public class AdvancedComputerMenu extends AbstractContainerMenu {
    public static String filePath;
    public final AdvancedComputerBlockEntity blockEntity;
    private final Level level;
    public final ContainerData data;
    private final Logger LOGGER = LogManager.getLogger();

    public AdvancedComputerMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(1));
        LOGGER.info("AdvancedComputerMenu initialized with " + data.get(0) + " slots.");
    }

    public AdvancedComputerMenu(int pContainerId, Inventory pInventory, BlockEntity entity, ContainerData data) {
        super(ModMenuTypes.ADVANCED_COMPUTER_MENU.get(), pContainerId);
        checkContainerSize(pInventory, 1);
        blockEntity = ((AdvancedComputerBlockEntity) entity);
        this.level = pInventory.player.level();
        this.data = data;

        this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(iItemHandler -> {
//            this.addSlot(new SlotItemHandler(iItemHandler, 0, 179, 84));
            LOGGER.info("AdvancedComputerMenu initialized with " + iItemHandler.getSlots() + " slots.");
        });

//        addDataSlots(data);
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return null;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), pPlayer, ModBlocks.ADVANCED_COMPUTER.get());
    }
}

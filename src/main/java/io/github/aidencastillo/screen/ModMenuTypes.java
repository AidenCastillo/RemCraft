package io.github.aidencastillo.screen;

import io.github.aidencastillo.RemCraft;
import io.github.aidencastillo.screen.Computer.advanced.AdvancedComputerMenu;
import io.github.aidencastillo.screen.Computer.advanced.AdvancedComputerScreen;
import io.github.aidencastillo.screen.Computer.normal.ComputerMenu;
import io.github.aidencastillo.screen.GemPolishingStation.GemPolishingStationMenu;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, RemCraft.MODID);

    public static final RegistryObject<MenuType<GemPolishingStationMenu>> GEM_POLISHING_MENU =
            registerMenuType("gem_polishing_menu", GemPolishingStationMenu::new);

    public static final RegistryObject<MenuType<ComputerMenu>> COMPUTER_MENU =
            registerMenuType("computer_menu", ComputerMenu::new);

    public static final RegistryObject<MenuType<AdvancedComputerMenu>> ADVANCED_COMPUTER_MENU =
            registerMenuType("advanced_computer_menu", AdvancedComputerMenu::new);
    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }
    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}

package com.floweytf.rebark;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

public class RebarkMain implements ModInitializer {
    public static final Item BARK = new BarkItem(new FabricItemSettings().tab(CreativeModeTab.TAB_MISC));
    public static final String MODID = "rebark";
    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, new ResourceLocation(MODID, "bark"), BARK);
        FuelRegistry.INSTANCE.add(BARK, 100);
    }
}

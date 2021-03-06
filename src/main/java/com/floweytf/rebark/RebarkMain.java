package com.floweytf.rebark;

import com.floweytf.rebark.mixin.AxeItemAccessor;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;

import static com.floweytf.rebark.RebarkClient.blockToId;

public class RebarkMain implements ModInitializer {
    public static final Item BARK = new BarkItem(new FabricItemSettings().tab(CreativeModeTab.TAB_MISC));
    public static final String MODID = "rebark";
    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, new ResourceLocation(MODID, "bark"), BARK);
        FuelRegistry.INSTANCE.add(BARK, 100);

        ServerWorldEvents.LOAD.register((server, level) -> {
            if (BarkItem.UNSTRIP == null) {
                BarkItem.UNSTRIP = AxeItemAccessor.getStrip().entrySet()
                    .stream()
                    .map((e) -> new AbstractMap.SimpleImmutableEntry<>(blockToId(e.getKey()), blockToId(e.getValue())))
                    .peek((e) -> System.out.printf("normal: %s     -     stripped: %s\n", e.getKey().toString(), e.getValue().toString()))
                    .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey, (prev, curr) -> prev));
            }
        });
    }
}
